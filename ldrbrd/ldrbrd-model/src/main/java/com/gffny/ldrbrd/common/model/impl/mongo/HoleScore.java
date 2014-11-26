/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl.mongo;

<<<<<<< HEAD
import java.util.Date;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public class HoleScore extends CommonUUIDEntity {

	/** */
	private static final long serialVersionUID = 40224322767247057L;

	private String competitionId;
	private String competitionRoundId;
	private String roundNumber;
	private String courseId;
	private Date dateTime;
	private int holeNumber;
	private int holeScore;
	private int holeCompetitionScore;
	private int holeToPar;
	private int holeToHandicapPar;

	/**
	 * @return the competitionId
	 */
	public String getCompetitionId() {
		return competitionId;
=======
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
>>>>>>> d4087c8090050451e69742c2c740357dfe64c26d
	}

	/**
	 * @param competitionId
<<<<<<< HEAD
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
=======
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
>>>>>>> d4087c8090050451e69742c2c740357dfe64c26d
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
<<<<<<< HEAD
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
=======
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
>>>>>>> d4087c8090050451e69742c2c740357dfe64c26d
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
<<<<<<< HEAD
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
=======
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
>>>>>>> d4087c8090050451e69742c2c740357dfe64c26d
	}

}
