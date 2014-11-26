/**
 * 
 */
package com.gffny.ldrbrd.common.service;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Golfer;

/**
 * Service class to update leaderboard and handle leaderboard queries
 * 
 * @author John D. Gaffney | gffny.com
 */
public interface ILeaderboardService {

	/**
	 * @param golfer
	 * @param handicap
	 * @param competitionRound
	 * @throws ServiceException
	 */
	public void startCompetitionRound(Golfer golfer, int handicap, CompetitionRound competitionRound)
			throws ServiceException;

}
