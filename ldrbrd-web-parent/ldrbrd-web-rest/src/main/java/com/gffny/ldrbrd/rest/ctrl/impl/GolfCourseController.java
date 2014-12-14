/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.common.utils.CollectionUtils;
import com.gffny.ldrbrd.rest.ctrl.AbstractController;
import com.gffny.ldrbrd.rest.req.CourseRequest;
import com.gffny.ldrbrd.rest.resp.CourseInformationResponse;
import com.gffny.ldrbrd.web.model.JSONable;
import com.gffny.ldrbrd.web.model.JsonResponse;

/**
 * @author jdgaffney
 * 
 */
@Controller
@RequestMapping(value = "/course")
// , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public class GolfCourseController extends AbstractController {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(GolfCourseController.class);
	/**
	 * 
	 */
	@Autowired
	private ICourseClubService courseClubService;

	/**
	 * 
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/coursefromid", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public ResponseEntity<JsonResponse<JSONable>> getCourseFromId(
			@RequestBody final CourseRequest courseId) {
		CourseInformationResponse response = new CourseInformationResponse();
		if (null != courseId && null != courseId.getId()) {
			try {
				response.setCourse(courseClubService.getCourseById(courseId
						.getId()));
				if (response.getCourse() != null) {
					response.addCourseHoleMap(response.getCourse().getId(),
							courseClubService.getHoleListByCourseId(response
									.getCourse().getId(), response.getCourse()
									.isNineHole()));
					return returnSuccess(response, HttpStatus.OK);
				} else {
					LOG.error("course id {} does not return a valid course",
							courseId);
					return returnError(
							"course id does not return a valid course",
							HttpStatus.BAD_REQUEST);
				}
			} catch (ServiceException serEx) {
				return returnError(serEx.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		LOG.error("error with request parameters");
		return returnError("course id is not available", HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param clubId
	 * @return
	 */
	@RequestMapping(value = "/courseListFromClubId", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse<JSONable>> getCourseListFromClubId(
			@RequestBody final CourseRequest clubId) {
		CourseInformationResponse response = new CourseInformationResponse();
		if (null != clubId && null != clubId.getId()) {
			try {
				response.setCourseList(CollectionUtils.asList(courseClubService
						.getCourseById(clubId.getId())));
				return returnSuccess(response, HttpStatus.OK);
			} catch (ServiceException serEx) {
				return returnError(serEx.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		return returnError("course id is not available", HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param golferId
	 * @return
	 */
	@RequestMapping(value = "/listFromFavourite", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse<JSONable>> getCourseListFromFavourite(
			@RequestBody final CourseRequest golferId) {
		try {
			CourseInformationResponse response = new CourseInformationResponse();
			if (null != golferId && null != golferId.getId()
					&& null != golferId.getGolferId()) {
				response.setCourseList(courseClubService
						.getFavouriteCourseList(golferId.getGolferId(),
								golferId.getFavouriteLimit()));
				return returnSuccess(response, HttpStatus.OK);
			}
			return returnError(
					"golfer id or favourite length is not available ",
					HttpStatus.BAD_REQUEST);
		} catch (ServiceException e) {
			return returnError(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	@RequestMapping(value = "/listFromLocation", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse<JSONable>> getCourseListFromLocation(
			@RequestBody final CourseRequest location) {
		// TODO implement list from location
		return returnSuccess(HttpStatus.OK);
	}

	/**
	 * 
	 * @param zipCode
	 * @return
	 */
	@RequestMapping(value = "/listFromZip")
	public ResponseEntity<JsonResponse<JSONable>> getCourseListFromZip(
			@RequestBody final CourseRequest zipCode) {
		// TODO implement list from Zip
		return returnSuccess(HttpStatus.OK);
	}
}
