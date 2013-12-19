/**
 * 
 */
package com.gffny.ldrbrd.web.model.dto;

import java.util.Collection;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;

import com.gffny.ldrbrd.common.model.enums.Dominance;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.impl.LeaderboardUserDetails;

/**
 * @author John Gaffney (john@gffny.com) May 1` 2013
 * 
 */
public class UserDto extends BaseDto {

	@JsonIgnore
	private String id;
	@JsonIgnore
	private String profileHandle;
	@JsonIgnore
	private String firstName;
	@JsonIgnore
	private String lastName;
	@JsonIgnore
	private String emailAddress;
	@JsonIgnore
	private String profileImageRef;	
	@JsonIgnore
	private DateTime lastLogin;
	@JsonIgnore
	private int failedLoginAttemptsCount = 0;
	@JsonIgnore
	private boolean enabled;

	@JsonIgnore
	private int handicap;
	@JsonIgnore
	private Dominance handedness = Dominance.RIGHT;

	@JsonIgnore
	private boolean obsolete;
	@JsonIgnore
	private boolean archive;
	@JsonIgnore
	private boolean delete;

	@JsonIgnore
	private int syncVersionId = 0;
	
	@JsonIgnore
	private Collection<? extends GrantedAuthority> authorities = null;
	@JsonIgnore
	private boolean accountNonExpired = false;
	@JsonIgnore
	private boolean accountNonLocked = false;
	@JsonIgnore
	private boolean credentialsNonExpired = false;
	@JsonIgnore
	private String message;

	/**
	 * 
	 * @param user
	 */
	public void setUser(LeaderboardUserDetails user) {
		if(user != null) {
			GolferProfile golfer = (GolferProfile) user.getUser();
			
			this.id = golfer.getId();

			this.profileHandle = golfer.getProfileHandle();
			this.firstName = golfer.getFirstName();
			this.lastName = golfer.getLastName();
			this.emailAddress = golfer.getEmailAddress();
			this.lastLogin = golfer.getLastLoginDT();
			this.handedness = golfer.getHandedness();
			this.handicap = golfer.getHandicap().intValue();
			this.profileImageRef = golfer.getProfileImageRef();

			this.failedLoginAttemptsCount = golfer.getFailedLoginAttemptsCount();			
			
			this.obsolete = golfer.isObsolete();
			this.archive = golfer.isArchive();
			this.delete = golfer.isDelete();
			this.authorities = user.getAuthorities();
			this.accountNonExpired = user.isAccountNonExpired();
			this.accountNonLocked = user.isAccountNonLocked();
			this.credentialsNonExpired = user.isCredentialsNonExpired();

			//TODO fix which "isEnabled()" to use!
			this.enabled = user.isEnabled();
			this.enabled = golfer.isEnabled();
}
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the profileHandle
	 */
	public String getProfileHandle() {
		return profileHandle;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @return the emailAddress
	 */
	public String getProfileImageRef() {
		return profileImageRef;
	}

	/**
	 * @return the lastLogin
	 */
	public DateTime getLastLogin() {
		return lastLogin;
	}

	/**
	 * @return the failedLoginAttemptsCount
	 */
	public int getFailedLoginAttemptsCount() {
		return failedLoginAttemptsCount;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @return the handicap
	 */
	public int getHandicap() {
		return handicap;
	}

	/**
	 * @return the handedness
	 */
	public Dominance getHandedness() {
		return handedness;
	}

	/**
	 * @return the obsolete
	 */
	public boolean isObsolete() {
		return obsolete;
	}

	/**
	 * @return the archive
	 */
	public boolean isArchive() {
		return archive;
	}

	/**
	 * @return the delete
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * @return the syncVersionId
	 */
	public int getSyncVersionId() {
		return syncVersionId;
	}

	/**
	 * @return the authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @return the accountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param object
	 */
	public void setMessage(String password) {
		this.message = password;
	}
}
