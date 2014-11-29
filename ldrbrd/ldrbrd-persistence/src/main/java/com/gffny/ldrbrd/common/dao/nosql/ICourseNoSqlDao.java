package com.gffny.ldrbrd.common.dao.nosql;

import java.util.List;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.nosql.Course;

public interface ICourseNoSqlDao extends
		GenericNoSqlDao<com.gffny.ldrbrd.common.model.nosql.Course> {

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