/**
 * 
 */
package com.gffny.ldrbrd.rest.resp;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author jdgaffney
 *
 */

public class AuthenticateTokenResponse {

	private StatusResponse status;
	private UserDetails userDetails;

	/**
	 * 
	 * @return
	 */
	public StatusResponse getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(final StatusResponse status) {
		this.status = status;
	}

	/**
	 * 
	 * @return
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}

	/**
	 * 
	 * @param userInfo
	 */
	public void setUserInfo(final UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}
