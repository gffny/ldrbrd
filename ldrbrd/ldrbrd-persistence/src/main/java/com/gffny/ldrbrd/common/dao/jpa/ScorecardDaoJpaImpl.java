/**
 * 
 */
package com.gffny.ldrbrd.common.dao.jpa;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.dao.IScorecardDao;
import com.gffny.ldrbrd.common.exception.ActiveScorecardServiceException;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.utils.CollectionUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Repository
public class ScorecardDaoJpaImpl implements IScorecardDao {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(ScorecardDaoJpaImpl.class);

	/** The em. */
	@PersistenceContext(unitName = "ldrbrd_pu")
	private EntityManager em;

	/**
	 * (non-Javadoc)
	 * 
	 * @throws PersistenceException
	 * @see com.gffny.ldrbrd.common.dao.IScorecardDao#isScorecardActive(int)
	 */
	public boolean isScorecardActive(int scorecardId) throws PersistenceException {
		if (doesScorecardExist(scorecardId)) {
			BigInteger scorecardCount = (BigInteger) em.createNativeQuery(
					"select count(" + Constant.DB_ID_FIELD + ") from "
							+ Constant.DB_TABLE_SCORECARD + " where " + Constant.DB_ID_FIELD + "="
							+ scorecardId + " and " + Constant.DB_SCORECARD_ACTIVE_FIELD + " = "
							+ Constant.DB_SCORECARD_ACTIVE_VALUE).getSingleResult();
			// check if the scorecard count is equal to zero then it does not exists
			if (scorecardCount.intValue() == 0) {
				return false;
				// if the scorecard count is equal to one then it exists
			} else if (scorecardCount.intValue() == 1) {
				return true;
				// if the scorecard count is greater one then there is an issue
			}
			throw new PersistenceException("there should only be one scorecard in existence");
		}
		throw new PersistenceException("scorecard does not exist");
	}

	/**
	 * @param scorecardId
	 * @return
	 * @throws PersistenceException
	 */
	private boolean doesScorecardExist(int scorecardId) throws PersistenceException {
		// scorecardId should be greater than zero
		if (scorecardId > 0) {
			BigInteger scorecardCount = (BigInteger) em.createNativeQuery(
					"select count(" + Constant.DB_ID_FIELD + ") from "
							+ Constant.DB_TABLE_SCORECARD + " where " + Constant.DB_ID_FIELD + "="
							+ scorecardId).getSingleResult();
			if (scorecardCount != null && scorecardCount.intValue() == 0) {
				return false;
			} else if (scorecardCount != null && scorecardCount.intValue() == 1) {
				return true;
			}
			throw new PersistenceException("there should only be one scorecard for id "
					+ scorecardId);
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.IScorecardDao#scoreHole(int, int, int)
	 */
	public void scoreHole(int scorecardId, int holeNumber, int holeScore, String holeId)
			throws PersistenceException {
		BigInteger holeScoreExists = (BigInteger) em.createNativeQuery(
				"select count(scorecard_id) from " + Constant.DB_TABLE_HOLE_SCORE
						+ " where scorecard_id=" + scorecardId + " and hole_number=" + holeNumber)
				.getSingleResult();
		switch (holeScoreExists.intValue()) {
		case 0:
			// insert
			Query insert = em.createNativeQuery("insert into " + Constant.DB_TABLE_HOLE_SCORE
					+ " values (" + scorecardId + ", " + holeNumber + ", " + holeScore + ", "
					+ holeId + ")");
			int res = insert.executeUpdate();
			LOG.debug(
					"inserted the hole score for scorecard {}, hole {}, with score {} : result {}",
					scorecardId, holeNumber, holeScore, res);
			break;
		case 1:
			// update
			Query update = em.createNativeQuery("update " + Constant.DB_TABLE_HOLE_SCORE
					+ " set score=" + holeScore + " where scorecard_id=" + scorecardId
					+ " and hole_number=" + holeNumber);
			int updateResult = update.executeUpdate();
			LOG.debug(
					"updated the hole score for scorecard {}, hole {}, with score {} : result {}",
					scorecardId, holeNumber, holeScore, updateResult);
			break;
		default:
			// remove existing and score
			LOG.info(
					"removing scores for hole {} for scorecard id {} because there are more than one",
					holeNumber, scorecardId);
			Query removeOld = em.createNativeQuery("delete from " + Constant.DB_TABLE_SCORECARD
					+ " where " + Constant.DB_SCORECARD_SCORECARD_ID + " = " + scorecardId
					+ " and " + Constant.DB_SCORECARD_HOLE_NUMBER + " = " + holeNumber);
			removeOld.executeUpdate();
			scoreHole(scorecardId, holeNumber, holeScore, holeId);
			throw new PersistenceException(
					"Shouldn't have a situation where there is multiple hole scores for one scorecard");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.IScorecardDao#hasActiveScoreacard(int)
	 */
	public boolean hasActiveScoreacard(int golferId) {
		// check validity
		if (golferId > 0) {
			Map<String, String> queryParamMap = new HashMap<String, String>();
			queryParamMap.put("golferId", String.valueOf(golferId));

			// create the query
			@SuppressWarnings("unchecked")
			List<Scorecard> activeScorecardList = em
					.createNamedQuery(Scorecard.FIND_ACTIVE_SCORECARD)
					.setParameter("golferId", golferId).getResultList();

			if (CollectionUtils.isEmpty(activeScorecardList)) {
				return false;
			} else if (CollectionUtils.size(activeScorecardList) == 1) {
				return true;
			} else {
				LOG.error("user id {} has an invalid number of active scorecard: {}", golferId,
						activeScorecardList.size());
				throw new ActiveScorecardServiceException(
						"user has an invalid number of active scorecards",
						activeScorecardList.get(0));
			}
		}
		LOG.error("user id {} is invalid", golferId);
		throw new ServiceException("user id is invalid");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.dao.IScorecardDao#signScorecard(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean signScorecard(String scorecardId, String signature) throws PersistenceException {
		if (scorecardId != null && signature != null) {
			em.createNativeQuery(
					"UPDATE " + Constant.DB_TABLE_SCORECARD + " SET "
							+ Constant.DB_SCORECARD_ACTIVE_FIELD + " = "
							+ Constant.DB_SCORECARD_INACTIVE_VALUE + ", "
							+ Constant.DB_SCORECARD_SIGNATURE_FIELD + " = \'" + signature
							+ "\' WHERE " + Constant.DB_ID_FIELD + " = " + scorecardId)
					.executeUpdate();
			return true;
		}
		return false;
	}
}
