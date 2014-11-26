package com.gffny.ldrbrd.common.dao;

import java.util.List;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;

public interface ICourseNoSqlDao extends GenericNoSqlDao<Course> {

	/**
	 * @param clubId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<Course> listCourseByClub(String clubId)
			throws PersistenceException;

	/**
	 * @param string
	 * @return
	 */
	public abstract List<Course> listCourseByCity(String city)
			throws PersistenceException;

	/**
	 * @param lat
	 * @param lon
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<Course> listCourseByLocation(String lat, String lon)
			throws PersistenceException;

	/**
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<Course> testList() throws PersistenceException;

}