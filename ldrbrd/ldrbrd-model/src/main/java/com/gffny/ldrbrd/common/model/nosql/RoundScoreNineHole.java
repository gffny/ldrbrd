/**
 * 
 */
package com.gffny.ldrbrd.common.model.nosql;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public class RoundScoreNineHole extends CommonUUIDEntity {

	/** */
	private static final long serialVersionUID = -8942302619908952890L;
	/** */
	protected String golferId;
	/** */
	protected String leaderboardDisplayName;
	/** */
	protected String competitionId;
	/** */
	protected String competitionRoundId;
	/** */
	protected int roundNumber;
	/** */
	protected int initialScore;
	/** */
	protected int initialToPar;
	/** */
	protected int initialToHandicapPar;
	/** */
	protected int initialCompetitionScore;
	/** */
	protected int hole01Score;
	/** */
	protected int hole01ToPar;
	/** */
	protected int hole01ToHandicapPar;
	/** */
	protected int hole01CompetitionScore;
	/** */
	protected int hole02Score;
	/** */
	protected int hole02ToPar;
	/** */
	protected int hole02ToHandicapPar;
	/** */
	protected int hole02CompetitionScore;
	/** */
	protected int hole03Score;
	/** */
	protected int hole03ToPar;
	/** */
	protected int hole03ToHandicapPar;
	/** */
	protected int hole03CompetitionScore;
	/** */
	protected int hole04Score;
	/** */
	protected int hole04ToPar;
	/** */
	protected int hole04ToHandicapPar;
	/** */
	protected int hole04CompetitionScore;
	/** */
	protected int hole05Score;
	/** */
	protected int hole05ToPar;
	/** */
	protected int hole05ToHandicapPar;
	/** */
	protected int hole05CompetitionScore;
	/** */
	protected int hole06Score;
	/** */
	protected int hole06ToPar;
	/** */
	protected int hole06ToHandicapPar;
	/** */
	protected int hole06CompetitionScore;
	/** */
	protected int hole07Score;
	/** */
	protected int hole07ToPar;
	/** */
	protected int hole07ToHandicapPar;
	/** */
	protected int hole07CompetitionScore;
	/** */
	protected int hole08Score;
	/** */
	protected int hole08ToPar;
	/** */
	protected int hole08ToHandicapPar;
	/** */
	protected int hole08CompetitionScore;
	/** */
	protected int hole09Score;
	/** */
	protected int hole09ToPar;
	/** */
	protected int hole09ToHandicapPar;
	/** */
	protected int hole09CompetitionScore;
	/** */
	protected int roundScore;
	/** */
	protected int roundToPar;
	/** */
	protected int roundToHandicapPar;
	/** */
	protected int roundCompetitionScore;
	/** */
	protected int overviewScore;
	/** */
	protected int overviewToPar;
	/** */
	protected int overviewToHandicapPar;
	/** */
	protected int overviewCompetitionScore;

	/**
	 * 
	 */
	public RoundScoreNineHole() {
		super();
	}

	/**
	 * @param competitionId
	 * @param roundNumber
	 */
	public static RoundScoreNineHole instance(String golferId,
			String competitionId, int roundNumber) {
		RoundScoreNineHole instance = new RoundScoreNineHole();
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
	public static RoundScoreNineHole instance(String golferId,
			String competitionRoundId) {
		RoundScoreNineHole instance = new RoundScoreNineHole();
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
	 * @return the golferId
	 */
	public String getLeaderboardDisplayName() {
		return leaderboardDisplayName;
	}

	/**
	 * @param golferId
	 *            the golferId to set
	 */
	public void setLeaderboardDisplayName(String leaderboardDisplayName) {
		this.leaderboardDisplayName = leaderboardDisplayName;
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
	 * @return the roundScore
	 */
	public int getRoundScore() {
		return roundScore;
	}

	/**
	 * @param roundScore
	 *            the roundScore to set
	 */
	public void setRoundScore(int roundScore) {
		this.roundScore = roundScore;
	}

	/**
	 * @return the roundToPar
	 */
	public int getRoundToPar() {
		return roundToPar;
	}

	/**
	 * @param roundToPar
	 *            the roundToPar to set
	 */
	public void setRoundToPar(int roundToPar) {
		this.roundToPar = roundToPar;
	}

	/**
	 * @return the roundToHandicapPar
	 */
	public int getRoundToHandicapPar() {
		return roundToHandicapPar;
	}

	/**
	 * @param roundToHandicapPar
	 *            the roundToHandicapPar to set
	 */
	public void setRoundToHandicapPar(int roundToHandicapPar) {
		this.roundToHandicapPar = roundToHandicapPar;
	}

	/**
	 * @return the roundCompetitionScore
	 */
	public int getRoundCompetitionScore() {
		return roundCompetitionScore;
	}

	/**
	 * @param roundCompetitionScore
	 *            the roundCompetitionScore to set
	 */
	public void setRoundCompetitionScore(int roundCompetitionScore) {
		this.roundCompetitionScore = roundCompetitionScore;
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

}