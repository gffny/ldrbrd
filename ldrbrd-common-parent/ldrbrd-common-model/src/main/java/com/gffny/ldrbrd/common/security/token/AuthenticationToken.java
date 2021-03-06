/**
 * 
 */
package com.gffny.ldrbrd.common.security.token;

/**
 * @author jdgaffney
 * 
 */
public interface AuthenticationToken {

	/**
	 * username, email address, etc
	 * 
	 * @return
	 */
	public String getTokenIdentifier();

	/**
	 * facebook token, password, etc
	 * 
	 * @return
	 */
	public String getTokenAuthenticator();

}
