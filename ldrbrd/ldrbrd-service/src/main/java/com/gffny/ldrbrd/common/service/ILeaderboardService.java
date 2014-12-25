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
	public void startCompetitionRound(Golfer golfer,
			CompetitionRound competitionRound, int handicap)
			throws ServiceException;

	/**
	 * 
	 * @param golfer
	 * @param competitionRound
	 * @param holeNumber
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 * @throws ServiceException
	 */
	public abstract void publishHoleScore(Golfer golfer,
			CompetitionRound competitionRound, int holeNumber, int holeScore,
			int toPar, int toHandicapPar, int competitionScore)
			throws ServiceException;

	/**
	 * @param golfer
	 * @param cr
	 * @return
	 */
	public abstract boolean hasLeaderboardBeenStarted(Golfer golfer,
			CompetitionRound cr);

}
