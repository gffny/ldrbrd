/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gffny.ldrbrd.common.exception.AuthorizationException;
import com.gffny.ldrbrd.common.model.auth.LeaderboardUserDetails;
import com.gffny.ldrbrd.common.service.IAuthorisationService;
import com.gffny.ldrbrd.common.utils.BootStrapUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
public class InMemoryAuthenticationService extends AbstractService implements IAuthorisationService {

	/**
	 * 
	 */
	public InMemoryAuthenticationService() {
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IAuthorisationService#isPermitted(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean isPermitted(String userId, String enterScorecard) {
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IAuthorisationService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new LeaderboardUserDetails(BootStrapUtils.golfer());
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IAuthorisationService#authorize()
	 */
	public void authorize() throws AuthorizationException {
		// everyone is authorize to do everything all the time!
	}

}
