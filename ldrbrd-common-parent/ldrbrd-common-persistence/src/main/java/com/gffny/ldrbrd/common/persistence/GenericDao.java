/**
 * 
 */
package com.gffny.ldrbrd.common.persistence;

import java.util.List;

import com.gffny.ldrbrd.common.exception.DataAccessException;

/**
 * @author jdgaffney
 *
 */
public interface GenericDao<T> {

	@SuppressWarnings("hiding")
	public <T> T save(T entity) throws DataAccessException;
	
	@SuppressWarnings("hiding")
	public <T> T merge(T entity) throws DataAccessException;
	
	@SuppressWarnings("hiding")
	public <T> T find(Long id) throws DataAccessException;
	
	public List<T> findAll() throws DataAccessException;
	
}
