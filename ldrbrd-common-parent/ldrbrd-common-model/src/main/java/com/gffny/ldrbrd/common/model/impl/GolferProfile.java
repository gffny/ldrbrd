/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.gffny.ldrbrd.common.model.enums.Dominance;

/**
 * @author
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_player")
public class GolferProfile extends UserProfile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7564055826202157120L;

	private String profileHandle;
	private String firstName;
	private String lastName;
	private Dominance handedness;
	private int handicap = 0;

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Golfer [userId=" + this.getId() + ", profileHandle="
				+ this.getProfileHandle() + ", emailAddress=" + emailAddress
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", handicap=" + handicap + "]";
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getProfileHandle()
	 */
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
	 * @see com.gffny.leaderboard.model.IGolfer#getEmailAddress()
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setEmailAddress(java.lang.String)
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getFirstName()
	 */
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
	 * @see com.gffny.leaderboard.model.IGolfer#getHandicap()
	 */
	public Integer getHandicap() {
		return handicap;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setHandicap(java.lang.String)
	 */
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getLocation()
	 */
	public Dominance getHandedness() {
		return handedness;
	}

	/**
	 * 
	 * @return
	 */
	public String getHandednessValue() {
		// TODO check if I want to call this value or something else
		return handedness.toString();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setLocation(java.lang.String)
	 */
	public void setHandedness(Dominance handedness) {
		this.handedness = handedness;
	}
}
