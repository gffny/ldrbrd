/**
 * 
 */
package com.gffny.ldrbrd.common.dao.mongo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.dao.GenericNoSqlDaoMongoImpl;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.mongo.Club;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;
import com.gffny.ldrbrd.common.utils.StringUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Repository
public class ClubMongoDaoImpl extends GenericNoSqlDaoMongoImpl<Club> {

	/**
	 * @param clubId
	 * @return
	 * @throws PersistenceException
	 */
	public List<Course> listCourseByClub(String clubId) throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(clubId)) {
			return this.datastore.createQuery(Course.class).field("club.objectId")
					.equal(new ObjectId(clubId)).asList();
		}
		throw new PersistenceException("clubId cannot be null or empty");
	}

	/**
	 * @param string
	 * @return
	 */
	public List<Course> listCourseByCity(String city) throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(city)) {
			return this.datastore.createQuery(Course.class).field("club.city")
					.equal(new ObjectId(city)).asList();
		}
		throw new PersistenceException("city cannot be null or empty");
	}

	/**
	 * @param lat
	 * @param lon
	 * @return
	 * @throws PersistenceException
	 */
	public List<Course> listCourseByLocation(String lat, String lon) throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(lat) && StringUtils.isNotEmpty(lon)) {
			// return this.datastore.createQuery(Course.class).field("club.city").equal(new
			// ObjectId(city)).asList();
		}
		throw new PersistenceException("city cannot be null or empty");
	}

}
