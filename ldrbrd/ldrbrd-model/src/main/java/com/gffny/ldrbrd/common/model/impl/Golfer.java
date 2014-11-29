/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.enums.Dominance;
import com.gffny.ldrbrd.common.model.nosql.Course;

/**
 * @author
 */
@NamedQueries({
		@NamedQuery(name = Golfer.FIND_BY_HANDLE, query = "select glfr from Golfer glfr where glfr.profileHandle = :profileHandle"),
		@NamedQuery(name = Golfer.FIND_BY_EMAIL, query = "select glfr from Golfer glfr where glfr.emailAddress = :emailAddress")
// TODO golfer.isObsolete = false and ...
})
@Entity
@Table(name = Constant.DB_TABLE_GOLFER)
public class Golfer extends UserProfile {

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
	private Dominance handDominance = Dominance.RIGHT;

	/**
	 * 
	 */
	private int handicap = 0;

	/**
	 * 
	 */
	private List<Course> favouriteCourseList;

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getHandicap()
	 */
	@Column(name = "handicap")
	public Integer getHandicap() {
		return handicap;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setHandicap(java.lang.String)
	 */
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#getLocation()
	 */
	@Transient
	public Dominance getHandedness() {
		return handDominance;
	}

	/**
	 * @return
	 */
	@Transient
	public String getHandDominanceValue() {
		return handDominance.getName();
	}

	/**
	 * @see com.gffny.leaderboard.model.IGolfer#setLocation(java.lang.String)
	 */
	public void setHandedness(Dominance handDominance) {
		this.handDominance = handDominance;
	}

	/**
	 * @return the favouriteCourseList
	 */
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
	@Override
	public String toString() {
		return "[" + this.getFirstName() + " " + this.getLastName() + " | id: "
				+ this.getId() + "]";

	}
}