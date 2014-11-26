/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl.mongo;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 */
public class LeaderboardEntry extends CommonUUIDEntity {

	private String competitionId;
	private String golferId;
	private String toParScore;
	private String toHandicapParScore;
	private String overallScore;
	private String competitionScore;

	/**
	 * 
	 */
	public LeaderboardEntry() {
		// TODO Auto-generated constructor stub
	}

}
