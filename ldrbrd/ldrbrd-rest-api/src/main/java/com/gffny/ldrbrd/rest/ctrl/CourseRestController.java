/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.mongo.Club;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;
import com.gffny.ldrbrd.common.service.impl.CourseClubService;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
@Controller
public class CourseRestController extends BaseRestController {

	/** */
	@Autowired
	private CourseClubService courseClubService;

	/**
	 * @param clas
	 */
	public CourseRestController() {
		super(CourseRestController.class);
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/club/id/{clubId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Club> clubById(@PathVariable String clubId) {

		try {
			return new ResponseEntity<Club>(courseClubService.clubById(clubId), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<Club>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/club/listclub", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Club>> listclub() {

		try {
			return new ResponseEntity<List<Club>>(courseClubService.listClub(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<List<Club>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/course/id/{courseId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Course> courseById(@PathVariable String courseId) {

		try {
			return new ResponseEntity<Course>(courseClubService.courseById(courseId), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/course/listbycity/{city}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Course>> courseByCity(@PathVariable String city) {

		try {
			return new ResponseEntity<List<Course>>(courseClubService.listCourseByCity(city),
					HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<List<Course>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/course/listfavouritebygolfer/{golferId}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Course>> courseByGolfer(@PathVariable String golferId) {

		try {
			return new ResponseEntity<List<Course>>(
					courseClubService.listCourseByGolferFavourite(golferId), HttpStatus.OK);
		} catch (AuthorisationException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<List<Course>>(HttpStatus.UNAUTHORIZED);
		} catch (ServiceException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<List<Course>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/course/testlist", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Course>> testlist() {

		try {
			return new ResponseEntity<List<Course>>(courseClubService.testList(), HttpStatus.OK);
		} catch (AuthorisationException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<List<Course>>(HttpStatus.UNAUTHORIZED);
		} catch (ServiceException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<List<Course>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
