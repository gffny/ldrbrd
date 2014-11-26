/**
 * 
 */
package com.gffny.ldrbrd.common.dao.nosql;

import java.net.UnknownHostException;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.dao.GenericNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.CommonUUIDEntity;
import com.gffny.ldrbrd.common.model.Constant;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * @author John D. Gaffney | gffny.com
 */
@Repository
public class GenericNoSqlDaoMongoImpl<T extends CommonUUIDEntity> implements
		GenericNoSqlDao<T> {

	/** */
	private MongoClient mongoClient;

	/** */
	private Morphia morphia;

	/** */
	protected Datastore datastore;

	/** */
	protected Logger LOG = LoggerFactory
			.getLogger(GenericNoSqlDaoMongoImpl.class);

	/** */
	public GenericNoSqlDaoMongoImpl() {
		try {
			// TODO make the mongo connection configurable (almost beanish)
			mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
			// mongoClient = new MongoClient(new
			// ServerAddress("ds047050.mongolab.com", 47050),
			// Arrays.asList(MongoCredential.createMongoCRCredential("ldrbrd",
			// "ldrbrd",
			// "ldrbrd".toCharArray())));
			morphia = new Morphia();
			morphia.mapPackage(Constant.MONGO_MAP_PACKAGE);

			datastore = morphia.createDatastore(mongoClient,
					Constant.MONGO_DB_NAME);

		} catch (UnknownHostException e) {
			LOG.error(
					"unable to create MongoClient connection; unknown host: {}",
					e.getMessage());
		}
	}

	/**
	 * @param entity
	 * @return
	 * @throws PersistenceException
	 */
	@Override
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> String persist(T entity)
			throws PersistenceException {
		Key<T> key = datastore.save(entity);
		return String.valueOf(key.getId());
	}

	/**
	 * @param name
	 * @return
	 * @throws PersistenceException
	 */
	@Override
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
	@Override
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> T findByName(Class<T> clazz, String name) {

		return datastore.find(clazz).field("name").equal(name).get();

	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> List<T> find(Class<T> clazz)
			throws PersistenceException {

		return datastore.find(clazz).asList();
	}
}
