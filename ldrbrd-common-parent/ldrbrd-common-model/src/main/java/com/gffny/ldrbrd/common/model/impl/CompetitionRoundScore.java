/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;

/**
 * @author John D. Gaffney | gffny.com
 */
@Entity
@Table(name = Constant.DB_TABLE_COMPETITION_ROUND_SCORE)
public class CompetitionRoundScore extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = -6188698053405294852L;

	/** */
	private CompetitionEntry competitionEntry;

	/** */
	private Scorecard scorecard;

	/** */
	public CompetitionRoundScore() {
		// hibernate required zero-arg constructor
	}

	/**
	 * @return
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competition_entry_id", nullable = false)
	@ForeignKey(name = "id")
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
	@ForeignKey(name = "id")
	public Scorecard getScorecard() {
		return this.scorecard;
	}

	/**
	 * @param scorecard
	 */
	public void setScorecard(Scorecard scorecard) {
		this.scorecard = scorecard;
	}
}
