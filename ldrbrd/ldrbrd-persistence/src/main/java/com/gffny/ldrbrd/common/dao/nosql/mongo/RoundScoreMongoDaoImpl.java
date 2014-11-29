/**
 * 
 */
package com.gffny.ldrbrd.common.dao.nosql.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
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
		// TODO Auto-generated method stub
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
	public RoundScore findGolferRoundScoreByCompetitionRound(String golerId,
			String competitionId, String roundNumber)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao#
	 * findGolferRoundScoreByCompetitionRound(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public RoundScore findGolferRoundScoreByCompetitionRound(String golerId,
			String competitionRoundId) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao#scoreHole(java.
	 * lang.String, java.lang.String, java.lang.String, int, int, int, int, int)
	 */
	@Override
	public RoundScore scoreHole(String competitionId, String roundNumber,
			String playerId, int holeNumber, int holeScore, int toPar,
			int toHandicapPar, int competitionScore)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
