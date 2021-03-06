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
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.gffny.ldrbrd.common.model.enums.Dominance;

/**
 * @author
 * 
 */
@NamedQueries({
		@NamedQuery(name = GolferProfile.FIND_BY_HANDLE, query = "select golfer from GolferProfile golfer where golfer.profileHandle = :profileHandle"),
		@NamedQuery(name = GolferProfile.FIND_BY_EMAIL, query = "select golfer from GolferProfile golfer where golfer.emailAddress = :emailAddress")
// TODO golfer.isObsolete = false and ...
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
	private Dominance handDominance;

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
	 */
	private List<Course> favouriteCourseList;

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
		return handDominance;
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	public String getHandDominanceValue() {
		return handDominance.getName();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfer#setLocation(java.lang.String)
	 */
	public void setHandedness(Dominance handDominance) {
		this.handDominance = handDominance;
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
	 * @return the favouriteCourseList
	 */
	// @JoinTable(name = "t_favourite_course", joinColumns = @JoinColumn(name =
	// "project_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
	@XmlTransient
	@JsonIgnore
	@Transient
	public List<Course> getFavouriteCourseList() {
		return favouriteCourseList;
	}

	/**
	 * @param favouriteCourseList
	 *            the favouriteCourseList to set
	 */
	public void setFavouriteCourseList(List<Course> favouriteCourseList) {
		this.favouriteCourseList = favouriteCourseList;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + " "
				+ this.getId();
	}
}