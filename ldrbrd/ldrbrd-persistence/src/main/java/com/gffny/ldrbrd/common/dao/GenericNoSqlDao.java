/**
 * 
 */
package com.gffny.ldrbrd.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 */
@Repository
public interface GenericNoSqlDao<T extends CommonUUIDEntity> {

	/**
	 * @param entity
	 * @return new object key
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> String persist(T entity) throws PersistenceException;

	/**
	 * @param entity
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> String merge(T entity) throws PersistenceException;

	/**
	 * @param entity
	 * @return updated object key
	 * @throws PersistenceException
	 */
	// @SuppressWarnings("hiding")
	// public <T extends CommonUUIDEntity> String merge(T entity) throws PersistenceException;

	/**
	 * @param clazz
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> T findById(Class<T> clazz, String id)
			throws PersistenceException;

	/**
	 * @param clazz
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> List<T> find(Class<T> clazz) throws PersistenceException;

	/**
	 * @param clazz
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	// @SuppressWarnings("hiding")
	// <T extends CommonUUIDEntity> T findById(Class<T> clazz, int id) throws PersistenceException;

	/**
	 * Find by using a query
	 * 
	 * @param query
	 *            query string
	 * @param parameters
	 *            map of query key-value pairs parameters. Pass empty map if no params
	 * @return
	 */
	// <T extends CommonUUIDEntity> List<T> findByQuery(String query, Map parameters)
	// throws PersistenceException;

	/**
	 * Finds all of the rows associated with the class
	 * 
	 * @param clazz
	 * @return
	 */
	// public <T extends CommonUUIDEntity> List<T> findAll(Class<T> clazz) throws
	// PersistenceException;

	/**
	 * @param clazz
	 * @param name
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T extends CommonUUIDEntity> T findByName(Class<T> clazz, String name)
			throws PersistenceException;
}
