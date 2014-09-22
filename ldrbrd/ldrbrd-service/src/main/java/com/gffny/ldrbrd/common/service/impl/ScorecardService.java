/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.dao.GenericNoSqlDao;
import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.model.impl.mongo.AnalysisScorecard;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;
import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.service.IUserProfileService;
import com.gffny.ldrbrd.common.utils.AnalysisUtils;
import com.gffny.ldrbrd.common.utils.DebugUtils;
import com.gffny.ldrbrd.common.utils.StringUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
@Service
public class ScorecardService extends AbstractService implements IScorecardService {

	/**	*/
	private static final int NEED_TO_BE_PUSHED_INTO_PROFILE = 5;

	/** */
	private static final int EXISTING_GOLFER_HANDICAP = -1;

	/** The Constant log. */
	static final Logger LOG = LoggerFactory.getLogger(ScorecardService.class);

	/** */
	@Autowired
	private ICourseClubService courseClubService;

	/** */
	@Autowired
	private IUserProfileService profileService;

	/** */
	@Autowired
	private GenericDao<Scorecard> scorecardDao;

	/** */
	@Autowired
	private GenericNoSqlDao<AnalysisScorecard> analysisScorecardMongoDaoImpl;

	/** The em. */
	@PersistenceContext(unitName = "ldrbrd_pu")
	private EntityManager em;

	/**
	 * (non-Javadoc)
	 * 
	 * @throws AuthorisationException
	 * @throws ServiceException
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard(int,
	 *      java.lang.String)
	 */
	@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED)
	public Scorecard startGeneralScorecard(String courseId) throws AuthorisationException,
			ServiceException {
		// check param
		if (StringUtils.isNotEmpty(courseId)) {
			try {
				DebugUtils.transactionRequired("ScorecardService.startGeneralScorecard");

				// get the course
				Course courseToPlay = courseClubService.courseById(courseId);
				if (courseToPlay != null) {
					// get the logged in golfer for whom to create the scorecard
					if (authorisationService.getLoggedInUser() instanceof Golfer) {
						Golfer golfer = (Golfer) authorisationService.getLoggedInUser();
						// persist the new scorecard
						Scorecard result = Scorecard.createNewScorecard(golfer, courseToPlay,
								golfer.getHandicap().intValue());
						this.em.persist(result);
						return result;
						// return scorecardDao.persist(Scorecard.createNewScorecard(golfer,
						// courseToPlay, golfer.getHandicap().intValue()));
					} else {
						Golfer golfer = profileService.getGolferByHandle(authorisationService
								.getLoggedInUser().getProfileHandle());
						// persist the new scorecard
						return scorecardDao.persist(Scorecard.createNewScorecard(golfer,
								courseToPlay, golfer.getHandicap().intValue()));
					}
				} else {
					LOG.error("no course returned for id {}", courseId);
				}
			} catch (PersistenceException | AuthorisationException excp) {
				LOG.error(excp.getMessage());
				throw new ServiceException(excp);
			}
		}
		throw new ServiceException("course id is null");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, java.util.HashMap, java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId,
			Map<String, String> hashMap, List<CommonIDEntity> clubList) {

		// return other method
		return startGeneralScorecard(golferId, golferId, courseId, hashMap, clubList);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, int, java.util.HashMap, java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId, int handicap,
			Map<String, String> hashMap, List<CommonIDEntity> clubList) {

		// return other method
		return startGeneralScorecard(golferId, golferId, courseId, handicap, hashMap, clubList);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, java.lang.String, java.util.HashMap,
	 *      java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId, String scoreKeeperId, String courseId,
			Map<String, String> hashMap, List<CommonIDEntity> clubList) {

		// TODO Maybe throw a business exception
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, java.lang.String, int, java.util.HashMap,
	 *      java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId, String scoreKeeperId, String courseId,
			int handicap, Map<String, String> hashMap, List<CommonIDEntity> clubList) {
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startCompetitionScorecard
	 *      (java.lang.String, java.lang.String, java.lang.String, int, java.util.LinkedList)
	 */
	public Scorecard startCompetitionScorecard(String golferId, String scoreKeeperId,
			String competitionId, int roundNumber, List<CommonIDEntity> clubList)
			throws ServiceException {

		// if there is no handicap passed, use the golfer's existing handicap
		return startCompetitionScorecard(golferId, scoreKeeperId, competitionId, roundNumber,
				clubList, EXISTING_GOLFER_HANDICAP);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.impl.IScorecardService#
	 *      startCompetitionScorecard(java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.Integer, java.util.LinkedList)
	 */
	public Scorecard startCompetitionScorecard(String golferId, String scoreKeeperId,
			String competitionId, int roundNumber, List<CommonIDEntity> clubList,
			int competitionHandicap) throws ServiceException {
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreHoleScoreMap(java .lang.String,
	 *      java.util.Map)
	 */
	public void scoreHoleScoreMap(int scorecardId, Map<Integer, Integer> holeScoreMap)
			throws ServiceException {

		// check is list is valid
		if (holeScoreMap != null) {
			// get the key set for the hole score map
			Set<Integer> holeScoreKeySet = holeScoreMap.keySet();
			// traverse hole map to score holes
			for (Integer holeScoreKey : holeScoreKeySet) {
				// score hole
				scoreHole(scorecardId, holeScoreKey.intValue(), holeScoreMap.get(holeScoreKey)
						.intValue());
			}
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreHole(com.gffny
	 *      .ldrbrd.common.model.impl.HoleScore)
	 */
	public void scoreHole(int scorecardId, int holeNumber, int holeScore) throws ServiceException {
		// check hole validity
		if (holeNumber > 0 && holeScore > 0) {
			EntityManager em = scorecardDao.getEntityManager();
			BigInteger holeScoreExists = (BigInteger) em.createNativeQuery(
					"select count(scorecard_id) from " + Constant.DB_TABLE_HOLE_SCORE
							+ " where scorecard_id=" + scorecardId + " and hole_number="
							+ holeNumber).getSingleResult();
			switch (holeScoreExists.intValue()) {
			case 0:
				// insert
				Query insert = em.createNativeQuery("insert into " + Constant.DB_TABLE_HOLE_SCORE
						+ " values (" + scorecardId + ", " + holeNumber + ", " + holeScore + ")");
				int res = insert.executeUpdate();
				LOG.debug(
						"inserted the hole score for scorecard {}, hole {}, with score {} : result {}",
						scorecardId, holeNumber, holeScore, res);
			case 1:
				// update
				Query update = em.createNativeQuery("update " + Constant.DB_TABLE_HOLE_SCORE
						+ " set score=" + holeScore + " where scorecard_id=" + scorecardId
						+ " and hole_number=" + holeNumber);
				int updateResult = update.executeUpdate();
				LOG.debug(
						"updated the hole score for scorecard {}, hole {}, with score {} : result {}",
						scorecardId, holeNumber, holeScore, updateResult);
			default:
				// error!
				// TODO delete all entries and create a new entry?
			}
		} else {
			LOG.error("holeNumber or holeScore is incorrect");
			throw new ServiceException("invalid parameters");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#checkScorecardScoreKeeper
	 *      (java.lang.String, java.lang.String)
	 */
	public boolean checkScorecardScoreKeeper(String scorecardId, String scoreKeeperId) {

		// check the parameters
		if (scorecardId != null && scoreKeeperId != null) {

		} else {
			LOG.error("method parameter(s) not valid");
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#submitScorecard(java .lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Transactional
	public void submitScorecard(String scorecardId, String scoreKeeperId, String competitionId) {

		// check the parameters
		if (scorecardId != null && scoreKeeperId != null && competitionId != null) {

		} else {
			LOG.error("method parameter(s) not valid");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws ServiceException
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#signScorecard(java. lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public void signScorecard(String scorecardId, String scoreKeeperId, String competitionId)
			throws ServiceException {

		// check the parameters
		if (scorecardId != null && scoreKeeperId != null && competitionId != null) {
			try {
				// create an analysis scorecard and persist it
				AnalysisScorecard analysisScorecard = AnalysisUtils.analyseScorecard(scorecardDao
						.findById(Scorecard.class, scorecardId));
				analysisScorecardMongoDaoImpl.persist(analysisScorecard);
			} catch (PersistenceException pex) {
				LOG.error("error retrieving scorecard for id {}", scorecardId);
				throw new ServiceException(pex);
			}
		} else {
			LOG.error("method parameter(s) not valid");
		}
	}

	/**
	 * @param scorecard
	 * @return
	 */
	public final static String encodeScorecard(Scorecard scorecard) {
		if (scorecard != null) {

		}
		LOG.error("scorecard parameter is null");
		// don't return null as there may be operations applied to the return
		// value
		return new String("");
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#getLastXScorecards( java.lang.String,
	 * int)
	 */
	public List<Scorecard> getLastXScorecards(String golferId, int xScorecards)
			throws ServiceException {
		if (!StringUtils.isEmpty(golferId) && xScorecards > 0) {

			return new ArrayList<Scorecard>();
		} else {
			LOG.error("");
			throw new ServiceException("");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#getLastXScorecards( java.lang.String)
	 */
	public List<Scorecard> getLastXScorecards(String golferId) throws ServiceException {
		// TODO add the "last x" value to the profile
		return getLastXScorecards(golferId, NEED_TO_BE_PUSHED_INTO_PROFILE);
	}
}
