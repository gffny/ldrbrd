/**
 * 
 */
package com.gffny.ldrbrd.common.model.nosql;

import org.mongodb.morphia.annotations.Entity;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 */
@Entity
public class RoundScore extends CommonUUIDEntity {

	/** */
	private static final long serialVersionUID = 7669972073554258805L;

	/** */
	private String competitionId;

	/** */
	private String roundNumber;

	/**
	 * 
	 */
	public RoundScore() {
	}

	/**
	 * @param competitionId
	 * @param roundNumber
	 */
	public RoundScore(String competitionId, String roundNumber) {

	}

	/**
	 * @return
	 */
	public String getCompetitionId() {
		return competitionId;
	}

	/**
	 * @param competitionId
	 */
	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	/**
	 * @return the roundNumber
	 */
	public String getRoundNumber() {
		return roundNumber;
	}

	/**
	 * @param roundNumber
	 *            the roundNumber to set
	 */
	public void setRoundNumber(String roundNumber) {
		this.roundNumber = roundNumber;
	}
}
