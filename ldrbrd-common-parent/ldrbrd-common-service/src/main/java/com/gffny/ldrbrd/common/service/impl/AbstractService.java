package com.gffny.ldrbrd.common.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.persistence.GenericDao;

public class AbstractService {
	
	/** The Constant log. */
	static final Logger LOG = LoggerFactory.getLogger(AbstractService.class);

	/**
	 * Utility method to get the non-collection result of a named query (if there is intended to be only one result)
	 * 
	 * @param genericDao
	 * @param namedQuery
	 * @param params
	 * @return
	 */
	protected <T> T namedQuerySingleResultOrNull(GenericDao<T> genericDao, String namedQuery, Map<String, Object> params) {
		
		//get the result list
		try {
			List<T> resultList = genericDao.findByNamedQuery(namedQuery, params);
			//check the result list validity
			if(resultList != null && resultList.size() == 1) {
				//if there's only one, return it
				return resultList.get(0);
			}
			//else kick up a fuss and return null
		} catch (DataAccessException e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

}