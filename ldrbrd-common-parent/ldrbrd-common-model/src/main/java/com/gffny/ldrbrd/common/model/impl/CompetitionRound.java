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

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author jdgaffney
 * 
 */

// TODO REFACTOR TO EXTRACT INTERFACE COMMON TO NON COMPETITION ROUND AND COMP
// ROUND
@NamedQueries({ @NamedQuery(name = CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR, query = "SELECT cr FROM CompetitionRound cr WHERE cr.roundNumber = :roundNumber AND cr.competition.id  = :competitionId") })
@Entity
@Table(name = "t_competition_round")
public class CompetitionRound extends CommonUUIDEntity {

	/**
	 * 
	 */
	public static final String FIND_BY_COMP_ID_AND_RND_NMBR = "find_by_comp_id_and_rnd_nmbr";

	/**
	 * 
	 */
	private static final long serialVersionUID = -8135318864907425168L;

	/**
	 * 
	 */
	protected DateTime roundDate;

	/**
	 * 
	 */
	protected Course course;

	/**
	 * 
	 */
	private int roundNumber;

	/**
	 * 
	 */
	private Competition competition;

	/**
	 * 
	 * @param competition
	 * @param course
	 * @param competitionRoundDT
	 * @return
	 */
	public static CompetitionRound createNewCompetitionRound(
			Competition competition, DateTime roundDate, Integer roundNumber,
			Course course) {
		return new CompetitionRound(competition, roundDate, roundNumber, course);
	}

	/**
	 * 
	 */
	public CompetitionRound() {
		// hibernate required non-private zero-argument constructor
	}

	/**
	 * 
	 * @param competition
	 * @param roundDate
	 * @param roundNumber
	 */
	private CompetitionRound(Competition competition, DateTime roundDate,
			Integer roundNumber, Course course) {
		this.competition = competition;
		this.roundNumber = getDefaultNotNullValue(roundNumber, 0);
		this.course = course;
		this.roundDate = roundDate;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "rnd_nmbr")
	public int getRoundNumber() {
		return this.roundNumber;
	}

	/**
	 * 
	 * @param roundNumber
	 */
	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = getDefaultNotNullValue(roundNumber, 0);
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cmpttn_id", nullable = false)
	@ForeignKey(name = "id")
	public Competition getCompetition() {
		return this.competition;
	}

	/**
	 * 
	 * @param competition
	 */
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "crs_id", nullable = false)
	@ForeignKey(name = "id")
	public Course getCourse() {
		return this.course;
	}

	/**
	 * 
	 * @param course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "rnd_dt")
	public Date getRoundDate() {
		return this.roundDate.toDate();
	}

	/**
	 * 
	 * @param roundDate
	 */
	public void setRoundDate(Date roundDate) {
		this.roundDate = new DateTime(roundDate);
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	public DateTime getRoundDateDT() {
		return this.roundDate;
	}

	/**
	 * 
	 * @param roundDate
	 */
	public void setRoundDateDT(DateTime roundDate) {
		this.roundDate = roundDate;
	}
}
