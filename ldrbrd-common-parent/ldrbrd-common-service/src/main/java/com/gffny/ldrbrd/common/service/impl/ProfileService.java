/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.model.impl.GolferClubDetail;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.security.enums.AuthenticationResult;
import com.gffny.ldrbrd.common.security.token.AuthenticationToken;
import com.gffny.ldrbrd.common.security.token.impl.EmailPasswordToken;
import com.gffny.ldrbrd.common.service.IGolfClubService;
import com.gffny.ldrbrd.common.service.IUserProfileService;

/**
 * 
 * @author jdgaffney
 * 
 */
@Service
public class ProfileService extends AbstractService implements
		IUserProfileService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(ProfileService.class);

	/**
	 * 
	 */
	@Autowired
	private GenericDao<GolferProfile> personDao;

	/**
	 * 
	 */
	@Autowired
	private IGolfClubService golfClubService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.impl.IPersonService#addPerson(com.gffny
	 * .ldrbrd.common.model.impl.GolferProfile)
	 */
	public void addGolferProfile(GolferProfile golfer) throws ServiceException {
		if (golfer != null) {
			LOG.debug("persisting golfer: " + golfer.toString());
			try {
				personDao.persist(golfer);
			} catch (DataAccessException e) {
				LOG.error(e.getMessage());
				throw new ServiceException(e);
			}
		} else {
			LOG.error("golfer is null");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.impl.IPersonService#fetchAllPersons()
	 */
	public List<GolferProfile> fetchAllPersons() throws ServiceException {
		return new ArrayList<GolferProfile>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.impl.IPersonService#authenticateUser(
	 * com.gffny.ldrbrd.common.security.token.AuthenticationToken)
	 */
	public AuthenticationResult authenticateUser(AuthenticationToken authToken)
			throws ServiceException {
		// check if authToken is not null
		if (authToken != null) {
			// check token type
			// EmailPasswordToken
			if (authToken instanceof EmailPasswordToken) {
				try {
					GolferProfile golferProfile = personDao
							.findById(GolferProfile.class,
									authToken.getTokenIdentifier());
					if (golferProfile != null) {
						if (golferProfile.getPassword().equals(
								authToken.getTokenAuthenticator())) {
							// reset user login attempts
							return AuthenticationResult.SUCCESS;
						} else {
							// check the number of authentication attempts
							// increase the number of authentication attempts
							return AuthenticationResult.FAILED;
						}
					}
					// update user login attempts
					return AuthenticationResult.FAILED;
				} catch (DataAccessException daex) {
					return AuthenticationResult.ERROR;
				}
			}
		}
		return AuthenticationResult.ERROR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#
	 * createDefaultGolfBagForGolfer
	 * (com.gffny.ldrbrd.common.model.impl.GolferProfile)
	 */
	public void createDefaultGolfBagForGolfer(GolferProfile golfer)
			throws ServiceException {
		if (golfer != null) {
			try {
				GolferProfile updatedGolfer = personDao.findById(
						GolferProfile.class, golfer.getId());
				List<GolferClubDetail> defaultGolfBag = new ArrayList<GolferClubDetail>();
				for (GolfClub gc : golfClubService.getDefaultGolfClubList()) {
					defaultGolfBag.add(new GolferClubDetail(gc, 0, ""));
				}
				updatedGolfer.setGolfBag(defaultGolfBag);
				personDao.merge(updatedGolfer);
			} catch (DataAccessException daEx) {
				LOG.error("error creating default golf bag for golfer id: "
						+ golfer.getId() + ". Excpt: " + daEx.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IUserProfileService#getGolferWithBagByHandle
	 * (java.lang.String)
	 */
	public GolferProfile getGolferWithBagByHandle(String golferHandle)
			throws ServiceException {
		GolferProfile golferProfile = getGolferByHandle(golferHandle);
		Hibernate.initialize(golferProfile.getGolfBag());
		return golferProfile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IUserProfileService#getGolferByHandle
	 * (java.lang.String)
	 */
	public GolferProfile getGolferByHandle(String golferHandle)
			throws ServiceException {
		if (golferHandle != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("profileHandle", golferHandle);
			return namedQuerySingleResultOrNull(personDao,
					GolferProfile.FIND_BY_HANDLE, params);
		}
		LOG.error("error with parameters for method");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IUserProfileService#getGolferByEmail(
	 * java.lang.String)
	 */
	public GolferProfile getGolferByEmail(String golferEmail)
			throws ServiceException {
		if (golferEmail != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("emailAddress", golferEmail);
			return namedQuerySingleResultOrNull(personDao,
					GolferProfile.FIND_BY_EMAIL, params);
		}
		LOG.error("error with parameters for method");
		return null;
	}
}