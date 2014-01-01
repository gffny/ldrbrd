/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.rest.ctrl.AbstractController;
import com.gffny.ldrbrd.rest.req.CourseRequest;
import com.gffny.ldrbrd.rest.resp.CourseInformationResponse;
import com.gffny.ldrbrd.utils.CollectionUtils;
import com.gffny.ldrbrd.web.model.JSONable;
import com.gffny.ldrbrd.web.model.JsonResponse;

/**
 * @author jdgaffney
 *
 */
@Controller
@RequestMapping(value = "/course")//, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public class GolfCourseController extends AbstractController{

	@Autowired
	private ICourseClubService courseClubService;
	
	/**
	 * 
	 * @param idCourseList
	 * @return
	 */
	@RequestMapping(value = "/listFromId", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse<JSONable>> getCourseListFromId(@RequestBody final CourseRequest idCourseList) {
		CourseInformationResponse response = new CourseInformationResponse();
		if(null != idCourseList && null != idCourseList.getId()) {
			response.setCourseList(CollectionUtils.asList(courseClubService.getCourseById(idCourseList.getId())));
			return returnSuccess(response, HttpStatus.OK);			
		}
		return returnError("course id is not available", HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param favouriteCourseList
	 * @return
	 */
	@RequestMapping(value = "/listFromFavourite", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse<JSONable>> getCourseListFromFavourite(@RequestBody final CourseRequest favouriteCourseList) {
		CourseInformationResponse response = new CourseInformationResponse();
		if(null != favouriteCourseList && null != favouriteCourseList.getId() && null != favouriteCourseList.getGolferId()) {
			response.setCourseList(courseClubService.getFavouriteCourseList(favouriteCourseList.getGolferId(), favouriteCourseList.getFavouriteLimit()));
			return returnSuccess(response, HttpStatus.OK);
		}
		return returnError("golfer id or favourite length is not available ", HttpStatus.BAD_REQUEST);		
	}

	/**
	 * 
	 * @param locationCourseList
	 * @return
	 */
	@RequestMapping(value = "/listFromLocation", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse<JSONable>> getCourseListFromLocation(@RequestBody final CourseRequest locationCourseList) {
		//TODO implement list from location
		return returnSuccess(HttpStatus.OK);
	}

	/**
	 * 
	 * @param zipCourseList
	 * @return
	 */
	@RequestMapping(value = "/listFromZip")
	public ResponseEntity<JsonResponse<JSONable>> getCourseListFromZip(@RequestBody final CourseRequest zipCourseList) {
		//TODO implement list from Zip
		return returnSuccess(HttpStatus.OK);
	}
}
