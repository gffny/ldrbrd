/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NamedQueries;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;

/**
 * @author John Gaffney (john@gffny.com) Jul 30, 2012
 */

@NamedQueries({
// @NamedQuery(name = Scorecard.FIND_SCORECARD_BY_COMPETITION_ROUND_AND_GOLFER, query =
// "SELECT s FROM Scorecard s WHERE s.competitionRound.id = :competitionRoundId AND s.golfer.id  = :golferId"),
// TODO Check that the scorecards are returned by in the right order
// @NamedQuery(name = Scorecard.FIND_SCORECARDS_BY_GOLFER_ID, query =
// "SELECT s FROM Scorecard s WHERE s.golfer.id  = :golferId ORDER BY s.scorecardDate") })
})
@Entity
@Table(name = Constant.DB_TABLE_SCORECARD)
public class Scorecard extends CommonIDEntity {

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
	public static final String FIND_SCORECARDS_BY_GOLFER_ID = "findScorecardsByGolferId";

	/** */
	private Golfer golfer;

	/** */
	private String courseDocumentId;

	/** */
	private Course course;

	/** */
	private DateTime roundDate;

	/** */
	private int handicap;

	/** */
	private String conditions;

	/** */
	private String scorecardNotes;

	/** */
	private List<Integer> holeScoreArray = new ArrayList<Integer>();

	/**
	 * Factory method to create a instance of Scorecard
	 * 
	 * @param golferId
	 * @param courseId
	 * @param handicap
	 * @return
	 */
	public static Scorecard createNewScorecard(Golfer golfer, Course course, int handicap) {
		Scorecard scorecard = new Scorecard(golfer, course, handicap);
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
	 * @param golfer
	 * @param scoreKeeper
	 * @param course
	 * @param handicap
	 */
	private Scorecard(Golfer golfer, Course course, int handicap) {
		this.golfer = golfer;
		this.course = course;
		this.courseDocumentId = course.getId();
		this.handicap = handicap;
	}

	/**
	 * @return
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "golfer_id", nullable = false)
	@ForeignKey(name = "id")
	public Golfer getGolfer() {
		return this.golfer;
	}

	/**
	 * @param golferId
	 */
	public void setGolfer(Golfer golfer) {
		this.golfer = golfer;
	}

	/**
	 * @return
	 */
	@Column(name = "course_did")
	public String getCourseDocumentId() {
		return this.courseDocumentId;
	}

	/**
	 * @param courseId
	 */
	public void setCourseDocumentId(String courseDocumentId) {
		this.courseDocumentId = courseDocumentId;
	}

	/**
	 * TODO populate this field in the competition service
	 * 
	 * @return
	 */
	@Transient
	public Course getCourse() {
		return this.course;
	}

	/**
	 * @param course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 
	 */
	@Column(name = "round_date")
	public Date getRoundDate() {
		return this.roundDate.toDate();
	}

	/**
	 * @param scorecardDate
	 */
	public void setRoundDate(Date scorecardDate) {
		this.roundDate = new DateTime(scorecardDate);
	}

	/**
	 * @return
	 */
	@Transient
	public DateTime getRoundDateDT() {
		return this.roundDate;
	}

	/**
	 * @param scorecardDateTime
	 */
	@Transient
	public void setRoundDateDT(DateTime scorecardDateTime) {
		this.roundDate = scorecardDateTime;
	}

	/**
	 * @return
	 */
	@Column(name = "handicap")
	public int getHandicap() {
		return handicap;
	};

	/**
	 * @param handicap
	 */
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

	/**
	 * 
	 */
	@Column(name = "conditions")
	public String getConditions() {
		return this.conditions;
	}

	/**
	 * @param scorecardNotes
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/**
	 * 
	 */
	@Column(name = "scorecard_notes")
	public String getScorecardNotes() {
		return this.scorecardNotes;
	}

	/**
	 * @param scorecardNotes
	 */
	public void setScorecardNotes(String scorecardNotes) {
		this.scorecardNotes = scorecardNotes;
	}

	/**
	 * @return
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = Constant.DB_TABLE_HOLE_SCORE, joinColumns = @JoinColumn(name = "scorecard_id"))
	@Column(name = "score")
	public List<Integer> getHoleScoreArray() {
		return this.holeScoreArray;
	}

	/**
	 * @param holeScoreArray
	 */
	public void setHoleScoreArray(List<Integer> holeScoreArray) {
		this.holeScoreArray = holeScoreArray;
	}

	/**
	 * @param dateTime
	 */
	private void initDates(DateTime dateTime) {
		this.setRoundDateDT(dateTime);
		this.setCreatedDateDT(dateTime);
	}

	/**
	 * TODO recreate this toString method when class is full
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Transient
	@Override
	public String toString() {
		return "Scorecard [golfer=" + golfer + ", courseDocumentId=" + courseDocumentId
				+ ", course=" + course + ", roundDate=" + roundDate + ", handicap=" + handicap
				+ ", conditions=" + conditions + ", scorecardNotes=" + scorecardNotes
				+ ", holeScoreArray=" + holeScoreArray + "]";
	}

	/** TODO move to a scorecard utils class */

	// /**
	// * @return
	// */
	// @Transient
	// public int grossScore() {
	// // create return value
	// int totalScore = 0;
	// // iterate the score array and total score
	// for (int holeScore : scoreArray) {
	// totalScore += holeScore;
	// }
	// return totalScore;
	// }
	//
	// /**
	// * @return
	// */
	// @Transient
	// public String encodingSignature() {
	// String signature = new String();
	// signature += ((golfer != null ? golfer.getId() : ""));
	// signature += (String.valueOf(grossScore()));
	// signature += (ArrayUtils.toString(scoreArray));
	// signature += ((scoringGolfer != null ? scoringGolfer.getId() : ""));
	// signature += ((scoringGolfer != null ? scoringGolfer.getProfileHandle() : ""));
	// return signature;
	// }
	//
	// /**
	// * @return the golferName
	// */
	// @Transient
	// public String getGolferName() {
	// return golferName;
	// }
	//
	// /**
	// * @return the scoringGolferName
	// */
	// @Transient
	// public String getScoringGolferName() {
	// return scoringGolferName;
	// }
	//
	// /**
	// * @return the courseName
	// */
	// @Transient
	// public String getCourseName() {
	// return courseName;
	// }
	//
	// /**
	// *
	// */
	// public void signScorecard(String signature) {
	// // TODO research what I could do to "sign" a scorecard
	// // (look at adding a "isSubmitted" bool to the entity)
	// // (signing time stamp)
	// this.scorecardNotes = signature;
	// }
	//
	// /**
	// *
	// */
	// public void submitScorecard(String submission) {
	// // TODO research what I should do to submit a scorecard
	// // (look at adding a "isSubmitted" bool to the entity)
	// // (signing time stamp)
	// this.scorecardNotes = submission;
	// }
}