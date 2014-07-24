/**
 * 
 */
package com.gffny.ldrbrd.common.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.CommonEntity;

/**
 * @author John D. Gaffney | gffny.com
 */
public interface GenericDao<T> {

	/**
	 * @param entity
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T> T persist(T entity) throws PersistenceException;

	/**
	 * @param entity
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T> T merge(T entity) throws PersistenceException;

	/**
	 * @param clazz
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T> T findById(Class<T> clazz, String id) throws PersistenceException;

	/**
	 * @param clazz
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	@SuppressWarnings("hiding")
	public <T> T findById(Class<T> clazz, int id) throws PersistenceException;

	/**
	 * Find by using a named query.
	 * 
	 * @param name
	 *            named query name
	 * @param parameters
	 *            map of query key-value pairs parameters. Pass empty map if no params
	 * @return
	 */
	List<T> findByNamedQuery(String name, Map<String, ?> parameters) throws PersistenceException;

	/**
	 * Find by named query, limit the result
	 * 
	 * @param name
	 * @param parameters
	 * @param maxResults
	 * @return
	 */
	List<T> findByNamedQuery(String name, Map<String, ?> parameters, int maxResults)
			throws PersistenceException;

	/**
	 * 1 Find by using a query
	 * 
	 * @param query
	 *            query string
	 * @param parameters
	 *            map of query key-value pairs parameters. Pass empty map if no params
	 * @return
	 */
	List<T> findByQuery(String query, Map<String, ?> parameters) throws PersistenceException;

	/**
	 * Returns a single result. Caller would cast returned value as needed. For count use long, for
	 * avg double, etc...
	 * 
	 * @param namedQuery
	 * @param parameters
	 * @return single result
	 */
	Object findByAggregateNamedQuerySingleResult(String namedQuery, Map<String, ?> parameters)
			throws PersistenceException;

	/**
	 * Finds all of the rows associated with the class
	 * 
	 * @param clazz
	 * @return
	 */
	List<T> findAll(Class<? extends CommonEntity> clazz) throws PersistenceException;

	/**
	 * Update by using Named query
	 * 
	 * @param query
	 * @param parameters
	 * @return returns update count - total number of rows updated
	 */
	int updateByNamedQuery(String query, Map<String, ?> parameters) throws PersistenceException;

	/**
	 * Update using query and parameters
	 * 
	 * @param query
	 * @param parameters
	 * @return returns update count
	 */
	int updateByQuery(String query, Map<String, ?> parameters) throws PersistenceException;

	/**
	 * @return
	 */
	public EntityManager getEntityManager();

}
