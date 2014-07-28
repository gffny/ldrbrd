/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Club;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.CourseHole;
import com.gffny.ldrbrd.common.service.ICourseClubService;

/**
 * @author John D. Gaffney | gffny.com
 */
@Service
public class CourseClubService extends AbstractService implements ICourseClubService {

	/** */
	private static final int EIGHTTEEN_HOLE = 18;

	/** */
	private static final int NINE_HOLE = 9;

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(CourseClubService.class);

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.ICourseClubService#getClubList()
	 */
	public List<Club> getClubList() throws ServiceException {
		throw new ServiceException();
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICourseClubService#getCourseById(java .lang.String)
	 */
	public Course getCourseById(final String courseId) throws ServiceException {

		LOG.debug("getting course with id: {}", courseId);
		Course course = new Course();
		course.setClub(new Club());
		course.setId("abcd-1234-efgh-5678");
		course.setPar(72);
		course.setCourseName("test course");
		course.setTeeColour(TeeColour.BLUE);
		course.setSlopeIndex(123.2);
		List<CourseHole> courseHoleList = new ArrayList<CourseHole>();
		for (int i = 1; i <= EIGHTTEEN_HOLE; i++) {
			CourseHole courseHole = new CourseHole();
			courseHole.setHoleNumber(i);
			courseHole.setHoleDistance(300 + (i * 10));
			courseHole.setHoleDescription("Hole number: " + i);
			courseHole.setName("Hole number: " + i);
			courseHoleList.add(courseHole);
		}
		course.setCourseHoleList(courseHoleList);
		return course;
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
