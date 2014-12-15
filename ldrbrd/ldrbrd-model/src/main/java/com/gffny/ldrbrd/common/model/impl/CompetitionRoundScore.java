/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;

/**
 * @author John D. Gaffney | gffny.com
 */
@NamedQueries(@NamedQuery(name = CompetitionRoundScore.FIND_BY_SCORECARD_ID, query = "select c from CompetitionRoundScore c where c.scorecard.id = :scorecardId"))
@Entity
@Table(name = Constant.DB_TABLE_COMPETITION_ROUND_SCORE)
public class CompetitionRoundScore extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = -6188698053405294852L;

	/** */
	public static final String FIND_BY_SCORECARD_ID = "FIND_BY_SCORECARD_ID";

	/** */
	private CompetitionEntry competitionEntry;

	/** */
	private Scorecard scorecard;

	/** */
	private CompetitionRound competitionRound;

	/** */
	private String golferSignature;

	/** */
	private String scorerSignature;

	/** */
	public CompetitionRoundScore() {
		// hibernate required zero-arg constructor
	}

	/**
	 * 
	 * @param competitionEntry
	 * @param scorecard
	 */
	public CompetitionRoundScore(CompetitionEntry competitionEntry,
			Scorecard scorecard, CompetitionRound competitionRound) {
		this.competitionEntry = competitionEntry;
		this.scorecard = scorecard;
		this.competitionRound = competitionRound;
	}

	/**
	 * @return
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competition_entry_id", nullable = false)
	public CompetitionEntry getCompetitionEntry() {
		return this.competitionEntry;
	}

	/**
	 * @param competitionEntry
	 */
	public void setCompetitionEntry(CompetitionEntry competitionEntry) {
		this.competitionEntry = competitionEntry;
	}

	/**
	 * @return
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "scorecard_id", nullable = false)
	public Scorecard getScorecard() {
		return this.scorecard;
	}

	/**
	 * @param scorecard
	 */
	public void setScorecard(Scorecard scorecard) {
		this.scorecard = scorecard;
	}

	/**
	 * @return the roundNumber
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competition_round_id", nullable = false)
	public CompetitionRound getCompetitionRound() {
		return competitionRound;
	}

	/**
	 * @param roundNumber
	 *            the roundNumber to set
	 */
	public void setCompetitionRound(CompetitionRound competitionRound) {
		this.competitionRound = competitionRound;
	}

	/**
	 * @return the golferSignature
	 */
	@Column(name = "golfer_signature")
	public String getGolferSignature() {
		return golferSignature;
	}

	/**
	 * @param golferSignature
	 *            the golferSignature to set
	 */
	public void setGolferSignature(String golferSignature) {
		this.golferSignature = golferSignature;
	}

	/**
	 * @return the scorerSignature
	 */
	@Column(name = "scorer_signature")
	public String getScorerSignature() {
		return scorerSignature;
	}

	/**
	 * @param scorerSignature
	 *            the scorerSignature to set
	 */
	public void setScorerSignature(String scorerSignature) {
		this.scorerSignature = scorerSignature;
	}
}
