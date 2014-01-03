package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Embeddable
public class GolferClubDetail {
	/**
	 * 
	 */
	private GolfClub golfClub;

	/**
	 * 
	 */
	private int averageDistance;

	/**
	 * 
	 */
	private String clubNotes;

	/**
	 * @return the golfClub
	 */
	@ManyToOne
	@JoinColumn(name = "glfclb_id", nullable = false)
	@ForeignKey(name = "id")
	public GolfClub getGolfClub() {
		return golfClub;
	}

	/**
	 * @param golfClub
	 *            the golfClub to set
	 */
	public void setGolfClub(GolfClub golfClub) {
		this.golfClub = golfClub;
	}

	/**
	 * @return the averageDistance
	 */
	@Column(name = "avg_dstnc")
	public int getAverageDistance() {
		return averageDistance;
	}

	/**
	 * @param averageDistance
	 *            the averageDistance to set
	 */
	public void setAverageDistance(int averageDistance) {
		this.averageDistance = averageDistance;
	}

	/**
	 * @return the clubNotes
	 */
	@Column(name = "glfr_nts")
	public String getClubNotes() {
		return clubNotes;
	}

	/**
	 * @param clubNotes
	 *            the clubNotes to set
	 */
	public void setClubNotes(String clubNotes) {
		this.clubNotes = clubNotes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GolferClubDetail ["
				+ (golfClub != null ? "golfClub=" + golfClub.toString() + ", "
						: "") + "averageDistance=" + averageDistance + ", "
				+ (clubNotes != null ? "clubNotes=" + clubNotes : "") + "]";
	}

}