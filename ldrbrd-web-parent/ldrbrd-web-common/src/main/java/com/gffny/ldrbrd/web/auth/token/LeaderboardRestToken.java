/**
 * 
 */
package com.gffny.ldrbrd.web.auth.token;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.gffny.ldrbrd.common.model.impl.UserProfile;

/**
 * @author jdgaffney
 * 
 */
@SuppressWarnings("serial")
public class LeaderboardRestToken implements Authentication {

	/**
	 * 
	 */
	private String profileHandle;

	/**
	 * 
	 */
	private UserProfile principal;

	/**
	 * 
	 */
	private Collection<? extends GrantedAuthority> grantedAuthorityCollection;

	/**
	 * 
	 */
	private boolean isAuthenticated = true;

	/**
	 * 
	 * @param tokenId
	 */
	public LeaderboardRestToken(String profileHandle,
			Collection<? extends GrantedAuthority> grantedAuthorityCollection,
			UserProfile princial) {
		this.profileHandle = profileHandle;
		this.principal = princial;
		this.grantedAuthorityCollection = grantedAuthorityCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.Principal#getName()
	 */
	public String getName() {
		return this.profileHandle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getAuthorities()
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorityCollection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getCredentials()
	 */
	public Object getCredentials() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getDetails()
	 */
	public Object getDetails() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getPrincipal()
	 */
	public UserProfile getPrincipal() {
		return this.principal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#isAuthenticated()
	 */
	public boolean isAuthenticated() {
		return this.isAuthenticated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.Authentication#setAuthenticated(boolean
	 * )
	 */
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}

}
