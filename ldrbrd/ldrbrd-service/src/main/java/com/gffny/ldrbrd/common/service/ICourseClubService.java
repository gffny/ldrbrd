package com.gffny.ldrbrd.common.service;

import java.util.List;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.mongo.Club;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;

public interface ICourseClubService {

	/**
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Club> getClubList() throws ServiceException;

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public abstract Course getCourseById(String id) throws ServiceException;

	/**
	 * @param clubId
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Course> listCourseByClub(String clubId) throws ServiceException;

	/**
	 * @param city
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Course> listCourseByCity(String city) throws ServiceException;

	/**
	 * @param lat
	 * @param lon
	 * @return
	 */
	public abstract List<Course> listCourseByLocation(String lat, String lon)
			throws ServiceException;

	/**
	 * @param golferId
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Course> listCourseByGolferFavourite(String golferId) throws ServiceException;

	/**
	 * @param golferId
	 * @param favouriteLimit
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Course> listCourseByGolferFavourite(String golferId, int favouriteLimit)
			throws ServiceException;

}