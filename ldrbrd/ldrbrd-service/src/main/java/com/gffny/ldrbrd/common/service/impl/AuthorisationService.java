/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.AuthorizationException;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.UserProfile;
import com.gffny.ldrbrd.common.service.IAuthorisationService;
import com.gffny.ldrbrd.security.token.LdrbrdAuthenticationToken;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 */
@Service
public class AuthorisationService extends AbstractService implements IAuthorisationService {

	/** */
	private static Logger LOG = LoggerFactory.getLogger(AuthorisationService.class);

	/** */
	@Autowired
	private GenericDao<Golfer> golferDao;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.impl.IAuthorisationService#isPermitted
	 *      (java.lang.String, java.lang.String)
	 */
	public boolean isPermitted(String userId, String enterScorecard) {
		// TODO handle authorisation
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws ServiceException
	 * @see com.gffny.ldrbrd.common.service.IAuthorisationService#authorise(java.lang.String)
	 */
	public String authorise(String profileId) throws AuthorizationException, ServiceException {
		// check params
		if (StringUtils.isEmpty(profileId)) {
			// if the profile id is null or empty return the users own profile id
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
				return profileId;
			} else if (profileId.equalsIgnoreCase(String.valueOf(getLoggedInUser().getId()))) {
				LOG.debug("user is operating on their own account");
				return profileId;
			}
			LOG.error("logged in user is not an admin or is not operating on their own account");
			throw new AuthorizationException(
					"logged in user is not an admin or is not operating on their own account");
		}
	}

	/**
	 * @return
	 * @throws ServiceException
	 */
	public UserProfile getLoggedInUser() throws ServiceException {
		// if the auth token is a LdrbrdToken then return the stored principal otherwise it's
		// presumed to be a UNamePword token
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof LdrbrdAuthenticationToken) {
			// cast as LdrbrdAuthenticationToken and return the principal
			return ((LdrbrdAuthenticationToken) auth).getPrincipal();
		} else {
			try {
				return golferDao.findByNamedQuery(Golfer.FIND_BY_HANDLE,
						populateParamMap(Constant.QUERY_PARAM_PROFILE_HANDLE, auth.getName()), 1)
						.get(0);
			} catch (PersistenceException e) {
				LOG.error("problem retriving golfer from the database with profileHandle: {}",
						auth.getName());
				throw new ServiceException(e);
			}
		}
	}

	/**
	 * @return
	 */
	public boolean hasLoggedInUserPrivilege(String priviledge) {
		// get the authentication token for the logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// cycle through the authorities
		for (GrantedAuthority ga : auth.getAuthorities()) {
			if (ga.getAuthority().equalsIgnoreCase(priviledge)) {
				return true;
			}
		}
		return false;
	}
}
