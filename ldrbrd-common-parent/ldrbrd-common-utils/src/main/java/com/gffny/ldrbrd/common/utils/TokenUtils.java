/**
 * 
 */
package com.gffny.ldrbrd.common.utils;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author John Gaffney | gffny.com
 * 
 */
public interface TokenUtils {

	/**
	 * 
	 * @param userDetails
	 * @return
	 */
	public String getToken(UserDetails userDetails);

	/**
	 * 
	 * @param userDetails
	 * @param expiration
	 * @return
	 */
	public String getToken(UserDetails userDetails, Long expiration);

	/**
	 * 
	 * @param token
	 * @return
	 */
	public boolean validate(String token);

	/**
	 * 
	 * @param token
	 * @return
	 */
	public UserDetails getUserFromToken(String token);

}
