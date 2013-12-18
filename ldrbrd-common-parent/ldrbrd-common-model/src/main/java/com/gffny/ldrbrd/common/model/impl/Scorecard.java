/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John Gaffney (john@gffny.com) Jul 30, 2012
 * 
 */
@Entity
@Table(name = "t_scorecard")
public class Scorecard extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 641411664200798837L;

	private GolferProfile golfer;
	private Course course;
	private CompetitionRound competitionRound;
	private String teesPlayedOff = new String();
	private int[] scoreArray = null;
	private String scorecardNotes = new String();
	private DateTime scorecardDate;
	private int handicap;

	/**
	 * Factory method to create a instance of Scorecard
	 * 
	 * @param golferId
	 * @param courseId
	 * @param handicap
	 * @return
	 */
	public static Scorecard createNewScorecard(GolferProfile golfer,
			Course course, int handicap) {
		Scorecard scorecard = new Scorecard(golfer, course, handicap);
		scorecard.setCreatedDateDT(new DateTime(System.currentTimeMillis()));
		return scorecard;
	}

	/**
	 * Factory method to create a instance of Scorecard
	 * 
	 * @param golferId
	 * @param courseId
	 * @param handicap
	 * @return
	 */
	public static Scorecard createNewCompetitionScorecard(GolferProfile golfer,
			CompetitionRound competitionRound, int handicap) {
		Scorecard scorecard = new Scorecard(golfer,
				competitionRound.getCourse(), handicap);
		scorecard.setCompetitionRound(competitionRound);
		scorecard.setCreatedDateDT(new DateTime(System.currentTimeMillis()));
		return scorecard;
	}

	/**
	 *
	 */
	public Scorecard() {
		//hibernate required non-private zero-argument constructor
	}

	/**
	 * 
	 * @param golferId
	 * @param courseId
	 * @param handicap
	 */
	private Scorecard(GolferProfile golfer, Course course, int handicap) {
		this.golfer = golfer;
		this.course = course;
		this.handicap = handicap;
	}

	/**
	 * 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "glfr_id", nullable = false)
	@ForeignKey(name = "id")
	public GolferProfile getGolfer() {
		return this.golfer;
	}

	/**
	 * 
	 * @param golferId
	 */
	public void setGolfer(GolferProfile golfer) {
		this.golfer = golfer;
	}

	/**
	 * 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "crs_id", nullable = false)
	@ForeignKey(name = "id")
	public Course getCourse() {
		return this.course;
	}

	/**
	 * 
	 * @param courseId
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "cmpttn_rnd_id", nullable = true)
	@ForeignKey(name = "id")
	public CompetitionRound getCompetitionRound() {
		return this.competitionRound;
	}

	/**
	 * 
	 * @param courseId
	 */
	public void setCompetitionRound(CompetitionRound competitionRound) {
		this.competitionRound = competitionRound;
	}

	/**
	 *
	 */
	@Transient
	public int getScoreOnHole(int holeNumber) {
		return scoreArray[holeNumber - 1];
	}

	/**
	 *
	 */
	@Transient
	public int getGrossScore() {
		int grossScore = 0;
		for (int i = 0; i < scoreArray.length; i++) {
			grossScore += scoreArray[i];
		}
		return grossScore;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "hndcp")
	public int getHandicap() {
		return handicap;
	};

	/**
	 * 
	 * @param handicap
	 */
	public void setHandicap(Integer handicap) {
		this.handicap = getDefaultNotNullValue(handicap, 0);
	}

	/**
	 * 
	 */
	@Transient
	public String getScorecardNotes() {
		return this.scorecardNotes;
	}

	/**
	 * 
	 */
	@Transient
	public String getTeesPlayedOffCode() {
		return teesPlayedOff;
	}

	/**
	 * 
	 */
	@Transient
	public String getTeesPlayedOffColour() {
		return this.teesPlayedOff;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IScorecard#getNumberOfHoles()
	 */
	@Transient
	public int getNumberOfHoles() {
		return scoreArray.length;
	};

	/**
	 * 
	 */
	@Column(name = "scrcrd_d")
	public Date getScorecardDate() {
		return this.scorecardDate.toDate();
	}

	/**
	 * 
	 * @param scorecardDate
	 */
	public void setScorecardDate(Date scorecardDate) {
		this.scorecardDate = new DateTime(scorecardDate);
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	public DateTime getScorecardDateDT() {
		return this.scorecardDate;
	}

	/**
	 * 
	 * @param scorecardDateTime
	 */
	@Transient
	public void setScorecardDateDT(DateTime scorecardDateTime) {
		this.scorecardDate = scorecardDateTime;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Transient
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
				+ getGrossScore() + ", handicap=" + getHandicap() + "]";
	}
}