package com.gffny.ldrbrd.common.dao.nosql;

import java.util.List;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.nosql.RoundScore;

public interface IRoundScoreNoSqlDao extends GenericNoSqlDao<RoundScore> {

	/**
	 * 
	 * @param competitionId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<RoundScore> listRoundScoreByCompetition(
			String competitionId) throws PersistenceException;

	/**
	 * 
	 * @param golferId
	 * @param competitionId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<RoundScore> listGolferRoundScoreByCompetition(
			String golferId, String competitionId) throws PersistenceException;

	/**
	 * 
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<RoundScore> listRoundScoreByCompetitionRound(
			String competitionId, String roundNumber)
			throws PersistenceException;

	/**
	 * 
	 * @param competitionRoundId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<RoundScore> listRoundScoreByCompetitionRound(
			String competitionRoundId) throws PersistenceException;

	/**
	 * 
	 * @param golerId
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract RoundScore findGolferRoundScoreByCompetitionRound(
			String golerId, String competitionId, String roundNumber)
			throws PersistenceException;

	/**
	 * 
	 * @param golerId
	 * @param competitionRoundId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract RoundScore findGolferRoundScoreByCompetitionRound(
			String golerId, String competitionRoundId)
			throws PersistenceException;

	/**
	 * 
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
	public abstract RoundScore scoreHole(String competitionId,
			String roundNumber, String playerId, int holeNumber, int holeScore,
			int toPar, int toHandicapPar, int competitionScore)
			throws PersistenceException;
}