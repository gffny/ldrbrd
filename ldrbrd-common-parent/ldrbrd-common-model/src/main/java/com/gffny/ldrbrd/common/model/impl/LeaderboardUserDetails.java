/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author jdgaffney
 * 
 */
public class LeaderboardUserDetails implements UserDetails {

	private UserProfile user;

	private List<GrantedAuthority> authList;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;

	/**
     *
     */
	private static final long serialVersionUID = -3653716496671608295L;

	/**
	 * @param user
	 */
	public LeaderboardUserDetails(UserProfile user) {
		this.user = user;
		this.authList = new ArrayList<GrantedAuthority>();
		this.authList.add(new SimpleGrantedAuthority("GOLFER"));
		this.authList.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	/**
	 * @return the user
	 */
	public UserProfile getUser() {
		return user;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authList;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {

		return this.getUser().getPassword();
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return this.getUser().getProfileHandle();
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {

		return this.accountNonLocked;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {

		return this.credentialsNonExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {

		return this.enabled;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserProfile user) {

		this.user = user;
	}

	/**
	 * @param accountNonExpired
	 *            the accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {

		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @param accountNonLocked
	 *            the accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {

		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {

		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {

		this.enabled = enabled;
	}
}
