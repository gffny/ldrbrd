/**
 * 
 */
package com.gffny.ldrbrd.common.dao;

import java.net.UnknownHostException;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.CommonUUIDEntity;
import com.gffny.ldrbrd.common.model.Constant;
import com.mongodb.MongoClient;

/**
 * @author John D. Gaffney | gffny.com
 */
@Repository
public class GenericNoSqlDaoMongoImpl<T extends CommonUUIDEntity> implements GenericNoSqlDao<T> {

	/** */
	private MongoClient mongoClient;

	/** */
	private Morphia morphia;

	/** */
	private Datastore datastore;

	/** */
	private Logger LOG = LoggerFactory.getLogger(GenericNoSqlDaoMongoImpl.class);

	/** */
	public GenericNoSqlDaoMongoImpl() {
		try {
			// TODO make the mongo connection configurable (almost beanish)
			mongoClient = new MongoClient("localhost", 27017);
			morphia = new Morphia();
			datastore = morphia.createDatastore(mongoClient, Constant.MONGO_DB_NAME);
			morphia.mapPackage(Constant.MONGO_MAP_PACKAGE);
		} catch (UnknownHostException e) {
			LOG.error("unable to create MongoClient connection; unknown host: {}", e.getMessage());
		}
	}

	/**
	 * @param entity
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> String persist(T entity) throws PersistenceException {
		Key<T> key = datastore.save(entity);
		return String.valueOf(key.getId());
	}

	/**
	 * @param name
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> T findById(Class<T> clazz, String id)
			throws PersistenceException {

		return datastore.get(clazz, new ObjectId(id));
	}

	/**
	 * @param clazz
	 * @param name
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> T findByName(Class<T> clazz, String name) {

		return datastore.find(clazz).field("name").equal(name).get();

	}

	/**
	 * 
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> List<T> find(Class<T> clazz) throws PersistenceException {

		return datastore.find(clazz).asList();
	}
}
