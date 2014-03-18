/**
 * 
 */
package com.gffny.ldrbrd.common.utils;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author jdgaffney
 * 
 */
@Service
public class TokenUtilsImpl implements TokenUtils {

	/**
	 * 
	 */
	public TokenUtilsImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.utils.TokenUtils#getToken(org.springframework
	 * .security.core.userdetails.UserDetails)
	 */
	public String getToken(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.utils.TokenUtils#getToken(org.springframework
	 * .security.core.userdetails.UserDetails, java.lang.Long)
	 */
	public String getToken(UserDetails userDetails, Long expiration) {
		// TODO Auto-generated method stub

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.utils.TokenUtils#validate(java.lang.String)
	 */
	public boolean validate(String token) {
		// TODO Auto-generated method stub
		System.out.println(token);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.utils.TokenUtils#getUserFromToken(java.lang.String
	 * )
	 */
	public UserDetails getUserFromToken(String token) {
		// TODO Auto-generated method stub
		System.out.println(token);
		return null;
	}

}
