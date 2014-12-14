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
	 * @param golferId
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract RoundScore findGolferRoundScoreByCompetitionRound(
			String golferId, String competitionId, String roundNumber)
			throws PersistenceException;

	/**
	 * 
	 * @param golferId
	 * @param competitionRoundId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract RoundScore findGolferRoundScoreByCompetitionRound(
			String golferId, String competitionRoundId)
			throws PersistenceException;

}