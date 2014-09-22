/**
 * 
 */
package com.gffny.ldrbrd.common.model.web;

import java.io.Serializable;

/**
 * @author John D. Gaffney | gffny.com
 */
public class ScorecardSubmission implements Serializable {

	/**	 */
	private static final long serialVersionUID = -9023750442520074581L;

	/**	 */
	private int[] scorecardArray;

	/**	 */
	private String scorecardId;

	/**
	 * @return the scorecardArray
	 */
	public int[] getScorecardArray() {
		return scorecardArray;
	}

	/**
	 * @param scorecardArray
	 *            the scorecardArray to set
	 */
	public void setScorecardArray(int[] scorecardArray) {
		this.scorecardArray = scorecardArray;
	}

	/**
	 * @return the scorecardId
	 */
	public String getScorecardId() {
		return scorecardId;
	}

	/**
	 * @param scorecardId
	 *            the scorecardId to set
	 */
	public void setScorecardId(String scorecardId) {
		this.scorecardId = scorecardId;
	}

}
