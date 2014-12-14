/**
 * 
 */
package com.gffny.ldrbrd.common.dao.nosql.mongo;

import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.nosql.HoleScore;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional
@Repository
public class HoleScoreMongoDaoImpl extends GenericNoSqlDaoMongoImpl<HoleScore>
		implements IHoleScoreNoSqlDao {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao#
	 *      listHoleScoreByCompetitionRound(java.lang.String, java.lang.String)
	 */
	@Override
	public List<HoleScore> listHoleScoreByCompetitionRound(
			String competitionId, String roundNumber)
			throws PersistenceException {
		return buildCompetitionRoundQuery(competitionId, roundNumber).asList();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao#
	 *      listHoleScoreByCompetitionRound(java.lang.String)
	 */
	@Override
	public List<HoleScore> listHoleScoreByCompetitionRound(
			String competitionRoundId) throws PersistenceException {
		return buildCompetitionRoundQuery(competitionRoundId).asList();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao#
	 *      listHoleScoreByCompetitionRoundAndHole(java.lang.String,
	 *      java.lang.String, int)
	 */
	@Override
	public List<HoleScore> listHoleScoreByCompetitionRoundAndHole(
			String competitionId, String roundNumber, int holeNumber)
			throws PersistenceException {
		return buildCompetitionRoundQuery(competitionId, roundNumber)
				.field(HoleScore.HOLE_NUMBER)
				.equal(Integer.valueOf(holeNumber)).asList();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao#
	 *      listHoleScoreByCompetitionRoundAndHole(java.lang.String, int)
	 */
	@Override
	public List<HoleScore> listHoleScoreByCompetitionRoundAndHole(
			String competitionRoundId, int holeNumber)
			throws PersistenceException {
		return buildCompetitionRoundQuery(competitionRoundId)
				.field(HoleScore.HOLE_NUMBER)
				.equal(Integer.valueOf(holeNumber)).asList();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao#
	 *      listGolferHoleScoreByCompetitionRound(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public List<HoleScore> listGolferHoleScoreByCompetitionRound(
			String golferId, String competitionId, String roundNumber)
			throws PersistenceException {
		return buildCompetitionRoundGolferQuery(golferId, competitionId,
				roundNumber).asList();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao#
	 *      listGolferHoleScoreByCompetitionRound(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public List<HoleScore> listGolferHoleScoreByCompetitionRound(
			String golferId, String competitionRoundId)
			throws PersistenceException {
		return buildCompetitionRoundGolferQuery(competitionRoundId, golferId)
				.asList();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao#
	 *      findGolferHoleScoreByCompetitionRound(java.lang.String,
	 *      java.lang.String, java.lang.String, int)
	 */
	@Override
	public HoleScore findGolferHoleScoreByCompetitionRound(String golferId,
			String competitionId, String roundNumber, int holeNumber)
			throws PersistenceException {
		return buildCompetitionRoundGolferQuery(golferId, competitionId,
				roundNumber).field(HoleScore.HOLE_NUMBER)
				.equal(Integer.valueOf(holeNumber)).get();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao#
	 *      findGolferHoleScoreByCompetitionRound(java.lang.String,
	 *      java.lang.String, int)
	 */
	@Override
	public HoleScore findGolferHoleScoreByCompetitionRound(String golferId,
			String competitionRoundId, int holeNumber)
			throws PersistenceException {
		return buildCompetitionRoundGolferQuery(golferId, competitionRoundId)
				.field(HoleScore.HOLE_NUMBER)
				.equal(Integer.valueOf(holeNumber)).get();
	}

	/**
	 * @param golferId
	 * @param competitionRoundId
	 * @return
	 */
	private Query<HoleScore> buildCompetitionRoundGolferQuery(String golferId,
			String competitionRoundId) {
		return buildCompetitionRoundQuery(competitionRoundId).field(
				HoleScore.GOLFER_ID).equal(golferId);
	}

	/**
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 */
	private Query<HoleScore> buildCompetitionRoundQuery(String competitionId,
			String roundNumber) {
		return this.datastore.createQuery(HoleScore.class)
				.field(HoleScore.COMPETITION_ID).equal(competitionId)
				.field(HoleScore.ROUND_NUMBER).equal(roundNumber);
	}

	/**
	 * @param competitionRoundId
	 * @return
	 */
	private Query<HoleScore> buildCompetitionRoundQuery(
			String competitionRoundId) {
		return this.datastore.createQuery(HoleScore.class)
				.field(HoleScore.COMPETITION_ROUND_ID)
				.equal(competitionRoundId);
	}

	/**
	 * @param golferId
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 */
	private Query<HoleScore> buildCompetitionRoundGolferQuery(String golferId,
			String competitionId, String roundNumber) {
		return buildCompetitionRoundQuery(competitionId, roundNumber).field(
				HoleScore.GOLFER_ID).equal(golferId);
	}

}
