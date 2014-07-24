/**
 * 
 */
package com.gffny.ldrbrd.common.security.token.impl;

import com.gffny.ldrbrd.common.security.token.AuthenticationToken;

/**
 * @author John D. Gaffney | gffny.com
 * 
 */
public class EmailPasswordToken implements AuthenticationToken {

	private String identifier;
	private String authenticator;

	public EmailPasswordToken(String emailAddress, String password) {
		this.identifier = emailAddress;
		this.authenticator = password;
	}

	public String getTokenIdentifier() {
		return this.identifier;
	}

	public String getTokenAuthenticator() {
		return this.authenticator;
	}

}
