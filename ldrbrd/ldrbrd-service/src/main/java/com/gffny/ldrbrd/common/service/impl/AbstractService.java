package com.gffny.ldrbrd.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.service.IAuthorisationService;

public class AbstractService {

	/** The Constant log. */
	static final Logger LOG = LoggerFactory.getLogger(AbstractService.class);

	/** */
	@Autowired
	protected IAuthorisationService authorisationService;

	/**
	 * Utility method to get the non-collection result of a named query (if
	 * there is intended to be only one result)
	 * 
	 * @param genericDao
	 * @param namedQuery
	 * @param params
	 * @return
	 */
	protected <T> T namedQuerySingleResultOrNull(GenericDao<T> genericDao,
			String namedQuery, Map<String, Object> params)
			throws ServiceException {

		// get the result list
		try {
			List<T> resultList = genericDao
					.findByNamedQuery(namedQuery, params);
			// check the result list validity
			if (resultList != null && resultList.size() == 1) {
				// if there's only one, return it
				return resultList.get(0);
			}
			// else kick up a fuss and return null
		} catch (PersistenceException e) {
			LOG.error(e.getMessage());
			throw new ServiceException(e);
		}
		return null;
	}

	/**
	 * 
	 * @param genericDao
	 * @param namedQuery
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	protected <T> List<T> namedQueryListResultOrNull(GenericDao<T> genericDao,
			String namedQuery, Map<String, Object> params)
			throws ServiceException {

		// get the result list
		try {
			return genericDao.findByNamedQuery(namedQuery, params);
		} catch (PersistenceException e) {
			LOG.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @param genericDao
	 * @param namedQuery
	 * @param params
	 * @param maxResults
	 * @return
	 * @throws ServiceException
	 */
	protected <T> List<T> namedQueryListResultOrNull(GenericDao<T> genericDao,
			String namedQuery, Map<String, Object> params, int maxResults)
			throws ServiceException {
		// get the result list
		try {
			return genericDao.findByNamedQuery(namedQuery, params, maxResults);
		} catch (PersistenceException e) {
			LOG.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * @param keyParamPairs
	 * @return
	 */
	protected Map<String, Object> populateParamMap(Object... keyParamPairs) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		// check param validity
		if (ArrayUtils.isNotEmpty(keyParamPairs)
				&& keyParamPairs.length % 2 == 0) {
			LOG.debug("populating param map with pairs {}",
					StringUtils.arrayToCommaDelimitedString(keyParamPairs));
			// loop through the pairs
			for (int i = 0; i < keyParamPairs.length; i += 2) {
				if (keyParamPairs[i] instanceof String) {
					String key = (String) keyParamPairs[i];
					paramMap.put(key, keyParamPairs[i + 1]);
				}
			}
		}
		return paramMap;
	}

}