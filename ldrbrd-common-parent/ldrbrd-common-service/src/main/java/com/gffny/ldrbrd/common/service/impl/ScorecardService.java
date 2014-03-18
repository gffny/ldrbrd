/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.CourseHole;
import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.impl.HoleScore;
import com.gffny.ldrbrd.common.model.impl.HoleShot;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.utils.StringUtils;

/**
 * @author jdgaffney
 * 
 */
@Service
public class ScorecardService extends AbstractService implements
		IScorecardService {

	private static final int NEED_TO_BE_PUSHED_INTO_PROFILE = 5;

	private static final int EXISTING_GOLFER_HANDICAP = -1;

	/** The Constant log. */
	static final Logger LOG = LoggerFactory.getLogger(ScorecardService.class);

	/**
	 * 
	 */
	@Autowired
	private GenericDao<Scorecard> scorecardDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<GolferProfile> golferDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<Competition> competitionDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<CompetitionRound> competitionRoundDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<Course> courseDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<CourseHole> courseHoleDao;

	/**
	 * 
	 */
	@Autowired
	private GenericDao<HoleScore> holeScoreDao;

	/**
	 * 
	 */
	@Autowired
	private ICompetitionService competitionService;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, java.util.HashMap,
	 *      java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId,
			HashMap<String, String> hashMap, LinkedList<GolfClub> clubList) {

		// return other method
		return startGeneralScorecard(golferId, golferId, courseId, hashMap,
				clubList);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, int, java.util.HashMap,
	 *      java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId,
			int handicap, HashMap<String, String> hashMap,
			LinkedList<GolfClub> clubList) {

		// return other method
		return startGeneralScorecard(golferId, golferId, courseId, handicap,
				hashMap, clubList);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, java.lang.String,
	 *      java.util.HashMap, java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId,
			String scoreKeeperId, String courseId,
			HashMap<String, String> hashMap, LinkedList<GolfClub> clubList) {
		try {
			GolferProfile golfer = golferDao.findById(GolferProfile.class,
					golferId);
			GolferProfile scoreKeeper = golferDao.findById(GolferProfile.class,
					scoreKeeperId);
			Course course = courseDao.findById(Course.class, courseId);
			if (golfer != null && scoreKeeper != null && course != null) {
				return scorecardDao.persist(Scorecard.createNewScorecard(
						golfer, scoreKeeper, course, golfer.getHandicap()));
			} else {
				LOG.error("golfer is null: " + golferId);
			}
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}

		// TODO Maybe throw a business exception
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, java.lang.String, int,
	 *      java.util.HashMap, java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId,
			String scoreKeeperId, String courseId, int handicap,
			HashMap<String, String> hashMap, LinkedList<GolfClub> clubList) {
		try {
			GolferProfile golfer = golferDao.findById(GolferProfile.class,
					golferId);
			GolferProfile scoreKeeper = golferDao.findById(GolferProfile.class,
					scoreKeeperId);
			Course course = courseDao.findById(Course.class, courseId);
			if (scoreKeeper != null && golfer != null && course != null) {
				return scorecardDao.persist(Scorecard.createNewScorecard(
						golfer, scoreKeeper, course, handicap));
			} else {
				LOG.error("golfer is null: " + golferId);
			}
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startCompetitionScorecard
	 *      (java.lang.String, java.lang.String, java.lang.String, int,
	 *      java.util.LinkedList)
	 */
	public Scorecard startCompetitionScorecard(String golferId,
			String scoreKeeperId, String competitionId, int roundNumber,
			LinkedList<GolfClub> clubList) throws ServiceException {

		// if there is no handicap passed, use the golfer's existing handicap
		return startCompetitionScorecard(golferId, scoreKeeperId,
				competitionId, roundNumber, clubList, EXISTING_GOLFER_HANDICAP);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.impl.IScorecardService#
	 *      startCompetitionScorecard(java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.util.LinkedList)
	 */
	public Scorecard startCompetitionScorecard(String golferId,
			String scoreKeeperId, String competitionId, int roundNumber,
			LinkedList<GolfClub> clubList, int competitionHandicap)
			throws ServiceException {
		Scorecard result = null;
		try {
			// create parameter map
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("competitionId", competitionId);
			params.put("roundNumber", Integer.valueOf(roundNumber));
			// get the competition round (so that you can get it's id!

			// TODO use the super.namedQueryResultOrNullMethod
			List<CompetitionRound> competitionRoundList = competitionRoundDao
					.findByNamedQuery(
							CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR,
							params);
			if (null != competitionRoundList && 0 < competitionRoundList.size()
					&& null != competitionRoundList.get(0)) {
				// check if scorecard exists for combination of {golfer,
				// competitionRound} via scorecard dao
				LOG.debug("competition round id: "
						+ competitionRoundList.get(0).getId());
				params = new HashMap<String, Object>();
				params.put("competitionRoundId", competitionRoundList.get(0)
						.getId());
				params.put("golferId", golferId);
				// query scorecard table

				// TODO use the super.namedQueryResultOrNullMethod
				List<Scorecard> scorecardList = scorecardDao
						.findByNamedQuery(
								Scorecard.FIND_SCORECARD_BY_COMPETITION_ROUND_AND_GOLFER,
								params);
				if (null != scorecardList && scorecardList.size() > 0
						&& null != scorecardList.get(0)) {
					// scorecard for the competition round / golfer combination
					// already exists
					result = scorecardList.get(0);
					LOG.debug("scorecard exists for competition round ("
							+ result.getCompetitionRound().getId()
							+ ") and golfer (" + result.getGolfer().getId()
							+ ")");
					return result;
				} else {
					// no scorecard exists
					GolferProfile golfer = golferDao.findById(
							GolferProfile.class, golferId);
					GolferProfile scoreKeeper = golferDao.findById(
							GolferProfile.class, scoreKeeperId);
					// CompetitionRound competitionRound = competitionService
					// .getCompetitionRound(competitionId, roundNumber);
					CompetitionRound competitionRound = competitionRoundList
							.get(0);
					if (scoreKeeper != null && golfer != null
							&& competitionRound != null) {
						LOG.debug("creating new scorecard for competition round ("
								+ competitionRound.getId()
								+ ") and golfer ("
								+ golfer.getId() + ")");
						if (competitionHandicap < 0) {
							result = scorecardDao.persist(Scorecard
									.createNewCompetitionScorecard(golfer,
											scoreKeeper, competitionRound,
											golfer.getHandicap()));
						} else {
							result = scorecardDao.persist(Scorecard
									.createNewCompetitionScorecard(golfer,
											scoreKeeper, competitionRound,
											competitionHandicap));
						}
						return result;
					} else {
						LOG.error("one of the following is null: compeition round, score keeper golfer profile, or golfer profile");
						throw new ServiceException(
								"one of the following is null: compeition round, score keeper golfer profile, or golfer profile");
					}
				}
			} else {
				// there is something wrong if there is no competition round
				// created before a scorecard TODO decide if a round should be
				// generated dynamically
				LOG.error("no competition round exists for competition id: "
						+ competitionId + " and round number: " + roundNumber);
				throw new ServiceException(
						"no competition round exists for competition id: "
								+ competitionId + " and round number: "
								+ roundNumber);
			}
		} catch (DataAccessException daEx) {
			LOG.error("{} error with competition scorecard", daEx.getClass()
					.getName());
			throw new ServiceException(daEx);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreHoleScoreMap(java
	 *      .lang.String, java.util.Map)
	 */
	public void scoreHoleScoreMap(String scorecardId,
			Map<Integer, Integer> holeScoreMap) throws ServiceException {

		// check is list is valid
		if (holeScoreMap != null) {
			// get the key set for the hole score map
			Set<Integer> holeScoreKeySet = holeScoreMap.keySet();
			// traverse hole map to score holes
			for (Integer holeScoreKey : holeScoreKeySet) {
				// score hole
				scoreHole(scorecardId, holeScoreKey,
						holeScoreMap.get(holeScoreKey));
			}
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreShotMap(java.lang
	 *      .String, java.util.Map)
	 */
	public void scoreShotMap(String scorecardId,
			Map<Integer, HoleShot> holeShotMap) {

		// check the map is valid
		if (holeShotMap != null) {
			Set<Integer> keySet = holeShotMap.keySet();
			for (Integer holeKey : keySet) {
				scoreHoleShot(scorecardId, holeKey, holeShotMap.get(holeKey));
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreHole(com.gffny
	 *      .ldrbrd.common.model.impl.HoleScore)
	 */
	public void scoreHole(String scorecardId, Integer holeNumber,
			Integer holeScore) throws ServiceException {
		// check hole validity
		if (scorecardId != null && holeNumber != null && holeScore != null) {
			try {
				// check if there is an existing hole
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("scorecardId", scorecardId);
				params.put("holeNumber", holeNumber);
				// get the hole record (if it exists)
				HoleScore existingHoleScore = namedQuerySingleResultOrNull(
						holeScoreDao,
						HoleScore.FIND_BY_SCORECARD_ID_AND_HOLE_NUMBER, params);
				// if the hole exists, update the hole score and persist it
				if (existingHoleScore != null) {
					// is so, update the score
					existingHoleScore.setHoleScore(holeScore.intValue());
					LOG.debug("scoring hole " + holeNumber + " for scorecard "
							+ scorecardId + " with score " + holeScore);
					holeScoreDao.merge(existingHoleScore);

					// TODO potentially score this to a redis server for the
					// "live scoreboard"
				} else {
					// if not, create and persist the object
					if (holeScore.intValue() > 0) {
						Scorecard scorecard = scorecardDao.findById(
								Scorecard.class, scorecardId);
						Map<String, Object> courseHoleParams = new HashMap<String, Object>();
						courseHoleParams.put("courseId", scorecard.getCourse()
								.getId());
						courseHoleParams.put("holeNumber", holeNumber);
						CourseHole courseHole = namedQuerySingleResultOrNull(
								courseHoleDao,
								CourseHole.FIND_BY_COURSE_ID_AND_HOLE_NUMBER,
								courseHoleParams);
						LOG.debug(
								"scoring hole {} for scorecard {} with score {}",
								holeNumber, scorecardId, holeScore);
						holeScoreDao.persist(HoleScore.createInstance(
								scorecard, courseHole, holeScore));

						// TODO potentially score this to a redis server for the
						// "live scoreboard"
					} else {
						LOG.error("a score below 1 is attempted to be stored");
					}
				}
			} catch (DataAccessException daEx) {
				LOG.error("{} error with competition scorecard", daEx
						.getClass().getName());
				throw new ServiceException(daEx);
			} catch (ServiceException srvEx) {
				LOG.error("{} error with competition scorecard", srvEx
						.getClass().getName());
				throw srvEx;
			}
		} else {
			LOG.error("one of scorecardId, holeNumber, or holeScore is null");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreHoleShot(java.
	 *      lang.String, java.lang.Integer,
	 *      com.gffny.ldrbrd.common.model.impl.HoleShot)
	 */
	public void scoreHoleShot(String scorecardId, Integer holeNumber,
			HoleShot shot) {

		// TODO potentially score this to a redis server for the "shot analysis"

		// check the parameter validity
		if (scorecardId != null && holeNumber != null && shot != null) {
			LOG.debug(shot.toString());
		} else {
			LOG.error("method parameter(s) not valid");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#checkScorecardScoreKeeper
	 *      (java.lang.String, java.lang.String)
	 */
	public boolean checkScorecardScoreKeeper(String scorecardId,
			String scoreKeeperId) {

		// check the parameters
		if (scorecardId != null && scoreKeeperId != null) {
			try {
				Scorecard scorecard = scorecardDao.findById(Scorecard.class,
						scorecardId);
				if (scorecard != null) {
					LOG.debug("submitting scorecard: " + scorecardId);
					return StringUtils.isEquivalentCaseInsensitive(scorecard
							.getScoringGolfer().getId(), scoreKeeperId);
				} else {
					LOG.error("scorecard is null for id: " + scorecardId);
				}
			} catch (DataAccessException daEx) {
				LOG.error(daEx.getMessage());
				return false;
			}
		} else {
			LOG.error("method parameter(s) not valid");
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#submitScorecard(java
	 *      .lang.String, java.lang.String, java.lang.String)
	 */
	public void submitScorecard(String scorecardId, String scoreKeeperId,
			String competitionId) {

		// check the parameters
		if (scorecardId != null && scoreKeeperId != null
				&& competitionId != null) {
			try {
				Scorecard scorecard = scorecardDao.findById(Scorecard.class,
						scorecardId);
				if (scorecard != null) {
					scorecard.submitScorecard(ScorecardService
							.encodeScorecard(scorecard));
					LOG.debug("submitting scorecard: " + scorecardId);
					scorecardDao.merge(scorecard);
				} else {
					LOG.error("scorecard is null for id: " + scorecardId);
				}
			} catch (DataAccessException daEx) {
				LOG.error(daEx.getMessage());
			}
		} else {
			LOG.error("method parameter(s) not valid");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#signScorecard(java.
	 *      lang.String, java.lang.String, java.lang.String)
	 */
	public void signScorecard(String scorecardId, String scoreKeeperId,
			String competitionId) {

		// check the parameters
		if (scorecardId != null && scoreKeeperId != null
				&& competitionId != null) {
			try {
				Scorecard scorecard = scorecardDao.findById(Scorecard.class,
						scorecardId);
				if (scorecard != null) {
					scorecard.signScorecard(ScorecardService
							.encodeScorecard(scorecard));
					LOG.debug("signing scorecard: " + scorecardId);
					scorecardDao.merge(scorecard);
				} else {
					LOG.error("scorecard is null for id: " + scorecardId);
				}
			} catch (DataAccessException daEx) {
				LOG.error(daEx.getMessage());
			}
		} else {
			LOG.error("method parameter(s) not valid");
		}
	}

	/**
	 * 
	 * @param scorecard
	 * @return
	 */
	public final static String encodeScorecard(Scorecard scorecard) {
		if (scorecard != null) {
			LOG.debug("encoding scorecard with signature: "
					+ scorecard.encodingSignature());
			return DigestUtils.md5Hex(scorecard.encodingSignature());
		}
		LOG.error("scorecard parameter is null");
		// don't return null as there may be operations applied to the return
		// value
		return new String("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IScorecardService#getLastXScorecards(
	 * java.lang.String, int)
	 */
	public List<Scorecard> getLastXScorecards(String golferId, int xScorecards)
			throws ServiceException {
		if (!StringUtils.isEmpty(golferId) && xScorecards > 0) {
			try {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("golferId", golferId);
				return scorecardDao.findByNamedQuery(
						Scorecard.FIND_SCORECARDS_BY_GOLFER_ID_AND_QUANTITY,
						paramMap, xScorecards);
			} catch (DataAccessException daEx) {
				LOG.error(daEx.getMessage());
				throw new ServiceException(daEx);
			}
		} else {
			LOG.error("");
			throw new ServiceException("");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IScorecardService#getLastXScorecards(
	 * java.lang.String)
	 */
	public List<Scorecard> getLastXScorecards(String golferId)
			throws ServiceException {
		// TODO add the "last x" value to the profile
		return getLastXScorecards(golferId, NEED_TO_BE_PUSHED_INTO_PROFILE);
	}
}
