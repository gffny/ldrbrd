package com.gffny.ldrbrd.common.service;

import java.util.List;
import java.util.Map;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Club;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.CourseHole;

public interface ICourseClubService {

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public abstract Course createCourse(String courseName, Club club,
			TeeColour teeColour, Double slopeIndex, Integer par,
			String courseImageReference) throws ServiceException;

	/**
	 * 
	 * @param clubName
	 * @return
	 */
	public abstract Club createClub(String clubName) throws ServiceException;

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Club> getClubList() throws ServiceException;

	/**
	 * 
	 * @param courseName
	 * @param teeColour
	 * @return
	 * @throws ServiceException
	 */
	public abstract Course getCourseByNameAndTeeColour(String courseName,
			TeeColour teeColour) throws ServiceException;

	/**
	 * 
	 * @param id
	 */
	public abstract Course getCourseById(String id) throws ServiceException;

	/**
	 * 
	 * @param golferId
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Course> getFavouriteCourseList(String golferId)
			throws ServiceException;

	/**
	 * 
	 * @param golferId
	 * @param favouriteLimit
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Course> getFavouriteCourseList(String golferId,
			int favouriteLimit) throws ServiceException;

	/**
	 * 
	 * @param courseHole
	 */
	public abstract void saveOrUpdateHole(CourseHole courseHole)
			throws ServiceException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	public abstract Map<Integer, CourseHole> getHoleListByCourseId(String id)
			throws ServiceException;

	/**
	 * 
	 * @param id
	 * @param nineHole
	 * @return
	 */
	public abstract Map<Integer, CourseHole> getHoleListByCourseId(String id,
			boolean isNineHole) throws ServiceException;

}