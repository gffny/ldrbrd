/**
 * 
 */
package com.gffny.ldrbrd.common.dao.nosql;

import java.util.List;

import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.nosql.HoleScore;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public interface IHoleScoreNoSqlDao extends GenericNoSqlDao<HoleScore> {

	/**
	 * Using competitionId and roundNumber keys
	 * 
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<HoleScore> listHoleScoreByCompetitionRound(
			String competitionId, String roundNumber)
			throws PersistenceException;

	/**
	 * Using competitionRoundId key
	 * 
	 * @param competitionRoundId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<HoleScore> listHoleScoreByCompetitionRound(
			String competitionRoundId) throws PersistenceException;

	/**
	 * Using competitionId and roundNumber keys
	 * 
	 * @param competitionId
	 * @param roundNumber
	 * @param holeNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<HoleScore> listHoleScoreByCompetitionRoundAndHole(
			String competitionId, String roundNumber, int holeNumber)
			throws PersistenceException;

	/**
	 * Using competitionRoundId key
	 * 
	 * @param competitionRoundId
	 * @param holeNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<HoleScore> listHoleScoreByCompetitionRoundAndHole(
			String competitionRoundId, int holeNumber)
			throws PersistenceException;

	/**
	 * Using competitionId and roundNumber keys
	 * 
	 * @param golferId
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<HoleScore> listGolferHoleScoreByCompetitionRound(
			String golferId, String competitionId, String roundNumber)
			throws PersistenceException;

	/**
	 * Using competitionId and roundNumber keys
	 * 
	 * @param golferId
	 * @param competitionRoundId
	 * @return
	 * @throws PersistenceException
	 */
	public abstract List<HoleScore> listGolferHoleScoreByCompetitionRound(
			String golferId, String competitionRoundId)
			throws PersistenceException;

	/**
	 * Using competitionId and roundNumber keys
	 * 
	 * @param golferId
	 * @param competitionId
	 * @param roundNumber
	 * @param holeNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract HoleScore findGolferHoleScoreByCompetitionRound(
			String golferId, String competitionId, String roundNumber,
			int holeNumber) throws PersistenceException;

	/**
	 * Using competitionRoundId key
	 * 
	 * @param golferId
	 * @param competitionRoundId
	 * @param holeNumber
	 * @return
	 * @throws PersistenceException
	 */
	public abstract HoleScore findGolferHoleScoreByCompetitionRound(
			String golferId, String competitionRoundId, int holeNumber)
			throws PersistenceException;
}
