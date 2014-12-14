/**
 * 
 */
package com.gffny.ldrbrd.common.model.nosql;

import java.util.Date;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public class HoleScore extends CommonUUIDEntity {

	/** */
	private static final long serialVersionUID = 40224322767247057L;

	// FIELD CONSTANTS
	public static String COMPETITION_ID = "competitionId";
	public static String COMPETITION_ROUND_ID = "competitionRoundId";
	public static String ROUND_NUMBER = "roundNumber";
	public static String COURSE_ID = "courseId";
	public static String GOLFER_ID = "golferId";
	public static String DATE_TIME = "dateTime";
	public static String HOLE_NUMBER = "holeNumber";
	public static String HOLE_SCORE = "holeScore";
	public static String HOLE_COMPETITION_SCORE = "holeCompetitionScore";
	public static String HOLE_TO_PAR = "holeToPar";
	public static String HOLE_TO_HANDICAP_PAR = "holeToHandicapPar";
	public static String OVERVIEW_SCORE = "overviewScore";
	public static String OVERVIEW_TO_PAR = "overviewToPar";
	public static String OVERVIEW_TO_HANDICAP_PAR = "overviewToHandicapPar";
	public static String OVERVIEW_COMPETITION_ROUND = "overviewCompetitionScore";

	// FIELDS
	private String competitionId;
	private String competitionRoundId;
	private String roundNumber;
	private String courseId;
	private String golferId;
	private Date dateTime;
	private int holeNumber;
	private int holeScore;
	private int holeCompetitionScore;
	private int holeToPar;
	private int holeToHandicapPar;
	private int overviewScore;
	private int overviewToPar;
	private int overviewToHandicapPar;
	private int overviewCompetitionScore;

	/**
	 * 
	 */
	private HoleScore() {
		super();
	}

	/**
	 * @param competitionId
	 * @param competitionRoundId
	 * @param roundNumber
	 * @param courseId
	 * @param golferId
	 * @param dateTime
	 * @param holeNumber
	 * @param holeScore
	 * @param holeCompetitionScore
	 * @param holeToPar
	 * @param holeToHandicapPar
	 * @param overviewScore
	 * @param overviewToPar
	 * @param overviewToHandicapPar
	 * @param overviewCompetitionScore
	 */
	public static HoleScore instance(String competitionId,
			String competitionRoundId, String roundNumber, String courseId,
			String golferId, Date dateTime, int holeNumber, int holeScore,
			int holeCompetitionScore, int holeToPar, int holeToHandicapPar,
			int overviewScore, int overviewToPar, int overviewToHandicapPar,
			int overviewCompetitionScore) {
		HoleScore instance = new HoleScore();
		instance.setCompetitionId(competitionId);
		instance.setCompetitionRoundId(competitionRoundId);
		instance.setRoundNumber(roundNumber);
		instance.setCourseId(courseId);
		instance.setGolferId(golferId);
		instance.setDateTime(dateTime);
		instance.setHoleNumber(holeNumber);
		instance.setHoleScore(holeScore);
		instance.setHoleCompetitionScore(holeCompetitionScore);
		instance.setHoleToPar(holeToPar);
		instance.setHoleToHandicapPar(holeToHandicapPar);
		instance.setOverviewScore(overviewScore);
		instance.setOverviewToPar(overviewToPar);
		instance.setOverviewToHandicapPar(overviewToHandicapPar);
		instance.setOverviewCompetitionScore(overviewCompetitionScore);
		return instance;
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
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime
	 *            the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return this.golferId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setGolferId(String playerId) {
		this.golferId = playerId;
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
	 * 
	 * @return the holeCompetitionScore
	 */
	public int getHoleCompetitionScore() {
		return holeCompetitionScore;
	}

	/**
	 * @param holeCompetitionScore
	 *            the holeCompetitionScore to set
	 */
	public void setHoleCompetitionScore(int holeCompetitionScore) {
		this.holeCompetitionScore = holeCompetitionScore;
	}

	/**
	 * @return the holeToPar
	 */
	public int getHoleToPar() {
		return holeToPar;
	}

	/**
	 * @param holeToPar
	 *            the holeToPar to set
	 */
	public void setHoleToPar(int holeToPar) {
		this.holeToPar = holeToPar;
	}

	/**
	 * @return the holeToHandicapPar
	 */
	public int getHoleToHandicapPar() {
		return holeToHandicapPar;
	}

	/**
	 * @param holeToHandicapPar
	 *            the holeToHandicapPar to set
	 */
	public void setHoleToHandicapPar(int holeToHandicapPar) {
		this.holeToHandicapPar = holeToHandicapPar;
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

}
