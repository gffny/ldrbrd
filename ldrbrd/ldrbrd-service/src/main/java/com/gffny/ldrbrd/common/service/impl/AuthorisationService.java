/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.UserProfile;
import com.gffny.ldrbrd.common.service.IAuthorisationService;
import com.gffny.ldrbrd.security.token.LdrbrdAuthenticationToken;

/**
 * does not extend AbstractService because it is autowired in the
 * AbstractService
 * 
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 */
@Service
public class AuthorisationService implements IAuthorisationService {

	/** */
	private static Logger LOG = LoggerFactory
			.getLogger(AuthorisationService.class);

	/** */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<Golfer> golferDao;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.impl.IAuthorisationService#isPermitted
	 *      (java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isPermitted(String userId, String scorecardId) {
		// TODO handle authorisation

		// get the scorecard for id and check the following
		// non-competition scorecard
		// is the player
		// competition scorecard
		// is the scorer, the competition organiser, or organising society admin
		// society scorecard
		// is the player, or organising society admin
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws ServiceException
	 * @see com.gffny.ldrbrd.common.service.IAuthorisationService#authorise(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public String authorise(String profileCharacteristic)
			throws AuthorisationException, ServiceException {
		// check params
		if (StringUtils.isEmpty(profileCharacteristic)) {
			// if the profile id is null or empty return the users own profile
			// id
			UserProfile user = getLoggedInUser();
			if (user != null && hasLoggedInUserPrivilege(Constant.ROLE_GOLFER)) {
				return String.valueOf(user.getId());
			}
			throw new ServiceException(
					"error with user return from security context or the user has not got the ROLE_GOLFER priviledge");
		} else {
			// check if the logged in user is authorise to see this profile
			if (hasLoggedInUserPrivilege("ROLE_ADMIN")) {
				LOG.debug("user has admin privilidges");
				return profileCharacteristic;
			} else if (profileCharacteristic.equalsIgnoreCase(String
					.valueOf(getLoggedInUser().getId()))
					|| profileCharacteristic.equalsIgnoreCase(getLoggedInUser()
							.getProfileHandle())
					|| profileCharacteristic.equalsIgnoreCase(getLoggedInUser()
							.getEmailAddress())) {
				LOG.debug("user is operating on their own account");
				return profileCharacteristic;
			}
			LOG.error("logged in user is not an admin or is not operating on their own account");
			throw new AuthorisationException(
					"logged in user is not an admin or is not operating on their own account");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IAuthorisationService#getLoggedInUser()
	 */
	@Override
	@Transactional(readOnly = true)
	public UserProfile getLoggedInUser() throws ServiceException {
		// if the auth token is a LdrbrdToken then return the stored principal
		// otherwise it's
		// presumed to be a UNamePword token
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null && auth instanceof LdrbrdAuthenticationToken) {
			// cast as LdrbrdAuthenticationToken and return the principal
			return ((LdrbrdAuthenticationToken) auth).getPrincipal();
		} else if (auth != null) {
			try {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put(Constant.QUERY_PARAM_PROFILE_HANDLE,
						auth.getName());
				return golferDao.findByNamedQuery(Golfer.FIND_BY_HANDLE,
						paramMap, 1).get(0);
			} catch (PersistenceException e) {
				LOG.error(
						"problem retriving golfer from the database with profileHandle: {}",
						auth.getName());
				throw new ServiceException(e);
			}
		}
		throw new ServiceException("no user is logged in");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IAuthorisationService#hasLoggedInUserPrivilege(java.lang.String)
	 */
	@Override
	public boolean hasLoggedInUserPrivilege(String priviledge) {
		// get the authentication token for the logged in user
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			// cycle through the authorities
			for (GrantedAuthority ga : auth.getAuthorities()) {
				if (ga.getAuthority().equalsIgnoreCase(priviledge)) {
					return true;
				}
			}
		} else {
			LOG.error("no user logged in");
		}
		return false;
	}
}
