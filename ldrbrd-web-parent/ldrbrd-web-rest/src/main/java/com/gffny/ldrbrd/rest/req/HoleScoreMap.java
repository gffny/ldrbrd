/**
 * 
 */
package com.gffny.ldrbrd.rest.req;

import java.util.Map;

import com.gffny.ldrbrd.common.model.impl.HoleShot;

/**
 * @author jdgaffney
 *
 */
public class HoleScoreMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7362997570916477844L;
	
	private boolean includeShots;
	private Map<Integer, Integer> scorecardScoreHoleMap;
	private Map<Integer, HoleShot> scorecardScoreShotMap;
	/**
	 * @return the includeShots
	 */
	public boolean doesIncludeShots() {
		return includeShots;
	}
	/**
	 * @param includeShots the includeShots to set
	 */
	public void setIncludeShots(boolean includeShots) {
		this.includeShots = includeShots;
	}
	/**
	 * @return the scorecardScoreHoleMap
	 */
	public Map<Integer, Integer> getScorecardScoreHoleMap() {
		return scorecardScoreHoleMap;
	}
	/**
	 * @param scorecardScoreHoleMap the scorecardScoreHoleMap to set
	 */
	public void setScorecardScoreHoleMap(Map<Integer, Integer> scorecardScoreHoleMap) {
		this.scorecardScoreHoleMap = scorecardScoreHoleMap;
	}
	/**
	 * @return the scorecardScoreShotMap
	 */
	public Map<Integer, HoleShot> getScorecardScoreShotMap() {
		return scorecardScoreShotMap;
	}
	/**
	 * @param scorecardScoreShotMap the scorecardScoreShotMap to set
	 */
	public void setScorecardScoreShotMap(
			Map<Integer, HoleShot> scorecardScoreShotMap) {
		this.scorecardScoreShotMap = scorecardScoreShotMap;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
