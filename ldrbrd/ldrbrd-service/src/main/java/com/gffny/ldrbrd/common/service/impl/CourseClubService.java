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
	private GenericNoSqlDao<Club> clubDao;

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
	 * @see com.gffny.ldrbrd.common.service.ICourseClubService#getFavouriteCourseList
	 * (java.lang.String)
	 */
	public List<Course> getFavouriteCourseList(String golferId) throws ServiceException {
		throw new ServiceException();
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICourseClubService#getFavouriteCourseList
	 * (java.lang.String, int)
	 */
	public List<Course> getFavouriteCourseList(String golferId, int favouriteLimit)
			throws ServiceException {
		throw new ServiceException();
	}
}
