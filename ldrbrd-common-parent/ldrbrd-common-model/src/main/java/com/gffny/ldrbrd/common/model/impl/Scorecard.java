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

import org.apache.commons.lang.ArrayUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John Gaffney (john@gffny.com) Jul 30, 2012
 * 
 */

@NamedQueries({
		@NamedQuery(name = Scorecard.FIND_SCORECARD_BY_COMPETITION_ROUND_AND_GOLFER, query = "SELECT s FROM Scorecard s WHERE s.competitionRound.id = :competitionRoundId AND s.golfer.id  = :golferId"),
		// TODO Check that the scorecards are returned by in the right order
		@NamedQuery(name = Scorecard.FIND_SCORECARDS_BY_GOLFER_ID_AND_QUANTITY, query = "SELECT s FROM Scorecard s WHERE s.golfer.id  = :golferId ORDER BY s.scorecardDate") })
@Entity
@Table(name = "t_scorecard")
public class Scorecard extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 641411664200798837L;

	/**
	 * 
	 */
	public static final String FIND_SCORECARD_BY_COMPETITION_ROUND_AND_GOLFER = "findScorecardByCompetitionRoundAndGolfer";

	/**
	 * 
	 */
	public static final String FIND_SCORECARDS_BY_GOLFER_ID_AND_QUANTITY = "findScorecardsByGolferId";

	private GolferProfile golfer;
	private String golferName;
	private GolferProfile scoringGolfer;
	private String scoringGolferName;
	private Course course;
	private String courseName;
	private CompetitionRound competitionRound;
	private String teesPlayedOff = new String();
	private int[] scoreArray = new int[18];
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
			GolferProfile scoreKeeper, Course course, int handicap) {
		Scorecard scorecard = new Scorecard(golfer, scoreKeeper, course,
				handicap);
		scorecard.initDates(new DateTime(System.currentTimeMillis()));
		return scorecard;
	}

	/**
	 * 
	 * @param golfer
	 * @param scoreKeeper
	 * @param competitionRound
	 * @param handicap
	 * @return
	 */
	public static Scorecard createNewCompetitionScorecard(GolferProfile golfer,
			GolferProfile scoreKeeper, CompetitionRound competitionRound,
			int handicap) {
		Scorecard scorecard = new Scorecard(golfer, scoreKeeper,
				competitionRound.getCourse(), handicap);
		scorecard.setCompetitionRound(competitionRound);
		scorecard.initDates(new DateTime(System.currentTimeMillis()));
		return scorecard;
	}

	/**
	 *
	 */
	public Scorecard() {
		// hibernate required non-private zero-argument constructor
	}

	/**
	 * 
	 * @param golfer
	 * @param scoreKeeper
	 * @param course
	 * @param handicap
	 */
	private Scorecard(GolferProfile golfer, GolferProfile scoreKeeper,
			Course course, int handicap) {
		this.golfer = golfer;
		this.golferName = golfer.getFirstName() + " " + golfer.getLastName();
		this.scoringGolfer = scoreKeeper;
		this.scoringGolferName = scoringGolfer.getFirstName() + " "
				+ scoringGolfer.getLastName();
		this.course = course;
		this.courseName = course.getClub().getClubName() + " "
				+ course.getCourseName();
		this.handicap = handicap;
	}

	/**
	 * 
	 */
	public void signScorecard(String signature) {
		// TODO research what I could do to "sign" a scorecard
		// (look at adding a "isSubmitted" bool to the entity)
		// (signing time stamp)
		this.scorecardNotes = signature;
	}

	/**
	 * 
	 */
	public void submitScorecard(String submission) {
		// TODO research what I should do to submit a scorecard
		// (look at adding a "isSubmitted" bool to the entity)
		// (signing time stamp)
		this.scorecardNotes = submission;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
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
		this.golferName = golfer.getFirstName() + " " + golfer.getLastName();
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "scrng_glfr_id", nullable = false)
	@ForeignKey(name = "id")
	public GolferProfile getScoringGolfer() {
		return this.scoringGolfer;
	}

	/**
	 * 
	 * @param golferId
	 */
	public void setScoringGolfer(GolferProfile golfer) {
		this.scoringGolfer = golfer;
		this.scoringGolferName = scoringGolfer.getFirstName() + " "
				+ scoringGolfer.getLastName();
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
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
		this.courseName = course.getClub().getClubName() + " "
				+ course.getCourseName();
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
	@Column(name = "scrcrd_nts")
	public String getScorecardNotes() {
		return this.scorecardNotes;
	}

	/**
	 * 
	 * @param scorecardNotes
	 */
	public void setScorecardNotes(String scorecardNotes) {
		this.scorecardNotes = scorecardNotes;
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
	 * 
	 * @param dateTime
	 */
	private void initDates(DateTime dateTime) {
		this.setScorecardDateDT(dateTime);
		this.setCreatedDateDT(dateTime);
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

	/**
	 * 
	 * @return
	 */
	@Transient
	public int grossScore() {
		// create return value
		int totalScore = 0;
		// iterate the score array and total score
		for (int holeScore : scoreArray) {
			totalScore += holeScore;
		}
		return totalScore;
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	public String encodingSignature() {
		String signature = new String();
		signature += ((golfer != null ? golfer.getId() : ""));
		signature += (String.valueOf(grossScore()));
		signature += (ArrayUtils.toString(scoreArray));
		signature += ((scoringGolfer != null ? scoringGolfer.getId() : ""));
		signature += ((scoringGolfer != null ? scoringGolfer.getProfileHandle()
				: ""));
		return signature;
	}

	/**
	 * @return the golferName
	 */
	@Transient
	public String getGolferName() {
		return golferName;
	}

	/**
	 * @return the scoringGolferName
	 */
	@Transient
	public String getScoringGolferName() {
		return scoringGolferName;
	}

	/**
	 * @return the courseName
	 */
	@Transient
	public String getCourseName() {
		return courseName;
	}
}