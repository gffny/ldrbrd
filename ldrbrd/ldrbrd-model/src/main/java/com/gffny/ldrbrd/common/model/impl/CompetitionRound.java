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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;

/**
 * @author John D. Gaffney | gffny.com
 */

// TODO REFACTOR TO EXTRACT INTERFACE COMMON TO NON COMPETITION ROUND AND COMP
// ROUND
@NamedQueries({ @NamedQuery(name = CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR, query = "SELECT cr FROM CompetitionRound cr WHERE cr.roundNumber = :roundNumber AND cr.competition.id  = :competitionId") })
@Entity
@Table(name = Constant.DB_TABLE_COMPETITION_ROUND)
public class CompetitionRound extends CommonIDEntity {

	/** */
	public static final String FIND_BY_COMP_ID_AND_RND_NMBR = "find_by_comp_id_and_rnd_nmbr";

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

	/** TODO change to an enum */
	private int scoringFormat;

	/**
	 * @param competition
	 * @param courseDocumentId
	 * @param competitionRoundDT
	 * @return
	 */
	public static CompetitionRound createNewCompetitionRound(Competition competition,
			DateTime startDate, int roundNumber, String courseDocumentId) {
		return new CompetitionRound(competition, startDate, roundNumber, courseDocumentId);
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
	private CompetitionRound(Competition competition, DateTime startDate, int roundNumber,
			String courseDocumentId) {
		this.competition = competition;
		this.roundNumber = roundNumber;
		this.courseDocumentId = courseDocumentId;
		this.startDate = startDate;
	}

	/**
	 * @return
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competition_id", nullable = false)
	@ForeignKey(name = "id")
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
	@JsonIgnore
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
		return this.startDate.toDate();
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
		return this.teeTime.toDate();
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
	 * @return
	 */
	@Column(name = "scoring_format")
	public int getScoringFormat() {
		return this.scoringFormat;
	}

	/**
	 * @param scoringFormat
	 */
	public void setScoringFormat(int scoringFormat) {
		this.scoringFormat = scoringFormat;
	}
}
