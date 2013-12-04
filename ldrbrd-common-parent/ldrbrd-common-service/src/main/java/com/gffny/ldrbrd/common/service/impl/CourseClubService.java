/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Club;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.persistence.GenericDao;

/**
 * @author jdgaffney
 * 
 */
@Service
public class CourseClubService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(CourseClubService.class);

	/**
	 * 
	 */
	@Autowired
	private GenericDao<Course> courseDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<Club> clubDao;

	/**
	 * 
	 * @return
	 */
	public Course createCourse(String courseName, Club club,
			TeeColour teeColour, Double slopeIndex, Integer par,
			String courseImageReference) {
		Course newCourse = Course.createCourse(courseName, club, teeColour,
				slopeIndex, par, courseImageReference);
		try {
			return courseDao.persist(newCourse);
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return newCourse;
	}

	/**
	 * 
	 * @param clubName
	 * @return
	 */
	public Club createClub(String clubName) {
		Club newClub = Club.createClub(clubName);
		try {
			return clubDao.persist(newClub);
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return newClub;
	}

	/**
	 * 
	 * @return
	 */
	public List<Club> getClubList() {
		return clubDao.findAll(Club.class);
	}

	/**
	 * 
	 * @param courseName
	 * @param teeColour
	 * @return
	 */
	public Course getCourseByNameAndTeeColour(String courseName,
			TeeColour teeColour) {
		Map<String, Object> params = new HashMap<String, Object>();// Collections.emptyMap();
		params.put("courseName", courseName);
		params.put("teeColour", teeColour);
		List<Course> courseList = courseDao.findByNamedQuery(
				Course.FIND_BY_NAME_AND_TEE_COLOUR, params);
		if (courseList != null) {
			return courseList.get(0);
		} else {
			return null;
		}
	}
}
