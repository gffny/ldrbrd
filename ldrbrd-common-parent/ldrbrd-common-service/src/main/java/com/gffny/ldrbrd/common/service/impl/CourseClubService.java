/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Club;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.CourseHole;
import com.gffny.ldrbrd.common.model.mapping.FavouriteCourse;
import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.common.utils.StringUtils;

/**
 * @author jdgaffney
 * 
 */
@Service
public class CourseClubService implements ICourseClubService {

	private static final int EIGHTTEEN_HOLE = 18;

	private static final int NINE_HOLE = 9;

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(CourseClubService.class);

	/**
	 * 
	 */
	@Autowired
	private GenericDao<Club> clubDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<Course> courseDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<CourseHole> courseHoleDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<FavouriteCourse> favouriteCourseDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.impl.ICourseClubService#getClubList()
	 */
	public List<Club> getClubList() {
		try {
			return clubDao.findAll(Club.class);
		} catch (DataAccessException daex) {
			// TODO fix this
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ICourseClubService#getCourseById(java
	 * .lang.String)
	 */
	public Course getCourseById(final String courseId) {
		LOG.debug("getting course with id: " + courseId);
		Course result = null;
		try {
			result = courseDao.findById(Course.class, courseId);
		} catch (DataAccessException e) {
			LOG.error(e.getMessage());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.impl.ICourseClubService#
	 * getCourseByNameAndTeeColour(java.lang.String,
	 * com.gffny.ldrbrd.common.model.enums.TeeColour)
	 */
	public Course getCourseByNameAndTeeColour(String courseName,
			TeeColour teeColour) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("courseName", courseName);
		params.put("teeColour", teeColour);
		try {
			// TODO use the super.namedQueryResultOrNullMethod
			List<Course> courseList = courseDao.findByNamedQuery(
					Course.FIND_BY_NAME_AND_TEE_COLOUR, params);

			if (courseList != null) {
				return courseList.get(0);
			} else {

				// TODO fix this
				return null;
			}
		} catch (DataAccessException daex) {

			// TODO fix this
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ICourseClubService#getFavouriteCourseList
	 * (java.lang.String)
	 */
	public List<Course> getFavouriteCourseList(String golferId)
			throws ServiceException {
		if (StringUtils.isNotEmpty(golferId)) {
			return getFavouriteCourseList(golferId, 0);
		}
		LOG.error("golfer id is empty");
		return new ArrayList<Course>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ICourseClubService#getFavouriteCourseList
	 * (java.lang.String, int)
	 */
	public List<Course> getFavouriteCourseList(String golferId,
			int favouriteLimit) throws ServiceException {

		// TODO refactor to remove the two stage result getting (i.e. from
		// List<FC> to List<C>)
		List<Course> favouriteList = new ArrayList<Course>();
		if (StringUtils.isNotEmpty(golferId)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("golferId", golferId);
			try {
				// get list of favourite courses from database
				List<FavouriteCourse> result;
				if (favouriteLimit > 0) {
					result = favouriteCourseDao.findByNamedQuery(
							FavouriteCourse.FAVOURITE_LIST_BY_GOLFER_ID,
							params, favouriteLimit);
				} else {
					result = favouriteCourseDao
							.findByNamedQuery(
									FavouriteCourse.FAVOURITE_LIST_BY_GOLFER_ID,
									params);
				}
				// parse list and build result set
				if (result != null && result.size() > 0) {
					for (FavouriteCourse course : result) {
						favouriteList.add(course.getCourse());
					}
				}
			} catch (DataAccessException e) {
				LOG.error(e.getMessage());
				throw new ServiceException(e);
			}
			return favouriteList;
		}
		LOG.error("golfer id is empty or favourite limit is less than one");
		return new ArrayList<Course>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ICourseClubService#getHoleListByCourseId
	 * (java.lang.String)
	 */
	public Map<Integer, CourseHole> getHoleListByCourseId(String courseId) {
		return getHoleListByCourseId(courseId, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ICourseClubService#getHoleListByCourseId
	 * (java.lang.String, boolean)
	 */
	public Map<Integer, CourseHole> getHoleListByCourseId(String courseId,
			boolean isNineHole) {
		Map<Integer, CourseHole> courseDetailMap = new HashMap<Integer, CourseHole>();
		if (courseId != null) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("courseId", courseId);
			try {
				LOG.debug(
						"querying course hole list for course id: {} from dao",
						courseId);
				List<CourseHole> courseHoleList = courseHoleDao
						.findByNamedQuery(CourseHole.FIND_BY_COURSE_ID, params,
						// limit the result set based on isNineHole
								(isNineHole ? NINE_HOLE : EIGHTTEEN_HOLE));
				// iterate over the list and build map with hole number as key
				for (CourseHole hole : courseHoleList) {
					courseDetailMap.put(hole.getHoleNumber() - 1, hole);
				}
			} catch (DataAccessException e) {
				LOG.error("no course holes returned for course id {}", courseId);
			}
		}
		return courseDetailMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.impl.ICourseClubService#createCourse(
	 * java.lang.String, com.gffny.ldrbrd.common.model.impl.Club,
	 * com.gffny.ldrbrd.common.model.enums.TeeColour, java.lang.Double,
	 * java.lang.Integer, java.lang.String)
	 */
	public Course createCourse(String courseName, Club club,
			TeeColour teeColour, Double slopeIndex, Integer par,
			String courseImageReference) {
		Course result = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("clubId", club.getId());
			params.put("courseName", courseName);
			// TODO use the super.namedQueryResultOrNullMethod
			List<Course> courseList = courseDao.findByNamedQuery(
					Course.FIND_BY_CLUB_ID_AND_COURSE_NAME, params);
			if (courseList != null && courseList.size() > 0
					&& courseList.get(0) != null) {
				result = courseList.get(0);
			} else {
				result = Course.createCourse(courseName, club, teeColour,
						slopeIndex, par, courseImageReference);
				return courseDao.persist(result);
			}
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.impl.ICourseClubService#createClub(java
	 * .lang.String)
	 */
	public Club createClub(final String clubName) {
		Club result = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("clubName", clubName);
			// TODO use the super.namedQueryResultOrNullMethod
			List<Club> clubList = clubDao.findByNamedQuery(
					Club.FIND_CLUB_BY_CLUB_NAME, params);
			if (clubList != null && clubList.size() > 0
					&& clubList.get(0) != null) {
				result = clubList.get(0);
			} else {
				result = Club.createClub(clubName);
				return clubDao.persist(result);
			}
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ICourseClubService#saveOrUpdateHole(com
	 * .gffny.ldrbrd.common.model.impl.CourseHole)
	 */
	public void saveOrUpdateHole(final CourseHole courseHole) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("courseId", courseHole.getCourse().getId());
		params.put("holeNumber", Integer.valueOf(courseHole.getHoleNumber()));
		List<CourseHole> existingCourseList = null;
		try {
			// TODO use the super.namedQueryResultOrNullMethod
			existingCourseList = courseHoleDao.findByNamedQuery(
					CourseHole.FIND_BY_COURSE_ID_AND_HOLE_NUMBER, params);
			if (null != existingCourseList && 0 < existingCourseList.size()
					&& null != existingCourseList.get(0)) {
				// update
				LOG.error("NOT IMPLEMENTED ****************");
			} else if (null == existingCourseList
					|| 0 == existingCourseList.size()
					|| null == existingCourseList.get(0)) {
				// persist
				LOG.debug("persisting hole " + courseHole.getName());
				courseHole.getCourse().getId().length();
				courseDao.persist(courseHole);
			}
		} catch (DataAccessException e) {
			LOG.error(e.getMessage());
		}
	}
}
