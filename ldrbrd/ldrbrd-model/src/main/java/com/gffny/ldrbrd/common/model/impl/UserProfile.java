/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 */
@MappedSuperclass
public abstract class UserProfile extends CommonIDEntity {

	/** serial version uid */
	private static final long serialVersionUID = -5309751265778916250L;

	/** */
	private String profileHandle;

	/** */
	private String firstName;

	/** */
	private String lastName;

	/** */
	private String emailAddress;

	/** */
	private String password;

	/** */
	private String profileImageRef;

	/** */
	private boolean isEnabled = true;

	/** */
	private DateTime lastLogin;

	/** */
	private int failedLoginAttemptsCount = 0;

	/* CONSTRUCTORS */

	/* ACCESSOR/MUTATOR METHODS */
	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getFirstName()
	 */
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getLastName()
	 */
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getProfileHandle()
	 */
	@Column(name = "profile_handle")
	public String getProfileHandle() {
		return this.profileHandle;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setProfileHandle(java.lang.String)
	 */
	public void setProfileHandle(String profileHandle) {
		this.profileHandle = profileHandle;
	}

	/**
	 * @return
	 */
	@Column(name = "email_address")
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getPassword()
	 */
	@Transient
	public String getPassword() {
		return this.password;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setPassword(java.lang.String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getLocation()
	 */
	@Transient
	public String getProfileImageRef() {
		return profileImageRef;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setLocation(java.lang.String)
	 */
	public void setProfileImageRef(String profileImageRef) {
		this.profileImageRef = profileImageRef;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getLastLogin()
	 */
	@Transient
	public Date getLastLogin() {
		if (getLastLoginDT() != null) {
			return getLastLoginDT().toDate();
		}
		return null;
	}

	/**
	 * @return
	 */
	@Transient
	public DateTime getLastLoginDT() {
		return this.lastLogin;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setLastLogin(org.joda.time.DateTime)
	 */
	public void setLastLogin(Date lastLogin) {
		setLastLoginDT(new DateTime(lastLogin));
	}

	/**
	 * @param lastLogin
	 */
	@Transient
	public void setLastLoginDT(DateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return
	 */
	@JsonIgnore
	@Transient
	public boolean isEnabled() {
		return this.isEnabled;
	}

	/**
	 * @param isEnabled
	 */
	public void setEnabled(Boolean isEnabled) {
		this.isEnabled = getDefaultNotNullValue(isEnabled, false);
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getFailedLoginAttemptsCount()
	 */
	@Transient
	public int getFailedLoginAttemptsCount() {
		return this.failedLoginAttemptsCount;
	}

	/**
	 * @param failedLoginAttemptsCount
	 */
	public void setFailedLoginAttemptsCount(Integer failedLoginAttemptsCount) {
		this.failedLoginAttemptsCount = getDefaultNotNullValue(failedLoginAttemptsCount, 5);
	}
}
