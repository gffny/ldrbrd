/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl.mongo;

/**
 * @author John D. Gaffney | gffny.com
 */
public class HoleScore {

	private String competitionId;
	private String roundNumber;
	private String playerId;
	private int holeNumber;
	private int holeScore;
	private int toPar;
	private int toHandicapPar;
	private int competitionScore;

	public HoleScore(String competitionId, String roundNumber, String playerId,
			int holeNumber, int holeScore, int toPar, int toHandicapPar, int competitionScore) {
		super();
		this.competitionId = competitionId;
		this.roundNumber = roundNumber;
		this.playerId = playerId;
		this.holeNumber = holeNumber;
		this.holeScore = holeScore;
		this.toPar = toPar;
		this.toHandicapPar = toHandicapPar;
		this.competitionScore = competitionScore;
	}

	/**
	 * @param competitionId
	 * @param roundNumber
	 * @param playerId
	 * @param holeNumber
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 * @return
	 */
	public static HoleScore instance(String competitionId, String roundNumber,
			String playerId, int holeNumber, int holeScore, int toPar, int toHandicapPar,
			int competitionScore) {
		return new HoleScore(competitionId, roundNumber, playerId, holeNumber,
				holeScore, toPar, toHandicapPar, competitionScore);
	}

	/**
	 * @return the competitionId
	 */
	public String getCompetitionId() {
		return competitionId;
	}

	/**
	 * @param competitionId
	 *            the competitionId to set
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

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the holeNumber
	 */
	public int getHoleNumber() {
		return holeNumber;
	}

	/**
	 * @param holeNumber
	 *            the holeNumber to set
	 */
	public void setHoleNumber(int holeNumber) {
		this.holeNumber = holeNumber;
	}

	/**
	 * @return the holeScore
	 */
	public int getHoleScore() {
		return holeScore;
	}

	/**
	 * @param holeScore
	 *            the holeScore to set
	 */
	public void setHoleScore(int holeScore) {
		this.holeScore = holeScore;
	}

	/**
	 * @return the toPar
	 */
	public int getToPar() {
		return toPar;
	}

	/**
	 * @param toPar
	 *            the toPar to set
	 */
	public void setToPar(int toPar) {
		this.toPar = toPar;
	}

	/**
	 * @return the toHandicapPar
	 */
	public int getToHandicapPar() {
		return toHandicapPar;
	}

	/**
	 * @param toHandicapPar
	 *            the toHandicapPar to set
	 */
	public void setToHandicapPar(int toHandicapPar) {
		this.toHandicapPar = toHandicapPar;
	}

	/**
	 * @return the competitionScore
	 */
	public int getCompetitionScore() {
		return competitionScore;
	}

	/**
	 * @param competitionScore
	 *            the competitionScore to set
	 */
	public void setCompetitionScore(int competitionScore) {
		this.competitionScore = competitionScore;
	}

}
