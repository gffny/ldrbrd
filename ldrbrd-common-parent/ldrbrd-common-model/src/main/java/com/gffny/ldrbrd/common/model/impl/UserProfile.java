/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author jdgaffney
 * 
 */
@MappedSuperclass
public abstract class UserProfile extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5309751265778916250L;

	private String profileHandle;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String password;

	private String profileImageRef;

	private boolean isEnabled = true;

	private DateTime lastLogin;

	private int failedLoginAttemptsCount = 0;

	/* CONSTRUCTORS */

	/* ACCESSOR/MUTATOR METHODS */

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getFirstName()
	 */
	@Column(name = "frst_nm")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getLastName()
	 */
	@Column(name = "lst_nm")
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "eml_addrss")
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getProfileHandle()
	 */
	@Column(name = "dsply_nm")
	public String getProfileHandle() {
		return this.profileHandle;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setProfileHandle(java.lang.String)
	 */
	public void setProfileHandle(String profileHandle) {
		this.profileHandle = profileHandle;
	}

	/**
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getPassword()
	 */
	@XmlTransient
	@JsonIgnore
	@Column(name = "psswrd")
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
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getLocation()
	 */
	@Column(name = "img_ref")
	public String getProfileImageRef() {
		return profileImageRef;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setLocation(java.lang.String)
	 */
	public void setProfileImageRef(String profileImageRef) {
		this.profileImageRef = profileImageRef;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getLastLogin()
	 */
	@Column(name = "lst_lgn_dt")
	public Date getLastLogin() {
		if (getLastLoginDT() != null) {
			return getLastLoginDT().toDate();
		}
		return null;
	}

	/**
	 * 
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
	 * 
	 * @param lastLogin
	 */
	@Transient
	public void setLastLoginDT(DateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "is_nbld", columnDefinition = "BIT", length = 1)
	public boolean isEnabled() {
		return this.isEnabled;
	}

	/**
	 * 
	 * @param isEnabled
	 */
	public void setEnabled(Boolean isEnabled) {
		this.isEnabled = getDefaultNotNullValue(isEnabled, false);
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getFailedLoginAttemptsCount()
	 */
	@Column(name = "fld_lgn_attmpts")
	public int getFailedLoginAttemptsCount() {
		return this.failedLoginAttemptsCount;
	}

	/**
	 * 
	 * @param failedLoginAttemptsCount
	 */
	public void setFailedLoginAttemptsCount(Integer failedLoginAttemptsCount) {
		this.failedLoginAttemptsCount = getDefaultNotNullValue(
				failedLoginAttemptsCount, 5);
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#incrementFailedLoginAttemptsCount()
	 */
	@Transient
	public void incrementFailedLoginAttemptsCount() {
		this.failedLoginAttemptsCount++;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#resetFailedLoginAttemptsCount()
	 */
	@Transient
	public void resetFailedLoginAttemptsCount() {
		this.failedLoginAttemptsCount = 0;
	}

}
