/**
 * 
 */
package com.gffny.ldrbrd.common.model.nosql;

import org.mongodb.morphia.annotations.Entity;

/**
 * @author John D. Gaffney | gffny.com
 */
@Entity
public class RoundScore extends RoundScoreNineHole {

	/** */
	private static final long serialVersionUID = 7669972073554258805L;

	public static final String GOLFER_ID = "golferId";

	/** */
	private int hole10Score;

	/** */
	private int hole10ToPar;

	/** */
	private int hole10ToHandicapPar;

	/** */
	private int hole10CompetitionScore;

	/** */
	private int hole11Score;

	/** */
	private int hole11ToPar;

	/** */
	private int hole11ToHandicapPar;

	/** */
	private int hole11CompetitionScore;

	/** */
	private int hole12Score;

	/** */
	private int hole12ToPar;

	/** */
	private int hole12ToHandicapPar;

	/** */
	private int hole12CompetitionScore;

	/** */
	private int hole13Score;

	/** */
	private int hole13ToPar;

	/** */
	private int hole13ToHandicapPar;

	/** */
	private int hole13CompetitionScore;

	/** */
	private int hole14Score;

	/** */
	private int hole14ToPar;

	/** */
	private int hole14ToHandicapPar;

	/** */
	private int hole14CompetitionScore;

	/** */
	private int hole15Score;

	/** */
	private int hole15ToPar;

	/** */
	private int hole15ToHandicapPar;

	/** */
	private int hole15CompetitionScore;

	/** */
	private int hole16Score;

	/** */
	private int hole16ToPar;

	/** */
	private int hole16ToHandicapPar;

	/** */
	private int hole16CompetitionScore;

	/** */
	private int hole17Score;

	/** */
	private int hole17ToPar;

	/** */
	private int hole17ToHandicapPar;

	/** */
	private int hole17CompetitionScore;

	/** */
	private int hole18Score;

	/** */
	private int hole18ToPar;

	/** */
	private int hole18ToHandicapPar;

	/** */
	private int hole18CompetitionScore;

	/**
	 * 
	 */
	public RoundScore() {
	}

	/**
	 * @param competitionId
	 * @param roundNumber
	 */
	public static RoundScore instance(String golferId, String competitionId,
			int roundNumber) {
		RoundScore instance = new RoundScore();
		instance.setGolferId(golferId);
		instance.setCompetitionId(competitionId);
		instance.setRoundNumber(roundNumber);
		return instance;
	}

	/**
	 * 
	 * @param golferId
	 * @param competitionRoundId
	 * @return
	 */
	public static RoundScore instance(String golferId, String competitionRoundId) {
		RoundScore instance = new RoundScore();
		instance.setGolferId(golferId);
		instance.setCompetitionRoundId(competitionRoundId);
		return instance;
	}

	/**
	 * @return the hole10Score
	 */
	public int getHole10Score() {
		return hole10Score;
	}

	/**
	 * @param hole10Score
	 *            the hole10Score to set
	 */
	public void setHole10Score(int hole10Score) {
		this.hole10Score = hole10Score;
	}

	/**
	 * @return the hole10ToPar
	 */
	public int getHole10ToPar() {
		return hole10ToPar;
	}

	/**
	 * @param hole10ToPar
	 *            the hole10ToPar to set
	 */
	public void setHole10ToPar(int hole10ToPar) {
		this.hole10ToPar = hole10ToPar;
	}

	/**
	 * @return the hole10ToHandicapPar
	 */
	public int getHole10ToHandicapPar() {
		return hole10ToHandicapPar;
	}

	/**
	 * @param hole10ToHandicapPar
	 *            the hole10ToHandicapPar to set
	 */
	public void setHole10ToHandicapPar(int hole10ToHandicapPar) {
		this.hole10ToHandicapPar = hole10ToHandicapPar;
	}

	/**
	 * @return the hole10CompetitionScore
	 */
	public int getHole10CompetitionScore() {
		return hole10CompetitionScore;
	}

	/**
	 * @param hole10CompetitionScore
	 *            the hole10CompetitionScore to set
	 */
	public void setHole10CompetitionScore(int hole10CompetitionScore) {
		this.hole10CompetitionScore = hole10CompetitionScore;
	}

	/**
	 * @return the hole11Score
	 */
	public int getHole11Score() {
		return hole11Score;
	}

	/**
	 * @param hole11Score
	 *            the hole11Score to set
	 */
	public void setHole11Score(int hole11Score) {
		this.hole11Score = hole11Score;
	}

	/**
	 * @return the hole11ToPar
	 */
	public int getHole11ToPar() {
		return hole11ToPar;
	}

	/**
	 * @param hole11ToPar
	 *            the hole11ToPar to set
	 */
	public void setHole11ToPar(int hole11ToPar) {
		this.hole11ToPar = hole11ToPar;
	}

	/**
	 * @return the hole11ToHandicapPar
	 */
	public int getHole11ToHandicapPar() {
		return hole11ToHandicapPar;
	}

	/**
	 * @param hole11ToHandicapPar
	 *            the hole11ToHandicapPar to set
	 */
	public void setHole11ToHandicapPar(int hole11ToHandicapPar) {
		this.hole11ToHandicapPar = hole11ToHandicapPar;
	}

	/**
	 * @return the hole11CompetitionScore
	 */
	public int getHole11CompetitionScore() {
		return hole11CompetitionScore;
	}

	/**
	 * @param hole11CompetitionScore
	 *            the hole11CompetitionScore to set
	 */
	public void setHole11CompetitionScore(int hole11CompetitionScore) {
		this.hole11CompetitionScore = hole11CompetitionScore;
	}

	/**
	 * @return the hole12Score
	 */
	public int getHole12Score() {
		return hole12Score;
	}

	/**
	 * @param hole12Score
	 *            the hole12Score to set
	 */
	public void setHole12Score(int hole12Score) {
		this.hole12Score = hole12Score;
	}

	/**
	 * @return the hole12ToPar
	 */
	public int getHole12ToPar() {
		return hole12ToPar;
	}

	/**
	 * @param hole12ToPar
	 *            the hole12ToPar to set
	 */
	public void setHole12ToPar(int hole12ToPar) {
		this.hole12ToPar = hole12ToPar;
	}

	/**
	 * @return the hole12ToHandicapPar
	 */
	public int getHole12ToHandicapPar() {
		return hole12ToHandicapPar;
	}

	/**
	 * @param hole12ToHandicapPar
	 *            the hole12ToHandicapPar to set
	 */
	public void setHole12ToHandicapPar(int hole12ToHandicapPar) {
		this.hole12ToHandicapPar = hole12ToHandicapPar;
	}

	/**
	 * @return the hole12CompetitionScore
	 */
	public int getHole12CompetitionScore() {
		return hole12CompetitionScore;
	}

	/**
	 * @param hole12CompetitionScore
	 *            the hole12CompetitionScore to set
	 */
	public void setHole12CompetitionScore(int hole12CompetitionScore) {
		this.hole12CompetitionScore = hole12CompetitionScore;
	}

	/**
	 * @return the hole13Score
	 */
	public int getHole13Score() {
		return hole13Score;
	}

	/**
	 * @param hole13Score
	 *            the hole13Score to set
	 */
	public void setHole13Score(int hole13Score) {
		this.hole13Score = hole13Score;
	}

	/**
	 * @return the hole13ToPar
	 */
	public int getHole13ToPar() {
		return hole13ToPar;
	}

	/**
	 * @param hole13ToPar
	 *            the hole13ToPar to set
	 */
	public void setHole13ToPar(int hole13ToPar) {
		this.hole13ToPar = hole13ToPar;
	}

	/**
	 * @return the hole13ToHandicapPar
	 */
	public int getHole13ToHandicapPar() {
		return hole13ToHandicapPar;
	}

	/**
	 * @param hole13ToHandicapPar
	 *            the hole13ToHandicapPar to set
	 */
	public void setHole13ToHandicapPar(int hole13ToHandicapPar) {
		this.hole13ToHandicapPar = hole13ToHandicapPar;
	}

	/**
	 * @return the hole13CompetitionScore
	 */
	public int getHole13CompetitionScore() {
		return hole13CompetitionScore;
	}

	/**
	 * @param hole13CompetitionScore
	 *            the hole13CompetitionScore to set
	 */
	public void setHole13CompetitionScore(int hole13CompetitionScore) {
		this.hole13CompetitionScore = hole13CompetitionScore;
	}

	/**
	 * @return the hole14Score
	 */
	public int getHole14Score() {
		return hole14Score;
	}

	/**
	 * @param hole14Score
	 *            the hole14Score to set
	 */
	public void setHole14Score(int hole14Score) {
		this.hole14Score = hole14Score;
	}

	/**
	 * @return the hole14ToPar
	 */
	public int getHole14ToPar() {
		return hole14ToPar;
	}

	/**
	 * @param hole14ToPar
	 *            the hole14ToPar to set
	 */
	public void setHole14ToPar(int hole14ToPar) {
		this.hole14ToPar = hole14ToPar;
	}

	/**
	 * @return the hole14ToHandicapPar
	 */
	public int getHole14ToHandicapPar() {
		return hole14ToHandicapPar;
	}

	/**
	 * @param hole14ToHandicapPar
	 *            the hole14ToHandicapPar to set
	 */
	public void setHole14ToHandicapPar(int hole14ToHandicapPar) {
		this.hole14ToHandicapPar = hole14ToHandicapPar;
	}

	/**
	 * @return the hole14CompetitionScore
	 */
	public int getHole14CompetitionScore() {
		return hole14CompetitionScore;
	}

	/**
	 * @param hole14CompetitionScore
	 *            the hole14CompetitionScore to set
	 */
	public void setHole14CompetitionScore(int hole14CompetitionScore) {
		this.hole14CompetitionScore = hole14CompetitionScore;
	}

	/**
	 * @return the hole15Score
	 */
	public int getHole15Score() {
		return hole15Score;
	}

	/**
	 * @param hole15Score
	 *            the hole15Score to set
	 */
	public void setHole15Score(int hole15Score) {
		this.hole15Score = hole15Score;
	}

	/**
	 * @return the hole15ToPar
	 */
	public int getHole15ToPar() {
		return hole15ToPar;
	}

	/**
	 * @param hole15ToPar
	 *            the hole15ToPar to set
	 */
	public void setHole15ToPar(int hole15ToPar) {
		this.hole15ToPar = hole15ToPar;
	}

	/**
	 * @return the hole15ToHandicapPar
	 */
	public int getHole15ToHandicapPar() {
		return hole15ToHandicapPar;
	}

	/**
	 * @param hole15ToHandicapPar
	 *            the hole15ToHandicapPar to set
	 */
	public void setHole15ToHandicapPar(int hole15ToHandicapPar) {
		this.hole15ToHandicapPar = hole15ToHandicapPar;
	}

	/**
	 * @return the hole15CompetitionScore
	 */
	public int getHole15CompetitionScore() {
		return hole15CompetitionScore;
	}

	/**
	 * @param hole15CompetitionScore
	 *            the hole15CompetitionScore to set
	 */
	public void setHole15CompetitionScore(int hole15CompetitionScore) {
		this.hole15CompetitionScore = hole15CompetitionScore;
	}

	/**
	 * @return the hole16Score
	 */
	public int getHole16Score() {
		return hole16Score;
	}

	/**
	 * @param hole16Score
	 *            the hole16Score to set
	 */
	public void setHole16Score(int hole16Score) {
		this.hole16Score = hole16Score;
	}

	/**
	 * @return the hole16ToPar
	 */
	public int getHole16ToPar() {
		return hole16ToPar;
	}

	/**
	 * @param hole16ToPar
	 *            the hole16ToPar to set
	 */
	public void setHole16ToPar(int hole16ToPar) {
		this.hole16ToPar = hole16ToPar;
	}

	/**
	 * @return the hole16ToHandicapPar
	 */
	public int getHole16ToHandicapPar() {
		return hole16ToHandicapPar;
	}

	/**
	 * @param hole16ToHandicapPar
	 *            the hole16ToHandicapPar to set
	 */
	public void setHole16ToHandicapPar(int hole16ToHandicapPar) {
		this.hole16ToHandicapPar = hole16ToHandicapPar;
	}

	/**
	 * @return the hole16CompetitionScore
	 */
	public int getHole16CompetitionScore() {
		return hole16CompetitionScore;
	}

	/**
	 * @param hole16CompetitionScore
	 *            the hole16CompetitionScore to set
	 */
	public void setHole16CompetitionScore(int hole16CompetitionScore) {
		this.hole16CompetitionScore = hole16CompetitionScore;
	}

	/**
	 * @return the hole17Score
	 */
	public int getHole17Score() {
		return hole17Score;
	}

	/**
	 * @param hole17Score
	 *            the hole17Score to set
	 */
	public void setHole17Score(int hole17Score) {
		this.hole17Score = hole17Score;
	}

	/**
	 * @return the hole17ToPar
	 */
	public int getHole17ToPar() {
		return hole17ToPar;
	}

	/**
	 * @param hole17ToPar
	 *            the hole17ToPar to set
	 */
	public void setHole17ToPar(int hole17ToPar) {
		this.hole17ToPar = hole17ToPar;
	}

	/**
	 * @return the hole17ToHandicapPar
	 */
	public int getHole17ToHandicapPar() {
		return hole17ToHandicapPar;
	}

	/**
	 * @param hole17ToHandicapPar
	 *            the hole17ToHandicapPar to set
	 */
	public void setHole17ToHandicapPar(int hole17ToHandicapPar) {
		this.hole17ToHandicapPar = hole17ToHandicapPar;
	}

	/**
	 * @return the hole17CompetitionScore
	 */
	public int getHole17CompetitionScore() {
		return hole17CompetitionScore;
	}

	/**
	 * @param hole17CompetitionScore
	 *            the hole17CompetitionScore to set
	 */
	public void setHole17CompetitionScore(int hole17CompetitionScore) {
		this.hole17CompetitionScore = hole17CompetitionScore;
	}

	/**
	 * @return the hole18Score
	 */
	public int getHole18Score() {
		return hole18Score;
	}

	/**
	 * @param hole18Score
	 *            the hole18Score to set
	 */
	public void setHole18Score(int hole18Score) {
		this.hole18Score = hole18Score;
	}

	/**
	 * @return the hole18ToPar
	 */
	public int getHole18ToPar() {
		return hole18ToPar;
	}

	/**
	 * @param hole18ToPar
	 *            the hole18ToPar to set
	 */
	public void setHole18ToPar(int hole18ToPar) {
		this.hole18ToPar = hole18ToPar;
	}

	/**
	 * @return the hole18ToHandicapPar
	 */
	public int getHole18ToHandicapPar() {
		return hole18ToHandicapPar;
	}

	/**
	 * @param hole18ToHandicapPar
	 *            the hole18ToHandicapPar to set
	 */
	public void setHole18ToHandicapPar(int hole18ToHandicapPar) {
		this.hole18ToHandicapPar = hole18ToHandicapPar;
	}

	/**
	 * @return the hole18CompetitionScore
	 */
	public int getHole18CompetitionScore() {
		return hole18CompetitionScore;
	}

	/**
	 * @param hole18CompetitionScore
	 *            the hole18CompetitionScore to set
	 */
	public void setHole18CompetitionScore(int hole18CompetitionScore) {
		this.hole18CompetitionScore = hole18CompetitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole10ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole10Score = holeScore;
		this.hole10ToPar = toPar;
		this.hole10ToHandicapPar = toHandicapPar;
		this.hole10CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole11ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole11Score = holeScore;
		this.hole11ToPar = toPar;
		this.hole11ToHandicapPar = toHandicapPar;
		this.hole11CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole12ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole12Score = holeScore;
		this.hole12ToPar = toPar;
		this.hole12ToHandicapPar = toHandicapPar;
		this.hole12CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole13ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole13Score = holeScore;
		this.hole13ToPar = toPar;
		this.hole13ToHandicapPar = toHandicapPar;
		this.hole13CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole14ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole14Score = holeScore;
		this.hole14ToPar = toPar;
		this.hole14ToHandicapPar = toHandicapPar;
		this.hole14CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole15ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole15Score = holeScore;
		this.hole15ToPar = toPar;
		this.hole15ToHandicapPar = toHandicapPar;
		this.hole15CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole16ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole16Score = holeScore;
		this.hole16ToPar = toPar;
		this.hole16ToHandicapPar = toHandicapPar;
		this.hole16CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole17ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole17Score = holeScore;
		this.hole17ToPar = toPar;
		this.hole17ToHandicapPar = toHandicapPar;
		this.hole17CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole18ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole18Score = holeScore;
		this.hole18ToPar = toPar;
		this.hole18ToHandicapPar = toHandicapPar;
		this.hole18CompetitionScore = competitionScore;
	}

}
