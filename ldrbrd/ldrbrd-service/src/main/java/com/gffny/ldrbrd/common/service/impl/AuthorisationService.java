/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.AuthorizationException;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.auth.LeaderboardUserDetails;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.service.IAuthorisationService;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 */
@Service
public class AuthorisationService implements IAuthorisationService, UserDetailsService,
		AuthenticationProvider {

	/** */
	private static Logger LOG = Logger.getLogger(AuthorisationService.class);

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
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("profileHandle", username);
			List<Golfer> golferList = golferDao
					.findByNamedQuery(Golfer.FIND_BY_HANDLE, queryMap, 1);
			if (golferList != null && golferList.size() == 1) {
				return new LeaderboardUserDetails(golferList.get(0));
			} else if (golferList != null && golferList.size() > 1) {
				LOG.error("No unique user has been found for user (" + username + ")");
				throw new UsernameNotFoundException("No unique user has been found for user ("
						+ username + ")");
			} else if (golferList != null && golferList.size() < 1) {
				LOG.error("User (" + username + ") has not been found");
				throw new UsernameNotFoundException("User (" + username + ") has not been found");
			} else {
				LOG.error("other issues here");
				throw new UsernameNotFoundException("Other issues!");
			}
		} catch (PersistenceException dae) {
			LOG.error("User (" + username + ") has not been found");
			throw new UsernameNotFoundException("User (" + username + ") has not been found");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		try {
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("profileHandle", authentication.getName());
			List<Golfer> golferList = golferDao
					.findByNamedQuery(Golfer.FIND_BY_HANDLE, queryMap, 1);
			if (golferList != null && golferList.size() == 1) {
				Golfer golfer = golferList.get(0);
				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
				grantedAuths.add(new SimpleGrantedAuthority("GOLFER"));
				Authentication auth = new UsernamePasswordAuthenticationToken(
						golfer.getProfileHandle(), golfer.getPassword(), grantedAuths);

				return auth;
			}
		} catch (PersistenceException dae) {
			LOG.error("User (" + authentication.getName() + ") has not been found");
			throw new UsernameNotFoundException("User (" + authentication.getName()
					+ ") has not been found");
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> authentication) {
		// TODO add the tokens that are supported

		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IAuthorisationService#authorise(java.lang.String)
	 */
	public String authorise(String profileId) throws AuthorizationException {
		// check params
		if (StringUtils.isEmpty(profileId)) {
			// if the profile id is null or empty return the users own profile id
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			auth.getName();
			auth.getCredentials();
		} else {
			// check if the logged in user is authorise to see this profile

		}
		return null;
	}
}
