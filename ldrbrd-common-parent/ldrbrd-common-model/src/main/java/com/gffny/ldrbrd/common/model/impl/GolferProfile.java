/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gffny.ldrbrd.common.model.enums.Dominance;

/**
 * @author
 * 
 */
@Entity
@Table(name = "t_golfer")
public class GolferProfile extends UserProfile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7564055826202157120L;

	private Dominance handedness;
	private int handicap = 0;

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	// public String toString() {
	// return "Golfer [userId=" + this.getId() + ", profileHandle="
	// + this.getProfileHandle() + ", emailAddress="
	// + getEmailAddress() + ", firstName=" + firstName
	// + ", lastName=" + lastName + ", handicap=" + handicap + "]";
	// }

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#getHandicap()
	 */
	@Column(name = "hndcp")
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
	@Column(name = "hnddnss")
	public Dominance getHandedness() {
		return handedness;
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	public String getHandednessValue() {
		// TODO check if I want to call this value or something else
		// change to dominance
		return handedness.toString();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setLocation(java.lang.String)
	 */
	public void setHandedness(Dominance handedness) {
		this.handedness = handedness;
	}

	/**
	 * 
	 */
	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + " "
				+ this.getId();
	}
}
