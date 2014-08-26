/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.service.IUserProfileService;
import com.gffny.ldrbrd.common.utils.QueryUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Service
public class ProfileService extends AbstractService implements IUserProfileService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(ProfileService.class);

	/** */
	@Autowired
	private GenericDao<Golfer> personDao;

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#getGolferByHandle (java.lang.String)
	 */
	public Golfer getGolferByHandle(String golferHandle) throws ServiceException {
		if (golferHandle != null) {
			return namedQuerySingleResultOrNull(personDao, Golfer.FIND_BY_HANDLE,
					QueryUtils.paramMap("profileHandle", golferHandle));
		}
		LOG.error("error with parameters for method");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#getGolferByEmail( java.lang.String)
	 */
	public Golfer getGolferByEmail(String golferEmail) throws ServiceException {
		if (golferEmail != null) {
			return namedQuerySingleResultOrNull(personDao, Golfer.FIND_BY_EMAIL,
					QueryUtils.paramMap("emailAddress", golferEmail));
		}
		LOG.error("error with parameters for method");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#getGolferById(int)
	 */
	public Golfer getGolferById(int id) throws ServiceException {
		// check param validity
		if (id > 0) {
			try {
				return personDao.findById(Golfer.class, id);
			} catch (PersistenceException e) {
				LOG.error(e.getMessage());
				throw new ServiceException(e);
			}
		}
		throw new ServiceException("invalid parameters for findGolferById. Golfer Id: " + id);
	}
}