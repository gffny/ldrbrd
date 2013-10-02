/**
 * 
 */
package com.gffny.ldrbrd.common.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.exception.DataAccessException;

/**
 * @author jdgaffney
 * 
 */
public class GenericDaoJpaImpl<T extends Serializable> implements GenericDao<T> {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(GenericDaoJpaImpl.class);

	/** The em. */
	@PersistenceContext
	private EntityManager em;

	/** The type. */

	// private Class<T> type;
	//
	// /**
	// *
	// */
	// public GenericDaoJpaImpl() {
	// // TODO Auto-generated constructor stub
	// }
	//
	// public GenericDaoJpaImpl(Class<T> clazz) {
	// type = clazz;
	// }

	// /**
	// * Sets the type.
	// *
	// * @param type
	// * the new type
	// */
	// protected void setType(final Class<T> type) {
	// this.type = type;
	// }

	/**
	 * Sets the entity manager.
	 * 
	 * @param entityManager
	 *            the new entity manager
	 */
	protected void setEntityManager(final EntityManager entityManager) {
		this.em = entityManager;
	}

	/**
	 * Gets the entity manager.
	 * 
	 * @return the entity manager
	 */
	protected EntityManager getEntityManager() {
		return em;
	}

	/**
	 * 
	 */
	@SuppressWarnings("hiding")
	@Transactional
	public <T> T persist(T entity) throws DataAccessException {
		em.persist(entity);
		LOG.debug("success");
		return entity;
	}

	/**
	 * 
	 */
	@SuppressWarnings("hiding")
	public <T> T merge(T entity) throws DataAccessException {
		return this.em.merge(entity);
	}

	/**
	 * 
	 */
	@SuppressWarnings("hiding")
	public <T> T find(Long id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public List<T> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
