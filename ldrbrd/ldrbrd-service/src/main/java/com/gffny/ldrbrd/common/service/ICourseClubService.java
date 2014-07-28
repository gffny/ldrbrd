package com.gffny.ldrbrd.common.service;

import java.util.List;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Club;
import com.gffny.ldrbrd.common.model.impl.Course;

public interface ICourseClubService {

	/**
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Club> getClubList() throws ServiceException;

	/**
	 * @param id
	 */
	public abstract Course getCourseById(String id) throws ServiceException;

	/**
	 * @param golferId
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Course> getFavouriteCourseList(String golferId) throws ServiceException;

	/**
	 * @param golferId
	 * @param favouriteLimit
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Course> getFavouriteCourseList(String golferId, int favouriteLimit)
			throws ServiceException;

}