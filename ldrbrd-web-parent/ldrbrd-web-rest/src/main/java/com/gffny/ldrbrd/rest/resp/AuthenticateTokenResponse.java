/**
 * 
 */
package com.gffny.ldrbrd.rest.resp;

/**
 * @author jdgaffney
 *
 */

public class AuthenticateTokenResponse {

	private StatusResponse status;
//	private UserInfo userInfo;

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
//	public UserInfo getUserInfo() {
//		return userInfo;
//	}

	/**
	 * 
	 * @param userInfo
	 */
//	public void setUserInfo(final UserInfo userInfo) {
//		this.userInfo = userInfo;
//	}
}
