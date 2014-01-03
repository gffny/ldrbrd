/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gffny.ldrbrd.common.model.enums.Dominance;

/**
 * @author
 * 
 */
@NamedQueries({
		@NamedQuery(name = GolferProfile.FIND_BY_HANDLE, query = "select golfer from GolferProfile golfer where golfer.profileHandle = :profileHandle"),
		@NamedQuery(name = GolferProfile.FIND_BY_EMAIL, query = "select golfer from GolferProfile golfer where golfer.emailAddress = :emailAddress")
// golfer.isObsolete = false and ...
})
@Entity
@Table(name = "t_golfer")
public class GolferProfile extends UserProfile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7564055826202157120L;

	/**
	 * 
	 */
	public static final String FIND_BY_HANDLE = "findByProfileHandle";

	/**
	 * 
	 */
	public static final String FIND_BY_EMAIL = "findByEmailAddress";

	/**
	 * 
	 */
	private Dominance handedness;

	/**
	 * 
	 */
	private int handicap = 0;

	/**
	 * 
	 */
	private List<GolferClubDetail> golfBag;

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
	 * @return
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "t_golf_bag", joinColumns = @JoinColumn(name = "glfr_id"))
	public List<GolferClubDetail> getGolfBag() {
		return this.golfBag;
	}

	/**
	 * 
	 * @param golfBag
	 */
	public void setGolfBag(List<GolferClubDetail> golfBag) {
		this.golfBag = golfBag;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + " "
				+ this.getId();
	}
}
