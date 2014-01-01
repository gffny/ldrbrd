/**
 * 
 */
package com.gffny.ldrbrd.rest.resp;

import java.util.Map;

import com.gffny.ldrbrd.web.model.JSONable;

/**
 * @author jdgaffney
 *
 */
public class ScorecardResponse implements JSONable{
	
	private String competitionId;
	private Map<String, String> golferScorecardMap;

	/**
	 * @return the competitionId
	 */
	public String getCompetitionId() {
		return competitionId;
	}

	/**
	 * @return the golferScorecardMap
	 */
	public Map<String, String> getGolferScorecardMap() {
		return golferScorecardMap;
	}

	/**
	 * @param competitionId the competitionId to set
	 */
	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}

	/**
	 * @param golferScorecardMap the golferScorecardMap to set
	 */
	public void setGolferScorecardMap(Map<String, String> golferScorecardMap) {
		this.golferScorecardMap = golferScorecardMap;
	}
}
