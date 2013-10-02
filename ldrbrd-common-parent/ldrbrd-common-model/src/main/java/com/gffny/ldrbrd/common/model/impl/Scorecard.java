/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Arrays;
import java.util.Date;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John Gaffney (john@gffny.com) Jul 30, 2012
 * 
 */
public class Scorecard extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 641411664200798837L;

	private String teesPlayedOff = new String();
	private int[] scoreArray = null;
	private String scorecardNotes = new String();
	private Date scorecardDate;
	private int handicap;

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 18;
		return "Scorecard [teesPlayedOff="
				+ teesPlayedOff
				+ ", scoreArray="
				+ (scoreArray != null ? Arrays.toString(Arrays.copyOf(
						scoreArray, Math.min(scoreArray.length, maxLen)))
						: null) + ", scorecardNotes=" + scorecardNotes
				+ ", scorecardDate=" + scorecardDate + ", grossScore="
				+ getGrossScore() + ", handicap=" + getHandicapForRound() + "]";
	}

	/**
	 *
	 */
	public int getScoreOnHole(int holeNumber) {
		return scoreArray[holeNumber - 1];
	}

	/**
	 *
	 */
	public int getGrossScore() {
		int grossScore = 0;
		for (int i = 0; i < scoreArray.length; i++) {
			grossScore += scoreArray[i];
		}
		return grossScore;
	}

	public int getHandicapForRound() {
		return handicap;
	};

	/**
	 * 
	 */
	public String getScorecardNotes() {
		return this.scorecardNotes;
	}

	/**
	 * 
	 */
	public String getTeesPlayedOffCode() {
		return teesPlayedOff;
	}

	/**
	 * 
	 */
	public String getTeesPlayedOffColour() {
		return this.teesPlayedOff;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IScorecard#getNumberOfHoles()
	 */
	public int getNumberOfHoles() {
		return scoreArray.length;
	};

	/**
	 * 
	 */
	public Date getScorecardDate() {
		return this.scorecardDate;
	}
}
