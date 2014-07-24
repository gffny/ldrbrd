/**
 * 
 */
package com.gffny.ldrbrd.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.CommonEntity;
import com.gffny.ldrbrd.common.utils.ClassUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Repository
@Transactional
public class GenericDaoJpaImpl<T extends Serializable> implements GenericDao<T> {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(GenericDaoJpaImpl.class);

	/** The em. */
	@PersistenceContext(unitName = "ldrbrd_pu")
	private EntityManager em;

	private Class<T> type;

	/**
	 * Instantiates a new generic dao jpa impl.
	 */
	@SuppressWarnings("unchecked")
	public GenericDaoJpaImpl() {
		// Dynamically set the type of generic class
		this.setType((Class<T>) ClassUtils.getTypeArguments(GenericDaoJpaImpl.class, getClass())
				.get(0));
	}

	/**
	 * @return
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(Class<T> type) {
		this.type = type;
	}

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
	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * 
	 */
	@SuppressWarnings("hiding")
	public <T> T persist(T entity) throws PersistenceException {
		this.em.persist(entity);
		LOG.debug("success");
		return entity;
	}

	/**
	 * 
	 */
	@SuppressWarnings("hiding")
	public <T> T merge(T entity) throws PersistenceException {
		return this.em.merge(entity);
	}

	/**
	 * 
	 */
	@SuppressWarnings("hiding")
	public <T> T findById(Class<T> clazz, String id) throws PersistenceException {
		return this.em.find(clazz, id);
	}

	/**
	 * 
	 */
	@SuppressWarnings("hiding")
	public <T> T findById(Class<T> clazz, int id) throws PersistenceException {
		return this.em.find(clazz, Integer.valueOf(id));
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.GenericDao#findByNamedQuery(java.lang.String, java.util.Map)
	 */
	public List<T> findByNamedQuery(String name, Map<String, ?> params) {
		return this.findByNamedQuery(name, params, 1);
	}

	/**
	 * 
	 */
	public List<T> findByQuery(String query, Map<String, ?> parameters) {
		// TODO implement findByQuery
		return null;
	}

	/**
	 * Find by named query.
	 * 
	 * @param name
	 *            the name
	 * @param params
	 *            the params
	 * @param maxResults
	 *            the max results
	 * @return the list
	 */
	public List<T> findByNamedQuery(final String name, final Map<String, ?> params,
			final int maxResults) {
		// create the query
		final Query query = getEntityManager().createNamedQuery(name);

		// set the result size
		if (maxResults > -1) {
			query.setMaxResults(maxResults);
		}

		// check for null params
		if (params != null) {
			for (final Map.Entry<String, ?> param : params.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}

		@SuppressWarnings("unchecked")
		final List<T> results = query.getResultList();

		return results;
	}

	/**
	 * 
	 */
	public Object findByAggregateNamedQuerySingleResult(String namedQuery, Map<String, ?> parameters) {
		// TODO implement findByAggregateNamedQuery....
		return null;
	}

	/**
	 * 
	 */
	public int updateByNamedQuery(String query, Map<String, ?> parameters) {
		// TODO implement updateByNamedQuery
		return 0;
	}

	/**
	 * 
	 */
	public int updateByQuery(String query, Map<String, ?> parameters) {
		// TODO implement updateByQuery
		return 0;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<? extends CommonEntity> clazz) {
		Query query = this.getEntityManager().createQuery("from " + clazz.getName(), clazz);
		return query.getResultList();
	}

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
	@SuppressWarnings("unused")
	private Session getSession() {
		return (Session) getEntityManager().getDelegate();
	}
}