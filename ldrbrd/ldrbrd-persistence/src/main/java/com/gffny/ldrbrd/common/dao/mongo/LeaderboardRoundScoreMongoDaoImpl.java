/**
 * 
 */
package com.gffny.ldrbrd.common.dao.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.dao.ILeaderboardRoundNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.mongo.HoleScore;
import com.gffny.ldrbrd.common.model.impl.mongo.RoundScore;
import com.gffny.ldrbrd.common.model.impl.mongo.PlayerLeaderboardRound;
import com.gffny.ldrbrd.common.utils.StringUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
// @Transactional
@Repository
public class LeaderboardRoundScoreMongoDaoImpl extends
		GenericNoSqlDaoMongoImpl<RoundScore> implements ILeaderboardRoundNoSqlDao {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.ILeaderboardRoundNoSqlDao#listLeaderboardByCompetition(java.lang.String)
	 */
	public List<RoundScore> listLeaderboardByCompetition(String competitionId)
			throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(competitionId)) {
			return this.datastore.createQuery(RoundScore.class).field("competitionId")
					.equal(competitionId).asList();
		}
		throw new PersistenceException("competitionId cannot be null or empty");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.ILeaderboardRoundNoSqlDao#leaderboardByCompetitionAndRound(java.lang.String,
	 *      java.lang.String)
	 */
	public RoundScore findLeaderboardByCompetitionAndRoundEile(String competitionId,
			String roundNumber) throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(competitionId) && StringUtils.isNotEmpty(roundNumber)) {
			return this.datastore.createQuery(RoundScore.class).field("competitionId")
					.equal(competitionId).field("roundNumber").equal(roundNumber).get();
		}
		throw new PersistenceException("competitionId cannot be null or empty");
	}

	public RoundScore findLeaderboardByCompetitionAndRound(String competitionId,
			String roundNumber) throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(competitionId) && StringUtils.isNotEmpty(roundNumber)) {
			return this.datastore.createQuery(RoundScore.class).field("competitionId")
					.equal(competitionId).field("roundNumber").equal(roundNumber).get();
		}
		throw new PersistenceException("competitionId cannot be null or empty");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws PersistenceException
	 * @see com.gffny.ldrbrd.common.dao.ILeaderboardRoundNoSqlDao#scoreHole(java.lang.String,
	 *      java.lang.String, java.lang.String, int, int, int, int, int)
	 */
	public boolean scoreHoleEile(String competitionId, String roundNumber, String playerId,
			int holeNumber, int holeScore, int toPar, int toHandicapPar, int competitionScore)
			throws PersistenceException {
		// check params
		if (StringUtils.isNotEmpty(competitionId) && StringUtils.isNotEmpty(roundNumber)
				&& StringUtils.isNotBlank(playerId) && holeNumber > 0 && holeScore > 0) {
			// get the leaderboard round score for the competition and round number
			RoundScore lrs = this.findLeaderboardByCompetitionAndRound(competitionId,
					roundNumber);
			if (lrs != null) {
				// get the player leaderboard round for the player id
				PlayerLeaderboardRound plr = lrs.getPlayerLeaderboardRoundMap().get(playerId);
				if (plr != null) {
					// set the values for the hole
					plr.setHoleScoreDetail(holeNumber, holeScore, toPar, toHandicapPar,
							competitionScore);
					// update the totals
					plr = updateLeaderboarRoundTotals(plr);
					// set the player leaderboard round
					lrs.setPlayerLeaderboardRound(playerId, plr);
					// merge updates
					this.merge(lrs);
					return true;
				}
			}
		}
		LOG.error(
				"invalid params; competitionId {}, roundNumber {}, and playerId {} cannot be null and holeScore {}, and holeNumber {} must be greater than 0",
				competitionId, roundNumber, playerId, holeNumber, holeScore);
		throw new PersistenceException(
				"invalid params; competitionId, roundNumber, and playerId cannot be null and holeScore, and holeNumber must be greater than 0");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.ILeaderboardRoundNoSqlDao#scoreHole(java.lang.String,
	 *      java.lang.String, java.lang.String, int, int, int, int, int)
	 */
	public boolean scoreHole(String competitionId, String roundNumber, String playerId,
			int holeNumber, int holeScore, int toPar, int toHandicapPar, int competitionScore)
			throws PersistenceException {
		this.datastore.save(HoleScore.instance(competitionId, roundNumber, playerId,
				holeNumber, holeScore, toPar, toHandicapPar, competitionScore));
		return true;
	}

	// TODO move these methods to a Utils class
	/**
	 * @param plr
	 * @return
	 */
	private PlayerLeaderboardRound updateLeaderboarRoundTotals(PlayerLeaderboardRound plr) {
		// check params
		if (plr != null) {
			plr.setOverviewScore(sum(plr.getHoleScoreArray()));
			plr.setOverviewToPar(sum(plr.getToParArray()));
			plr.setOverviewToHandicapPar(sum(plr.getToHandicapParArray()));
			plr.setOverviewCompetitionScore(sum(plr.getCompetitionScoreArray()));
		}
		return plr;
	}

	/**
	 * @param scoreArray
	 * @return
	 */
	private int sum(int[] scoreArray) {
		// check params
		if (scoreArray != null && scoreArray.length > 0) {
			int total = 0;
			for (int score : scoreArray) {
				total += score;
			}
			return total;
		}
		return 0;
	}
}
