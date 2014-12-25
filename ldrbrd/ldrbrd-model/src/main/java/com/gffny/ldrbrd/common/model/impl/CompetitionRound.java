/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.nosql.Course;

/**
 * @author John D. Gaffney | gffny.com
 */

// TODO REFACTOR TO EXTRACT INTERFACE COMMON TO NON COMPETITION ROUND AND COMP
// ROUND
@NamedQueries({
		@NamedQuery(name = CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR, query = "SELECT cr FROM CompetitionRound cr WHERE cr.roundNumber = :roundNumber AND cr.competition.id  = :competitionId"),
		@NamedQuery(name = CompetitionRound.FIND_BY_COMP_ID, query = "SELECT cr FROM CompetitionRound cr WHERE cr.competition.id  = :competitionId"),
		@NamedQuery(name = CompetitionRound.FIND_BY_SCORECARD_ID_AND_RND_NMBR, query = "SELECT cr FROM CompetitionRound cr WHERE cr.roundNumber = :roundNumber AND cr.competition.id = (SELECT ce.competition.id FROM CompetitionEntry ce WHERE ce.id = (SELECT crs.competitionEntry.id FROM CompetitionRoundScore crs WHERE crs.scorecard.id = :scorecardId))"),
		@NamedQuery(name = CompetitionRound.FIND_BY_GOLFER_ID_IN_COMPETITION_ENTRY, query = "SELECT cr FROM CompetitionRound cr WHERE cr.complete = false AND cr.competition.id IN (SELECT ce.competition.id FROM CompetitionEntry ce WHERE ce.golfer.id = :golferId) ORDER BY cr.startDate ASC"),
		@NamedQuery(name = CompetitionRound.FIND_NON_COMPLETE_BY_GOLFER_ID_IN_COMPETITION_ENTRY, query = "SELECT cr FROM CompetitionRound cr WHERE cr.complete = false AND cr.competition.id IN (SELECT ce.competition.id FROM CompetitionEntry ce WHERE ce.golfer.id = :golferId) AND cr.id NOT IN (SELECT crs.competitionRound.id FROM CompetitionRoundScore crs WHERE crs.competitionEntry.id IN (SELECT ce.id FROM CompetitionEntry ce WHERE ce.golfer.id = :golferId))") })
@Entity
@Table(name = Constant.DB_TABLE_COMPETITION_ROUND)
public class CompetitionRound extends CommonIDEntity {

	/** */
	public static final String FIND_BY_COMP_ID_AND_RND_NMBR = "CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR";

	/** */
	public static final String FIND_BY_GOLFER_ID_IN_COMPETITION_ENTRY = "CompetitionRound.FIND_BY_GOLFER_ID_IN_COMPETITION_ENTRY";

	/** */
	public static final String FIND_NON_COMPLETE_BY_GOLFER_ID_IN_COMPETITION_ENTRY = "CompetitionRound.FIND_NON_COMPLETE_BY_GOLFER_ID_IN_COMPETITION_ENTRY";

	/** */
	public static final String FIND_BY_COMP_ID = "CompetitionRound.FIND_BY_COMP_ID";

	/** */
	public static final String FIND_BY_SCORECARD_ID_AND_RND_NMBR = "CompetitionRound.FIND_BY_SCORECARD_ID_AND_RND_NMBR";

	/** */
	public static final String FIND_BY_COMPETITION_ID_LIST = "CompetitionRound.FIND_BY_COMPETITION_ID_LIST";

	/** */
	private static final long serialVersionUID = -8135318864907425168L;

	/** */
	private Competition competition;

	/** */
	private int roundNumber;

	/** */
	private String courseDocumentId;

	/** */
	private Course course;

	/** */
	private DateTime startDate;

	/** */
	private DateTime teeTime;

	/** */
	private boolean isComplete;

	/** TODO change to an enum */
	private ScoringFormat scoringFormat;

	/**
	 * @param competition
	 * @param courseDocumentId
	 * @param competitionRoundDT
	 * @return
	 */
	public static CompetitionRound createNewCompetitionRound(
			Competition competition, DateTime startDate, int roundNumber,
			String courseDocumentId) {
		return new CompetitionRound(competition, startDate, roundNumber,
				courseDocumentId);
	}

	/** */
	public CompetitionRound() {
		// hibernate required non-private zero-argument constructor
	}

	/**
	 * @param competition
	 * @param startDate
	 * @param roundNumber
	 */
	private CompetitionRound(Competition competition, DateTime startDate,
			int roundNumber, String courseDocumentId) {
		this.competition = competition;
		this.roundNumber = roundNumber;
		this.courseDocumentId = courseDocumentId;
		this.startDate = startDate;
	}

	/**
	 * @return
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competition_id", nullable = false)
	public Competition getCompetition() {
		return this.competition;
	}

	/**
	 * @param competition
	 */
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	/**
	 * @return
	 */
	@Column(name = "round_number")
	public int getRoundNumber() {
		return this.roundNumber;
	}

	/**
	 * @param roundNumber
	 */
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	/**
	 * @return
	 */
	@Column(name = "course_did")
	public String getCourseDocumentId() {
		return this.courseDocumentId;
	}

	/**
	 * @param courseDocumentId
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
	 * @return
	 */
	@Column(name = "start_date")
	public Date getStartDate() {
		return this.startDate != null ? this.startDate.toDate() : null;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = new DateTime(startDate);
	}

	/**
	 * @return
	 */
	@Transient
	public DateTime getStartDateDT() {
		return this.startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDateDT(DateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return
	 */
	@Column(name = "initial_tee_time")
	public Date getTeeTime() {
		return this.teeTime != null ? this.teeTime.toDate() : null;
	}

	/**
	 * @param startDate
	 */
	public void setTeeTime(Date teeTime) {
		this.teeTime = new DateTime(teeTime);
	}

	/**
	 * @return
	 */
	@Transient
	public DateTime getTeeTimeDT() {
		return this.teeTime;
	}

	/**
	 * @param startDate
	 */
	public void setTeeTimeDT(DateTime teeTime) {
		this.teeTime = teeTime;
	}

	/**
	 * @return the complete
	 */
	@Column(name = "is_complete")
	public boolean isComplete() {
		return this.isComplete;
	}

	/**
	 * @param complete
	 *            the complete to set
	 */
	public void setComplete(boolean complete) {
		this.isComplete = complete;
	}

	/**
	 * @return
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "scoring_format_id", nullable = false)
	public ScoringFormat getScoringFormat() {
		return this.scoringFormat;
	}

	/**
	 * @param scoringFormat
	 */
	public void setScoringFormat(ScoringFormat scoringFormat) {
		this.scoringFormat = scoringFormat;
	}
}
