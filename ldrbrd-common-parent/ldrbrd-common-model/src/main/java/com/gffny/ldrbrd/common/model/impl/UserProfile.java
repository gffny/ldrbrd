/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author jdgaffney
 * 
 */
@Entity
@Table(name = "t_user")
public class UserProfile extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5309751265778916250L;

	@Id
	@Column(name = "usr_id")
	private Integer id;

	@Column(name = "is_nbld")
	private boolean isEnabled = true;

	@Column(name = "lst_lgn_in")
	private DateTime lastLogin;

	@Column(name = "eml_addrss")
	protected String emailAddress;

	@Column(name = "psswrd")
	private String password;

	@Column(name = "fld_lgn_attmpts")
	private int failedLoginAttemptsCount = 0;

	/* CONSTRUCTORS */

	/* ACCESSOR/MUTATOR METHODS */
	/**
	 * @return the isEnabled
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * @param isEnabled
	 *            the isEnabled to set
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getPassword()
	 */
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
	 * @see com.gffny.leaderboard.model.IGolfer#getLastLogin()
	 */
	public String getLastLogin() {
		return this.lastLogin.toString();
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setLastLogin(java.lang.String)
	 */
	public void setLastLogin(String lastLogin) {
		// TODO create a DateUtils class (and project for utils)
		// this.lastLogin = DateUtils.parseDateOrNull(lastLogin,
		// DateUtils.MYSQL_DATE_FORMAT.getPattern());
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setLastLogin(java.lang.String)
	 */
	public void setLastLogin(DateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getFailedLoginAttemptsCount()
	 */
	public int getFailedLoginAttemptsCount() {
		return this.failedLoginAttemptsCount;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#incrementFailedLoginAttemptsCount()
	 */
	public void incrementFailedLoginAttemptsCount() {
		this.failedLoginAttemptsCount++;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#resetFailedLoginAttemptsCount()
	 */
	public void resetFailedLoginAttemptsCount() {
		this.failedLoginAttemptsCount = 0;
	}

}
