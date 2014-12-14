/**
 * 
 */
package com.gffny.ldrbrd.common.dao.nosql.mongo;

import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.nosql.HoleScore;
import com.gffny.ldrbrd.common.model.nosql.RoundScore;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional
@Repository
public class RoundScoreMongoDaoImpl extends
		GenericNoSqlDaoMongoImpl<RoundScore> implements IRoundScoreNoSqlDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao#
	 * listRoundScoreByCompetition(java.lang.String)
	 */
	@Override
	public List<RoundScore> listRoundScoreByCompetition(String competitionId)
			throws PersistenceException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao#
	 * listGolferRoundScoreByCompetition(java.lang.String, java.lang.String)
	 */
	@Override
	public List<RoundScore> listGolferRoundScoreByCompetition(String golferId,
			String competitionId) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao#
	 * listRoundScoreByCompetitionRound(java.lang.String, java.lang.String)
	 */
	@Override
	public List<RoundScore> listRoundScoreByCompetitionRound(
			String competitionId, String roundNumber)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao#
	 * listRoundScoreByCompetitionRound(java.lang.String)
	 */
	@Override
	public List<RoundScore> listRoundScoreByCompetitionRound(
			String competitionRoundId) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao#
	 * findGolferRoundScoreByCompetitionRound(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public RoundScore findGolferRoundScoreByCompetitionRound(String golferId,
			String competitionId, String roundNumber)
			throws PersistenceException {
		return buildCompetitionRoundQuery(competitionId, roundNumber)
				.field(RoundScore.GOLFER_ID).equal(golferId).get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao#
	 * findGolferRoundScoreByCompetitionRound(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public RoundScore findGolferRoundScoreByCompetitionRound(String golferId,
			String competitionRoundId) throws PersistenceException {
		return buildCompetitionRoundQuery(competitionRoundId)
				.field(RoundScore.GOLFER_ID).equal(golferId).get();
	}

	/**
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 */
	private Query<RoundScore> buildCompetitionRoundQuery(String competitionId,
			String roundNumber) {
		return this.datastore.createQuery(RoundScore.class)
				.field(HoleScore.COMPETITION_ID).equal(competitionId)
				.field(HoleScore.ROUND_NUMBER).equal(roundNumber);
	}

	/**
	 * @param competitionRoundId
	 * @return
	 */
	private Query<RoundScore> buildCompetitionRoundQuery(
			String competitionRoundId) {
		return this.datastore.createQuery(RoundScore.class)
				.field(HoleScore.COMPETITION_ROUND_ID)
				.equal(competitionRoundId);
	}

}
