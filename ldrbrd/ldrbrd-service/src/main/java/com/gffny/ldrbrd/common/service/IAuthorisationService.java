package com.gffny.ldrbrd.common.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gffny.ldrbrd.common.exception.AuthorizationException;

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

	/**
	 * @throws AuthorizationException
	 */
	public abstract String authorise(String profileId) throws AuthorizationException;

	// TODO potentially add new methods to authorize different behaviours, such as society admin
	// behaviours, etc.
}