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
@NamedQueries({
		@NamedQuery(name = CompetitionRoundScore.FIND_BY_SCORECARD_ID, query = "SELECT c FROM CompetitionRoundScore c WHERE c.scorecard.id = :scorecardId"),
		@NamedQuery(name = CompetitionRoundScore.FIND_BY_COMPETITION_ROUND_ID_AND_GOLFER_ID, query = "SELECT c FROM CompetitionRoundScore c WHERE c.competitionRound.id = :competitionRoundId AND c.competitionEntry.id = (SELECT ce.id FROM CompetitionEntry ce WHERE ce.golfer.id = :golferId)") })
@Entity
@Table(name = Constant.DB_TABLE_COMPETITION_ROUND_SCORE)
public class CompetitionRoundScore extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = -6188698053405294852L;

	/** */
	public static final String FIND_BY_SCORECARD_ID = "CompetitionRoundScore.FIND_BY_SCORECARD_ID";

	/** */
	public static final String FIND_BY_COMPETITION_ROUND_ID_AND_GOLFER_ID = "CompetitionRoundScore.FIND_BY_COMPETITION_ROUND_ID_AND_GOLFER_ID";

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
	private boolean complete;

	/** */
	public CompetitionRoundScore() {
		// hibernate required zero-arg constructor
	}

	/**
	 * 
	 * @param competitionEntry
	 * @param scorecard
	 */
	public static CompetitionRoundScore instance(
			CompetitionEntry competitionEntry, Scorecard scorecard,
			CompetitionRound competitionRound) {
		CompetitionRoundScore crs = new CompetitionRoundScore();
		crs.setCompetitionEntry(competitionEntry);
		crs.setScorecard(scorecard);
		crs.setCompetitionRound(competitionRound);
		return crs;
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

	/**
	 * @return the complete
	 */
	@Column(name = "is_complete", columnDefinition = "BIT", length = 1)
	public boolean isComplete() {
		return complete;
	}

	/**
	 * @param complete
	 *            the complete to set
	 */
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
}
