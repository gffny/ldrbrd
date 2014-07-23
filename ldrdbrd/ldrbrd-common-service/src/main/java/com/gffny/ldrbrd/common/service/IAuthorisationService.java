package com.gffny.ldrbrd.common.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IAuthorisationService {

	/**
	 * @see com.gffny.ldrbrd.common.service.leaderboard.service.IAuthorisationService#isPermitted(java.lang.String,
	 *      java.lang.String)
	 */
	public abstract boolean isPermitted(String userId, String enterScorecard);

	/**
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public abstract UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException;

}