/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericNoSqlDao;
import com.gffny.ldrbrd.common.dao.mongo.ClubMongoDaoImpl;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.mongo.Club;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;
import com.gffny.ldrbrd.common.service.ICourseClubService;

/**
 * @author John D. Gaffney | gffny.com
 */
@Service
public class CourseClubService extends AbstractService implements ICourseClubService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(CourseClubService.class);

	/** */
	@Autowired
	private ClubMongoDaoImpl clubDao;

	/** */
	@Autowired
	private GenericNoSqlDao<Course> courseDao;

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.ICourseClubService#getClubList()
	 */
	public List<Club> getClubList() throws ServiceException {
		try {
			return clubDao.find(Club.class);
		} catch (PersistenceException e) {
			LOG.error("error retrieving club list from the datastore");
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICourseClubService#getCourseById(java .lang.String)
	 */
	public Course getCourseById(final String courseId) throws ServiceException {

		LOG.debug("getting course with id: {}", courseId);
		if (courseId != null) {
			try {
				return courseDao.findById(Course.class, courseId);
			} catch (PersistenceException e) {
				LOG.error("error retrieving course with id {} from datastore. Exception {}",
						courseId, e.getMessage());
				return null;
			}
		} else {
			LOG.error("course id was null");
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICourseClubService#listCourseByClub(java.lang.String)
	 */
	public List<Course> listCourseByClub(String clubId) throws ServiceException {
		LOG.debug("listCourseByClub id {}", clubId);
		try {
			return clubDao.listCourseByClub(clubId);
		} catch (PersistenceException e) {
			LOG.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICourseClubService#listCourseByCity(java.lang.String)
	 */
	public List<Course> listCourseByCity(String city) throws ServiceException {
		LOG.debug("listCourseByCity id {}", city);
		try {
			return clubDao.listCourseByCity(city);
		} catch (PersistenceException e) {
			LOG.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.gffny.ldrbrd.common.service.ICourseClubService#listCourseByLocation(java.lang.String,
	 * java.lang.String)
	 */
	public List<Course> listCourseByLocation(String lat, String lon) throws ServiceException {
		LOG.debug("listCourseByLocation: latitude {}, longtitude {}", lat, lon);
		try {
			return clubDao.listCourseByLocation(lat, lon);
		} catch (PersistenceException e) {
			LOG.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICourseClubService#getFavouriteCourseList
	 * (java.lang.String)
	 */
	public List<Course> listCourseByGolferFavourite(String golferId) throws ServiceException {
		LOG.debug("listCourseByGolferFavourite golferId {}", golferId);

		throw new ServiceException("listCourseByGolferFavourite is not yet implemented");
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICourseClubService#getFavouriteCourseList
	 * (java.lang.String, int)
	 */
	public List<Course> listCourseByGolferFavourite(String golferId, int favouriteLimit)
			throws ServiceException {
		LOG.debug("listCourseByGolferFavourite golferId {}, favouriteLimit", golferId,
				favouriteLimit);
		throw new ServiceException("listCourseByGolferFavourite is not yet implemented");
	}
}
