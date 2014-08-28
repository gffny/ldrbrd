/**
 * 
 */
package com.gffny.ldrbrd.security.token;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.gffny.ldrbrd.common.model.impl.Golfer;

/**
 * @author John D. Gaffney | gffny.com
 */
public class LdrbrdAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8832317191249953286L;

	/**
	 * @param principal
	 * @param credentials
	 */
	public LdrbrdAuthenticationToken(Golfer principal, Object credentials) {
		super(principal, credentials);
	}

	/**
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public LdrbrdAuthenticationToken(Golfer principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	/**
	 * 
	 */
	@Override
	public Golfer getPrincipal() {
		return (Golfer) super.getPrincipal();
	}
}
