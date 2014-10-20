/**
 * 
 */
package com.gffny.ldrbrd.common.dao;

import com.gffny.ldrbrd.common.exception.ActiveScorecardServiceException;
import com.gffny.ldrbrd.common.exception.PersistenceException;

/**
 * @author John D. Gaffney | gffny.com
 */
public interface IScorecardDao {

	/**
	 * @param scorecardId
	 * @param holeNumber
	 * @param holeScore
	 */
	void scoreHole(int scorecardId, int holeNumber, int holeScore, String holeId)
			throws PersistenceException;

	/**
	 * @param scorecardId
	 * @return
	 * @throws PersistenceException
	 */
	boolean isScorecardActive(int scorecardId) throws PersistenceException;

	/**
	 * @param id
	 * @return
	 */
	boolean hasActiveScoreacard(int golferId) throws ActiveScorecardServiceException;

	/**
	 * @param scorecardId
	 * @param signature
	 */
	boolean signScorecard(String scorecardId, String signature) throws PersistenceException;

}
