/**
 * 
 */
package com.gffny.ldrbrd.common.dao;

import java.util.List;
import java.util.Map;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.CommonEntity;

/**
 * @author jdgaffney
 * 
 */
public interface GenericDao<T> {

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("hiding")
	public <T> T persist(T entity) throws DataAccessException;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("hiding")
	public <T> T merge(T entity) throws DataAccessException;

	/**
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("hiding")
	public <T> T findById(Class<T> clazz, String id) throws DataAccessException;

	/**
	 * Find by using a named query.
	 * 
	 * @param name
	 *            named query name
	 * @param parameters
	 *            map of query key-value pairs parameters. Pass empty map if no
	 *            params
	 * @return
	 */
	List<T> findByNamedQuery(String name, Map<String, ?> parameters) throws DataAccessException;

	/**
	 * 
	 * Find by named query, limit the result
	 * 
	 * @param name
	 * @param parameters
	 * @param maxResults
	 * @return
	 */
	List<T> findByNamedQuery(String name, Map<String, ?> parameters, int maxResults) throws DataAccessException;

	/**
	 * 1 Find by using a query
	 * 
	 * @param query
	 *            query string
	 * @param parameters
	 *            map of query key-value pairs parameters. Pass empty map if no
	 *            params
	 * @return
	 */
	List<T> findByQuery(String query, Map<String, ?> parameters) throws DataAccessException;

	/**
	 * Returns a single result. Caller would cast returned value as needed. For
	 * count use long, for avg double, etc...
	 * 
	 * @param namedQuery
	 * @param parameters
	 * @return single result
	 */
	Object findByAggregateNamedQuerySingleResult(String namedQuery,	Map<String, ?> parameters) throws DataAccessException;

	/**
	 * Finds all of the rows associated with the class
	 * 
	 * @param clazz
	 * @return
	 */
	List<T> findAll(Class<? extends CommonEntity> clazz) throws DataAccessException;

	/**
	 * Update by using Named query
	 * 
	 * @param query
	 * @param parameters
	 * @return returns update count - total number of rows updated
	 */
	int updateByNamedQuery(String query, Map<String, ?> parameters) throws DataAccessException;

	/**
	 * Update using query and parameters
	 * 
	 * @param query
	 * @param parameters
	 * @return returns update count
	 */
	int updateByQuery(String query, Map<String, ?> parameters) throws DataAccessException;

}
