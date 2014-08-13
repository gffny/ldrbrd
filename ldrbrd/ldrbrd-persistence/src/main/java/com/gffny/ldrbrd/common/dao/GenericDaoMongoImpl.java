/**
 * 
 */
package com.gffny.ldrbrd.common.dao;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.CommonUUIDEntity;
import com.gffny.ldrbrd.common.model.Constant;
import com.mongodb.MongoClient;

/**
 * @author John D. Gaffney | gffny.com
 */
public class GenericDaoMongoImpl<T extends CommonUUIDEntity> implements GenericNoSqlDao<T> {

	/** */
	private MongoClient mongoClient;

	/** */
	private Morphia morphia;

	/** */
	private Datastore datastore;

	/** */
	private Logger LOG = LoggerFactory.getLogger(GenericDaoMongoImpl.class);

	/** */
	public GenericDaoMongoImpl() {
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

}
