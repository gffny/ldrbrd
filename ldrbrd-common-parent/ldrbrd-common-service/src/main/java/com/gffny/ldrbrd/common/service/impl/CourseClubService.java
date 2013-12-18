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
import com.gffny.ldrbrd.common.service.ICourseClubService;

/**
 * @author jdgaffney
 * 
 */
@Service
public class CourseClubService implements ICourseClubService {

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

	/* (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.ICourseClubService#createCourse(java.lang.String, com.gffny.ldrbrd.common.model.impl.Club, com.gffny.ldrbrd.common.model.enums.TeeColour, java.lang.Double, java.lang.Integer, java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.ICourseClubService#createClub(java.lang.String)
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

	/* (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.ICourseClubService#getClubList()
	 */
	public List<Club> getClubList() {
		return clubDao.findAll(Club.class);
	}

	/* (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.ICourseClubService#getCourseByNameAndTeeColour(java.lang.String, com.gffny.ldrbrd.common.model.enums.TeeColour)
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
