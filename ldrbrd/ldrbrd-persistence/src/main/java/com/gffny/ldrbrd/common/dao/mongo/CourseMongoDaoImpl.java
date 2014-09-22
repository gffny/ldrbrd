/**
 * 
 */
package com.gffny.ldrbrd.common.dao.mongo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.dao.GenericNoSqlDaoMongoImpl;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;
import com.gffny.ldrbrd.common.utils.StringUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Repository
public class CourseMongoDaoImpl extends GenericNoSqlDaoMongoImpl<Course> implements
		GenericNoSqlDaoCourse {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.gffny.ldrbrd.common.dao.mongo.GenericNoSqlDaoCourse#listCourseByClub(java.lang.String)
	 */
	@Override
	public List<Course> listCourseByClub(String clubId) throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(clubId)) {
			return this.datastore.createQuery(Course.class).field("club.objectId")
					.equal(new ObjectId(clubId)).asList();
		}
		throw new PersistenceException("clubId cannot be null or empty");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.gffny.ldrbrd.common.dao.mongo.GenericNoSqlDaoCourse#listCourseByCity(java.lang.String)
	 */
	@Override
	public List<Course> listCourseByCity(String city) throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(city)) {
			return this.datastore.createQuery(Course.class).field("club.city")
					.equal(new ObjectId(city)).asList();
		}
		throw new PersistenceException("city cannot be null or empty");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.gffny.ldrbrd.common.dao.mongo.GenericNoSqlDaoCourse#listCourseByLocation(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public List<Course> listCourseByLocation(String lat, String lon) throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(lat) && StringUtils.isNotEmpty(lon)) {
			// return this.datastore.createQuery(Course.class).field("club.city").equal(new
			// ObjectId(city)).asList();
		}
		throw new PersistenceException("city cannot be null or empty");
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.dao.mongo.GenericNoSqlDaoCourse#testList()
	 */
	@Override
	public List<Course> testList() throws PersistenceException {
		return this.datastore.createQuery(Course.class).field("name")
				.containsIgnoreCase("butter brook").asList();
	}

}
