/**
 * 
 */
package com.gffny.ldrbrd.rest.req;

import java.util.Map;

/**
 * @author jdgaffney
 *
 */
public class ScorecardRequest {

	private String courseId;
	private int roundNumber;
	private String scoreKeeperId;
	private String golferArray[];
	private String scorecardArray[];
	private String competitionId;
	private String timestamp;

	Map<String, HoleScoreMap> scorecardHoleScoreMap;

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * 
	 * @return
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

	/**
	 * @return the scoreKeeperId
	 */
	public String getScoreKeeperId() {
		return scoreKeeperId;
	}

	/**
	 * @return the golferArray
	 */
	public String[] getGolferArray() {
		return golferArray;
	}

	/**
	 * @return the golferArray
	 */
	public String[] getScorecardArray() {
		return scorecardArray;
	}

	/**
	 * @return the competitionId
	 */
	public String getCompetitionId() {
		return competitionId;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, HoleScoreMap> getScorecardHoleScoreMap() {
		return scorecardHoleScoreMap;
	}
	
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * 
	 * @param roundNumber
	 */
	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = roundNumber;
	}

	/**
	 * @param scoreKeeperId the scoreKeeperId to set
	 */
	public void setScoreKeeperId(String scoreKeeperId) {
		this.scoreKeeperId = scoreKeeperId;
	}

	/**
	 * @param golferArray the golferArray to set
	 */
	public void setScorecardArray(String[] scorecardArray) {
		this.scorecardArray = scorecardArray;
	}

	/**
	 * @param golferArray the golferArray to set
	 */
	public void setGolferArray(String[] golferArray) {
		this.golferArray = golferArray;
	}

	/**
	 * @param competitionId the competitionId to set
	 */
	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	/**
	 * 
	 * @param holeScoreList
	 */
	public void setScorecardHoleScoreMap(Map<String, HoleScoreMap> scorecardHoleScoreMap) {
		this.scorecardHoleScoreMap = scorecardHoleScoreMap;
	}
	
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	} 
}