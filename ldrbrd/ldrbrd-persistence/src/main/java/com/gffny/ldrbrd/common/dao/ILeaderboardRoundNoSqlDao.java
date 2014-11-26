package com.gffny.ldrbrd.common.dao;

import java.util.List;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.mongo.RoundScore;

public interface ILeaderboardRoundNoSqlDao extends GenericNoSqlDao<RoundScore> {

	/**
	 * @param clubId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<RoundScore> listLeaderboardByCompetition(String competitionId)
			throws PersistenceException;

	/**
	 * @param string
	 * @return
	 */
	public abstract RoundScore findLeaderboardByCompetitionAndRound(
			String competitionId, String roundNumber) throws PersistenceException;

	/**
	 * @param competitionId
	 * @param roundNumber
	 * @param playerId
	 * @param holeNumber
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 * @return
	 * @throws PersistenceException
	 */
	public abstract boolean scoreHole(String competitionId, String roundNumber, String playerId,
			int holeNumber, int holeScore, int toPar, int toHandicapPar, int competitionScore)
			throws PersistenceException;
}