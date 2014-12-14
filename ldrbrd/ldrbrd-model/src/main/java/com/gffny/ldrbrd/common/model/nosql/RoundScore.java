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

	public static final String GOLFER_ID = "golferId";

	/** */
	private String golferId;

	/** */
	private String competitionId;

	/** */
	private String competitionRoundId;

	/** */
	private int roundNumber;

	/** */
	private int initialScore;

	/** */
	private int initialToPar;

	/** */
	private int initialToHandicapPar;

	/** */
	private int initialCompetitionScore;

	/** */
	private int hole01Score;

	/** */
	private int hole01ToPar;

	/** */
	private int hole01ToHandicapPar;

	/** */
	private int hole01CompetitionScore;

	/** */
	private int hole02Score;

	/** */
	private int hole02ToPar;

	/** */
	private int hole02ToHandicapPar;

	/** */
	private int hole02CompetitionScore;

	/** */
	private int hole03Score;

	/** */
	private int hole03ToPar;

	/** */
	private int hole03ToHandicapPar;

	/** */
	private int hole03CompetitionScore;

	/** */
	private int hole04Score;

	/** */
	private int hole04ToPar;

	/** */
	private int hole04ToHandicapPar;

	/** */
	private int hole04CompetitionScore;

	/** */
	private int hole05Score;

	/** */
	private int hole05ToPar;

	/** */
	private int hole05ToHandicapPar;

	/** */
	private int hole05CompetitionScore;

	/** */
	private int hole06Score;

	/** */
	private int hole06ToPar;

	/** */
	private int hole06ToHandicapPar;

	/** */
	private int hole06CompetitionScore;

	/** */
	private int hole07Score;

	/** */
	private int hole07ToPar;

	/** */
	private int hole07ToHandicapPar;

	/** */
	private int hole07CompetitionScore;

	/** */
	private int hole08Score;

	/** */
	private int hole08ToPar;

	/** */
	private int hole08ToHandicapPar;

	/** */
	private int hole08CompetitionScore;

	/** */
	private int hole09Score;

	/** */
	private int hole09ToPar;

	/** */
	private int hole09ToHandicapPar;

	/** */
	private int hole09CompetitionScore;

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

	/** */
	private int overviewScore;

	/** */
	private int overviewToPar;

	/** */
	private int overviewToHandicapPar;

	/** */
	private int overviewCompetitionScore;

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
	 * @return the golferId
	 */
	public String getGolferId() {
		return golferId;
	}

	/**
	 * @param golferId
	 *            the golferId to set
	 */
	public void setGolferId(String golferId) {
		this.golferId = golferId;
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
	 * @return the competitionRoundId
	 */
	public String getCompetitionRoundId() {
		return competitionRoundId;
	}

	/**
	 * @param competitionRoundId
	 *            the competitionRoundId to set
	 */
	public void setCompetitionRoundId(String competitionRoundId) {
		this.competitionRoundId = competitionRoundId;
	}

	/**
	 * @return the roundNumber
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

	/**
	 * @param roundNumber
	 *            the roundNumber to set
	 */
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	/**
	 * @return the initialScore
	 */
	public int getInitialScore() {
		return initialScore;
	}

	/**
	 * @param initialScore
	 *            the initialScore to set
	 */
	public void setInitialScore(int initialScore) {
		this.initialScore = initialScore;
	}

	/**
	 * @return the initialToPar
	 */
	public int getInitialToPar() {
		return initialToPar;
	}

	/**
	 * @param initialToPar
	 *            the initialToPar to set
	 */
	public void setInitialToPar(int initialToPar) {
		this.initialToPar = initialToPar;
	}

	/**
	 * @return the initialToHandicapPar
	 */
	public int getInitialToHandicapPar() {
		return initialToHandicapPar;
	}

	/**
	 * @param initialToHandicapPar
	 *            the initialToHandicapPar to set
	 */
	public void setInitialToHandicapPar(int initialToHandicapPar) {
		this.initialToHandicapPar = initialToHandicapPar;
	}

	/**
	 * @return the initialCompetitionScore
	 */
	public int getInitialCompetitionScore() {
		return initialCompetitionScore;
	}

	/**
	 * @param initialCompetitionScore
	 *            the initialCompetitionScore to set
	 */
	public void setInitialCompetitionScore(int initialCompetitionScore) {
		this.initialCompetitionScore = initialCompetitionScore;
	}

	/**
	 * @return the hole01Score
	 */
	public int getHole01Score() {
		return hole01Score;
	}

	/**
	 * @param hole01Score
	 *            the hole01Score to set
	 */
	public void setHole01Score(int hole01Score) {
		this.hole01Score = hole01Score;
	}

	/**
	 * @return the hole01ToPar
	 */
	public int getHole01ToPar() {
		return hole01ToPar;
	}

	/**
	 * @param hole01ToPar
	 *            the hole01ToPar to set
	 */
	public void setHole01ToPar(int hole01ToPar) {
		this.hole01ToPar = hole01ToPar;
	}

	/**
	 * @return the hole01ToHandicapPar
	 */
	public int getHole01ToHandicapPar() {
		return hole01ToHandicapPar;
	}

	/**
	 * @param hole01ToHandicapPar
	 *            the hole01ToHandicapPar to set
	 */
	public void setHole01ToHandicapPar(int hole01ToHandicapPar) {
		this.hole01ToHandicapPar = hole01ToHandicapPar;
	}

	/**
	 * @return the hole01CompetitionScore
	 */
	public int getHole01CompetitionScore() {
		return hole01CompetitionScore;
	}

	/**
	 * @param hole01CompetitionScore
	 *            the hole01CompetitionScore to set
	 */
	public void setHole01CompetitionScore(int hole01CompetitionScore) {
		this.hole01CompetitionScore = hole01CompetitionScore;
	}

	/**
	 * @return the hole02Score
	 */
	public int getHole02Score() {
		return hole02Score;
	}

	/**
	 * @param hole02Score
	 *            the hole02Score to set
	 */
	public void setHole02Score(int hole02Score) {
		this.hole02Score = hole02Score;
	}

	/**
	 * @return the hole02ToPar
	 */
	public int getHole02ToPar() {
		return hole02ToPar;
	}

	/**
	 * @param hole02ToPar
	 *            the hole02ToPar to set
	 */
	public void setHole02ToPar(int hole02ToPar) {
		this.hole02ToPar = hole02ToPar;
	}

	/**
	 * @return the hole02ToHandicapPar
	 */
	public int getHole02ToHandicapPar() {
		return hole02ToHandicapPar;
	}

	/**
	 * @param hole02ToHandicapPar
	 *            the hole02ToHandicapPar to set
	 */
	public void setHole02ToHandicapPar(int hole02ToHandicapPar) {
		this.hole02ToHandicapPar = hole02ToHandicapPar;
	}

	/**
	 * @return the hole02CompetitionScore
	 */
	public int getHole02CompetitionScore() {
		return hole02CompetitionScore;
	}

	/**
	 * @param hole02CompetitionScore
	 *            the hole02CompetitionScore to set
	 */
	public void setHole02CompetitionScore(int hole02CompetitionScore) {
		this.hole02CompetitionScore = hole02CompetitionScore;
	}

	/**
	 * @return the hole03Score
	 */
	public int getHole03Score() {
		return hole03Score;
	}

	/**
	 * @param hole03Score
	 *            the hole03Score to set
	 */
	public void setHole03Score(int hole03Score) {
		this.hole03Score = hole03Score;
	}

	/**
	 * @return the hole03ToPar
	 */
	public int getHole03ToPar() {
		return hole03ToPar;
	}

	/**
	 * @param hole03ToPar
	 *            the hole03ToPar to set
	 */
	public void setHole03ToPar(int hole03ToPar) {
		this.hole03ToPar = hole03ToPar;
	}

	/**
	 * @return the hole03ToHandicapPar
	 */
	public int getHole03ToHandicapPar() {
		return hole03ToHandicapPar;
	}

	/**
	 * @param hole03ToHandicapPar
	 *            the hole03ToHandicapPar to set
	 */
	public void setHole03ToHandicapPar(int hole03ToHandicapPar) {
		this.hole03ToHandicapPar = hole03ToHandicapPar;
	}

	/**
	 * @return the hole03CompetitionScore
	 */
	public int getHole03CompetitionScore() {
		return hole03CompetitionScore;
	}

	/**
	 * @param hole03CompetitionScore
	 *            the hole03CompetitionScore to set
	 */
	public void setHole03CompetitionScore(int hole03CompetitionScore) {
		this.hole03CompetitionScore = hole03CompetitionScore;
	}

	/**
	 * @return the hole04Score
	 */
	public int getHole04Score() {
		return hole04Score;
	}

	/**
	 * @param hole04Score
	 *            the hole04Score to set
	 */
	public void setHole04Score(int hole04Score) {
		this.hole04Score = hole04Score;
	}

	/**
	 * @return the hole04ToPar
	 */
	public int getHole04ToPar() {
		return hole04ToPar;
	}

	/**
	 * @param hole04ToPar
	 *            the hole04ToPar to set
	 */
	public void setHole04ToPar(int hole04ToPar) {
		this.hole04ToPar = hole04ToPar;
	}

	/**
	 * @return the hole04ToHandicapPar
	 */
	public int getHole04ToHandicapPar() {
		return hole04ToHandicapPar;
	}

	/**
	 * @param hole04ToHandicapPar
	 *            the hole04ToHandicapPar to set
	 */
	public void setHole04ToHandicapPar(int hole04ToHandicapPar) {
		this.hole04ToHandicapPar = hole04ToHandicapPar;
	}

	/**
	 * @return the hole04CompetitionScore
	 */
	public int getHole04CompetitionScore() {
		return hole04CompetitionScore;
	}

	/**
	 * @param hole04CompetitionScore
	 *            the hole04CompetitionScore to set
	 */
	public void setHole04CompetitionScore(int hole04CompetitionScore) {
		this.hole04CompetitionScore = hole04CompetitionScore;
	}

	/**
	 * @return the hole05Score
	 */
	public int getHole05Score() {
		return hole05Score;
	}

	/**
	 * @param hole05Score
	 *            the hole05Score to set
	 */
	public void setHole05Score(int hole05Score) {
		this.hole05Score = hole05Score;
	}

	/**
	 * @return the hole05ToPar
	 */
	public int getHole05ToPar() {
		return hole05ToPar;
	}

	/**
	 * @param hole05ToPar
	 *            the hole05ToPar to set
	 */
	public void setHole05ToPar(int hole05ToPar) {
		this.hole05ToPar = hole05ToPar;
	}

	/**
	 * @return the hole05ToHandicapPar
	 */
	public int getHole05ToHandicapPar() {
		return hole05ToHandicapPar;
	}

	/**
	 * @param hole05ToHandicapPar
	 *            the hole05ToHandicapPar to set
	 */
	public void setHole05ToHandicapPar(int hole05ToHandicapPar) {
		this.hole05ToHandicapPar = hole05ToHandicapPar;
	}

	/**
	 * @return the hole05CompetitionScore
	 */
	public int getHole05CompetitionScore() {
		return hole05CompetitionScore;
	}

	/**
	 * @param hole05CompetitionScore
	 *            the hole05CompetitionScore to set
	 */
	public void setHole05CompetitionScore(int hole05CompetitionScore) {
		this.hole05CompetitionScore = hole05CompetitionScore;
	}

	/**
	 * @return the hole06Score
	 */
	public int getHole06Score() {
		return hole06Score;
	}

	/**
	 * @param hole06Score
	 *            the hole06Score to set
	 */
	public void setHole06Score(int hole06Score) {
		this.hole06Score = hole06Score;
	}

	/**
	 * @return the hole06ToPar
	 */
	public int getHole06ToPar() {
		return hole06ToPar;
	}

	/**
	 * @param hole06ToPar
	 *            the hole06ToPar to set
	 */
	public void setHole06ToPar(int hole06ToPar) {
		this.hole06ToPar = hole06ToPar;
	}

	/**
	 * @return the hole06ToHandicapPar
	 */
	public int getHole06ToHandicapPar() {
		return hole06ToHandicapPar;
	}

	/**
	 * @param hole06ToHandicapPar
	 *            the hole06ToHandicapPar to set
	 */
	public void setHole06ToHandicapPar(int hole06ToHandicapPar) {
		this.hole06ToHandicapPar = hole06ToHandicapPar;
	}

	/**
	 * @return the hole06CompetitionScore
	 */
	public int getHole06CompetitionScore() {
		return hole06CompetitionScore;
	}

	/**
	 * @param hole06CompetitionScore
	 *            the hole06CompetitionScore to set
	 */
	public void setHole06CompetitionScore(int hole06CompetitionScore) {
		this.hole06CompetitionScore = hole06CompetitionScore;
	}

	/**
	 * @return the hole07Score
	 */
	public int getHole07Score() {
		return hole07Score;
	}

	/**
	 * @param hole07Score
	 *            the hole07Score to set
	 */
	public void setHole07Score(int hole07Score) {
		this.hole07Score = hole07Score;
	}

	/**
	 * @return the hole07ToPar
	 */
	public int getHole07ToPar() {
		return hole07ToPar;
	}

	/**
	 * @param hole07ToPar
	 *            the hole07ToPar to set
	 */
	public void setHole07ToPar(int hole07ToPar) {
		this.hole07ToPar = hole07ToPar;
	}

	/**
	 * @return the hole07ToHandicapPar
	 */
	public int getHole07ToHandicapPar() {
		return hole07ToHandicapPar;
	}

	/**
	 * @param hole07ToHandicapPar
	 *            the hole07ToHandicapPar to set
	 */
	public void setHole07ToHandicapPar(int hole07ToHandicapPar) {
		this.hole07ToHandicapPar = hole07ToHandicapPar;
	}

	/**
	 * @return the hole07CompetitionScore
	 */
	public int getHole07CompetitionScore() {
		return hole07CompetitionScore;
	}

	/**
	 * @param hole07CompetitionScore
	 *            the hole07CompetitionScore to set
	 */
	public void setHole07CompetitionScore(int hole07CompetitionScore) {
		this.hole07CompetitionScore = hole07CompetitionScore;
	}

	/**
	 * @return the hole08Score
	 */
	public int getHole08Score() {
		return hole08Score;
	}

	/**
	 * @param hole08Score
	 *            the hole08Score to set
	 */
	public void setHole08Score(int hole08Score) {
		this.hole08Score = hole08Score;
	}

	/**
	 * @return the hole08ToPar
	 */
	public int getHole08ToPar() {
		return hole08ToPar;
	}

	/**
	 * @param hole08ToPar
	 *            the hole08ToPar to set
	 */
	public void setHole08ToPar(int hole08ToPar) {
		this.hole08ToPar = hole08ToPar;
	}

	/**
	 * @return the hole08ToHandicapPar
	 */
	public int getHole08ToHandicapPar() {
		return hole08ToHandicapPar;
	}

	/**
	 * @param hole08ToHandicapPar
	 *            the hole08ToHandicapPar to set
	 */
	public void setHole08ToHandicapPar(int hole08ToHandicapPar) {
		this.hole08ToHandicapPar = hole08ToHandicapPar;
	}

	/**
	 * @return the hole08CompetitionScore
	 */
	public int getHole08CompetitionScore() {
		return hole08CompetitionScore;
	}

	/**
	 * @param hole08CompetitionScore
	 *            the hole08CompetitionScore to set
	 */
	public void setHole08CompetitionScore(int hole08CompetitionScore) {
		this.hole08CompetitionScore = hole08CompetitionScore;
	}

	/**
	 * @return the hole09Score
	 */
	public int getHole09Score() {
		return hole09Score;
	}

	/**
	 * @param hole09Score
	 *            the hole09Score to set
	 */
	public void setHole09Score(int hole09Score) {
		this.hole09Score = hole09Score;
	}

	/**
	 * @return the hole09ToPar
	 */
	public int getHole09ToPar() {
		return hole09ToPar;
	}

	/**
	 * @param hole09ToPar
	 *            the hole09ToPar to set
	 */
	public void setHole09ToPar(int hole09ToPar) {
		this.hole09ToPar = hole09ToPar;
	}

	/**
	 * @return the hole09ToHandicapPar
	 */
	public int getHole09ToHandicapPar() {
		return hole09ToHandicapPar;
	}

	/**
	 * @param hole09ToHandicapPar
	 *            the hole09ToHandicapPar to set
	 */
	public void setHole09ToHandicapPar(int hole09ToHandicapPar) {
		this.hole09ToHandicapPar = hole09ToHandicapPar;
	}

	/**
	 * @return the hole09CompetitionScore
	 */
	public int getHole09CompetitionScore() {
		return hole09CompetitionScore;
	}

	/**
	 * @param hole09CompetitionScore
	 *            the hole09CompetitionScore to set
	 */
	public void setHole09CompetitionScore(int hole09CompetitionScore) {
		this.hole09CompetitionScore = hole09CompetitionScore;
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
	 * @return the overviewScore
	 */
	public int getOverviewScore() {
		return overviewScore;
	}

	/**
	 * @param overviewScore
	 *            the overviewScore to set
	 */
	public void setOverviewScore(int overviewScore) {
		this.overviewScore = overviewScore;
	}

	/**
	 * @return the overviewToPar
	 */
	public int getOverviewToPar() {
		return overviewToPar;
	}

	/**
	 * @param overviewToPar
	 *            the overviewToPar to set
	 */
	public void setOverviewToPar(int overviewToPar) {
		this.overviewToPar = overviewToPar;
	}

	/**
	 * @return the overviewToHandicapPar
	 */
	public int getOverviewToHandicapPar() {
		return overviewToHandicapPar;
	}

	/**
	 * @param overviewToHandicapPar
	 *            the overviewToHandicapPar to set
	 */
	public void setOverviewToHandicapPar(int overviewToHandicapPar) {
		this.overviewToHandicapPar = overviewToHandicapPar;
	}

	/**
	 * @return the overviewCompetitionScore
	 */
	public int getOverviewCompetitionScore() {
		return overviewCompetitionScore;
	}

	/**
	 * @param overviewCompetitionScore
	 *            the overviewCompetitionScore to set
	 */
	public void setOverviewCompetitionScore(int overviewCompetitionScore) {
		this.overviewCompetitionScore = overviewCompetitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole01ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole01Score = holeScore;
		this.hole01ToPar = toPar;
		this.hole01ToHandicapPar = toHandicapPar;
		this.hole01CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole02ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole02Score = holeScore;
		this.hole02ToPar = toPar;
		this.hole02ToHandicapPar = toHandicapPar;
		this.hole02CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole03ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole03Score = holeScore;
		this.hole03ToPar = toPar;
		this.hole03ToHandicapPar = toHandicapPar;
		this.hole03CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole04ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole04Score = holeScore;
		this.hole04ToPar = toPar;
		this.hole04ToHandicapPar = toHandicapPar;
		this.hole04CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole05ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole05Score = holeScore;
		this.hole05ToPar = toPar;
		this.hole05ToHandicapPar = toHandicapPar;
		this.hole05CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole06ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole06Score = holeScore;
		this.hole06ToPar = toPar;
		this.hole06ToHandicapPar = toHandicapPar;
		this.hole06CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole07ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole07Score = holeScore;
		this.hole07ToPar = toPar;
		this.hole07ToHandicapPar = toHandicapPar;
		this.hole07CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole08ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole08Score = holeScore;
		this.hole08ToPar = toPar;
		this.hole08ToHandicapPar = toHandicapPar;
		this.hole08CompetitionScore = competitionScore;
	}

	/**
	 * 
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public void setHole09ScoreDetails(int holeScore, int toPar,
			int toHandicapPar, int competitionScore) {
		this.hole09Score = holeScore;
		this.hole09ToPar = toPar;
		this.hole09ToHandicapPar = toHandicapPar;
		this.hole09CompetitionScore = competitionScore;
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
