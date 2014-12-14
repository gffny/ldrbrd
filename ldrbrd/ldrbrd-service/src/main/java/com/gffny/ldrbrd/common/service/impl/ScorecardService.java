/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.dao.IScorecardDao;
import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.exception.ValidationException;
import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.impl.CompetitionEntry;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.model.impl.UserProfile;
import com.gffny.ldrbrd.common.model.nosql.Course;
import com.gffny.ldrbrd.common.scoring.impl.Stableford;
import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.common.service.ILeaderboardService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.service.IUserProfileService;
import com.gffny.ldrbrd.common.utils.CollectionUtils;
import com.gffny.ldrbrd.common.utils.DateUtils;
import com.gffny.ldrbrd.common.utils.MapUtils;
import com.gffny.ldrbrd.common.utils.ScoringUtils;
import com.gffny.ldrbrd.common.utils.Security;
import com.gffny.ldrbrd.common.utils.StringUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
@Service
public class ScorecardService extends AbstractService implements
		IScorecardService {

	/**	*/
	private static final int NEED_TO_BE_PUSHED_INTO_PROFILE = 5;

	/** */
	private static final int EXISTING_GOLFER_HANDICAP = -1;

	/** The Constant log. */
	static final Logger LOG = LoggerFactory.getLogger(ScorecardService.class);

	/** */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<Scorecard> scorecardDao;

	/** */
	@Autowired
	private IScorecardDao scorecardDaoJpaImpl;

	/** */
	@Autowired
	private ICompetitionService competitionService;

	/** */
	@Autowired
	private ICourseClubService courseClubService;

	/** */
	@Autowired
	private IUserProfileService profileService;

	/** */
	@Autowired
	private ICourseClubService clubService;

	/** */
	@Autowired
	private ILeaderboardService leaderboardService;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#hasActiveScorecard(java.lang.String)
	 */
	@Override
	public boolean hasActiveScorecard(String golferId) {
		try {
			// if the golferId is null, then return the logged in users
			// scorecard active status
			if (golferId == null) {
				UserProfile loggedInUser = authorisationService
						.getLoggedInUser();
				return scorecardDaoJpaImpl.hasActiveScoreacard(loggedInUser
						.getId());
			} else {
				return scorecardDaoJpaImpl.hasActiveScoreacard(Integer
						.parseInt(golferId));
			}
		} catch (ServiceException | NumberFormatException e) {
			LOG.error(e.getMessage());
			// if error return true
			return true;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws ServiceException
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#getActiveScorecard(java.lang.String)
	 */
	@Override
	public Scorecard getActiveScorecard(String golferId)
			throws ServiceException {

		// if the there is no golferId passed, use the loggedIn Golfer's id
		if (golferId == null) {
			golferId = String.valueOf(authorisationService.getLoggedInUser()
					.getId());
			LOG.debug(
					"golfer id was null, setting it to the logged in user's id {}",
					golferId);
		}
		try {
			// get the list of active scorecards
			List<Scorecard> activeScorecardList = scorecardDao
					.findByNamedQuery(
							Scorecard.FIND_ACTIVE_SCORECARD,
							populateParamMap(Constant.QUERY_PARAM_GOLFER_ID,
									Integer.valueOf(golferId)));
			if (CollectionUtils.safeSize(activeScorecardList) == 1) {
				// get the course of the scorecard
				Scorecard scorecard = activeScorecardList.get(0);
				if (scorecard != null) {
					scorecard.setCourse(clubService.courseById(scorecard
							.getCourseDocumentId()));
				}
				return scorecard;
			} else {
				LOG.debug("no active scorecards found for golfer id {}",
						golferId);
			}
		} catch (PersistenceException e) {
			LOG.error(e.getMessage());
			throw new ServiceException(e);
		}
		return null;
	};

	/**
	 * (non-Javadoc)
	 * 
	 * @throws AuthorisationException
	 * @throws ServiceException
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard(int,
	 *      java.lang.String)
	 */
	@Override
	@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED)
	public Scorecard startGeneralScorecard(String courseId)
			throws AuthorisationException, ServiceException {
		// check param
		if (StringUtils.isNotEmpty(courseId)) {
			try {

				// get the course
				Course courseToPlay = courseClubService.courseById(courseId);
				UserProfile loggedInUser = authorisationService
						.getLoggedInUser();
				if (courseToPlay != null) {
					scorecardDaoJpaImpl.hasActiveScoreacard(loggedInUser
							.getId());
					// get the logged in golfer for whom to create the scorecard
					if (loggedInUser instanceof Golfer) {
						Golfer golfer = (Golfer) loggedInUser;
						// persist the new scorecard
						Scorecard result = Scorecard.createNewScorecard(golfer,
								courseToPlay, golfer.getHandicap().intValue());
						scorecardDao.persist(result);
						return result;
					} else {
						Golfer golfer = profileService
								.getGolferByHandle(loggedInUser
										.getProfileHandle());
						// persist the new scorecard
						return scorecardDao.persist(Scorecard
								.createNewScorecard(golfer, courseToPlay,
										golfer.getHandicap().intValue()));
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
	 *      (java.lang.String, java.lang.String, java.util.HashMap,
	 *      java.util.LinkedList)
	 */
	@Override
	public Scorecard startGeneralScorecard(String golferId, String courseId,
			Map<String, String> hashMap, List<CommonIDEntity> clubList) {

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
	@Override
	public Scorecard startGeneralScorecard(String golferId, String courseId,
			int handicap, Map<String, String> hashMap,
			List<CommonIDEntity> clubList) {

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
	@Override
	public Scorecard startGeneralScorecard(String golferId,
			String scoreKeeperId, String courseId, Map<String, String> hashMap,
			List<CommonIDEntity> clubList) {
		throw new RuntimeException("not implemented");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startGeneralScorecard
	 *      (java.lang.String, java.lang.String, java.lang.String, int,
	 *      java.util.HashMap, java.util.LinkedList)
	 */
	@Override
	public Scorecard startGeneralScorecard(String golferId,
			String scoreKeeperId, String courseId, int handicap,
			Map<String, String> hashMap, List<CommonIDEntity> clubList) {
		throw new RuntimeException("not implemented");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#startCompetitionScorecard
	 *      (java.lang.String, java.lang.String, java.lang.String, int,
	 *      java.util.LinkedList)
	 */
	@Override
	public Scorecard startCompetitionScorecard(String golferId,
			String scoreKeeperId, String competitionId, int roundNumber,
			List<CommonIDEntity> clubList) throws ServiceException {

		// if there is no handicap passed, use the golfer's existing handicap
		return startCompetitionScorecard(golferId, scoreKeeperId,
				competitionId, roundNumber, clubList, EXISTING_GOLFER_HANDICAP);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws AuthorisationException
	 * @see com.gffny.ldrbrd.common.service.impl.IScorecardService#
	 *      startCompetitionScorecard(java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.util.LinkedList)
	 */
	@Override
	@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED)
	public Scorecard startCompetitionScorecard(String golferId,
			String scoreKeeperId, String competitionId, int roundNumber,
			List<CommonIDEntity> clubList, int competitionHandicap)
			throws ServiceException {

		// check param
		if (roundNumber > 0) {
			LOG.debug(
					"starting competition scorecard for golfer {} with scorekeeper {} for competition {} and round {} with handicap {}",
					golferId, scoreKeeperId, competitionId, roundNumber,
					competitionHandicap);
			try {
				CompetitionEntry competitionEntry = competitionService
						.getCompetitionRegistrationForGolfer(golferId,
								competitionId);
				// get competition from dao
				CompetitionRound competitionRound = competitionService
						.getCompetitionRound(competitionId, roundNumber);

				if (competitionEntry != null && competitionRound != null
						&& competitionRound.getCourse() != null) {
					Golfer golfer = profileService.getGolferById(golferId);
					int handicap = competitionHandicap == EXISTING_GOLFER_HANDICAP ? golfer
							.getHandicap() : competitionHandicap;
					// persist the new scorecard
					Scorecard newScorecard = scorecardDao.persist(Scorecard
							.createNewScorecard(golfer,
									competitionRound.getCourse(), handicap));
					// register the score for the competition
					competitionService.registerCompetitionScorecard(
							competitionEntry, newScorecard, competitionRound);
					// create the leaderboard round
					leaderboardService.startCompetitionRound(golfer,
							competitionRound, handicap);
					return newScorecard;
				}
			} catch (AuthorisationException | PersistenceException ae) {
				LOG.error(ae.getMessage(), ae);
				throw new ServiceException(ae.getMessage(), ae);
			}
		} else {
			LOG.error("round number cannot be less than 1");
			throw new ServiceException("round number cannot be less than 1");
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreHoleScoreMap(java
	 *      .lang.String, java.util.Map)
	 */
	@Override
	public void scoreHoleScoreMap(int scorecardId,
			Map<Integer, Integer> holeScoreMap) throws ServiceException {

		// check is list is valid
		if (MapUtils.isNotEmpty(holeScoreMap)) {
			// get the key set for the hole score map
			Set<Integer> holeScoreKeySet = holeScoreMap.keySet();
			// traverse hole map to score holes
			for (Integer holeScoreKey : holeScoreKeySet) {
				// score hole
				scoreHole(scorecardId, holeScoreKey.intValue(), holeScoreMap
						.get(holeScoreKey).intValue(), null);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreHoleArray(java.lang.String,
	 *      int[])
	 */
	@Override
	public void scoreHoleArray(String scorecardId, int[] scorecardArray)
			throws ServiceException {

		LOG.debug("scoring scorecard id {} with array of scores {}",
				scorecardId, Arrays.toString(scorecardArray));
		//
		if (StringUtils.isNotBlank(scorecardId)
				&& ArrayUtils.isNotEmpty(scorecardArray)) {
			try {
				// check if the scorecard is active
				Scorecard scorecard = scorecardDao.findById(Scorecard.class,
						Integer.parseInt(scorecardId));
				// check if scorecard is for a competition
				CompetitionRound competitionRound = competitionService
						.getCompetitionRoundByScorecardId(scorecardId);

				// if scorecard isn't null persist score to RDMS
				if (scorecard != null) {
					// traverse scorecard array
					int holeNumber = 1;
					for (int holeScore : scorecardArray) {
						// score each hole
						scoreHole(scorecard.getId(), holeNumber, holeScore,
								null, scorecard.isActive());
						// then publish
						publishHoleScore(holeNumber, holeScore, scorecard,
								competitionRound);
						holeNumber++;
					}
				}
				// TODO HANDLE CASE
			} catch (PersistenceException | NumberFormatException e) {
				LOG.error(e.getMessage());
				throw new ServiceException(e.getMessage(), e);
			}
		} else {
			LOG.error(
					"invalid parameters for scoreHoleArray. ID: {}, scorecardArray: {}, scorecardArray.length {}",
					scorecardId, scorecardArray, scorecardArray.length);
			throw new ServiceException("invalid parameters for scoreHoleArray");
		}
	}

	/**
	 * 
	 */
	@Override
	public void scoreHole(int scorecardId, int holeNumber, int holeScore,
			String holeId) throws ServiceException {

		try {
			// check if the scorecard is active
			Scorecard scorecard = scorecardDao.findById(Scorecard.class,
					scorecardId);

			// check if scorecard is for a competition
			CompetitionRound competitionRound = competitionService
					.getCompetitionRoundByScorecardId(String
							.valueOf(scorecardId));
			// if scorecard isn't null then score the hole to the RDMS
			if (scorecard != null) {
				scoreHole(scorecard.getId(), holeNumber, holeScore, holeId,
						scorecard.isActive());
				// then publish to the leaderboard
				publishHoleScore(holeNumber, holeScore, scorecard,
						competitionRound);
			}
		} catch (ServiceException | PersistenceException e) {
			LOG.error(e.getMessage(), e);
			scoreHole(scorecardId, holeNumber, holeScore, holeId, false);
		}

	}

	/**
	 * @param holeNumber
	 * @param holeScore
	 * @param scorecard
	 * @param competitionRound
	 * @throws ServiceException
	 */
	private void publishHoleScore(int holeNumber, int holeScore,
			Scorecard scorecard, CompetitionRound competitionRound)
			throws ServiceException {
		// if entry does not exist then it's a general scorecard
		if (competitionRound != null) {
			leaderboardService.publishHoleScore(scorecard.getGolfer(),
					competitionRound, holeNumber, holeScore, //
					ScoringUtils.toPar(competitionRound.getCourse(),
							holeNumber, holeScore), //
					ScoringUtils.toHandicapPar(competitionRound.getCourse(),
							holeNumber, holeScore, scorecard.getHandicap()), //
					ScoringUtils.competitionScore(competitionRound.getCourse(),
							holeNumber, holeScore, scorecard.getHandicap(),
							new Stableford()));
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#scoreHole(com.gffny
	 *      .ldrbrd.common.model.impl.HoleScore)
	 */
	private void scoreHole(int scorecardId, int holeNumber, int holeScore,
			String holeId, boolean scorecardIsActive) throws ServiceException {

		// if we don't know that the scorecard is active, then check
		if (!scorecardIsActive) {
			try {
				scorecardIsActive = scorecardDaoJpaImpl
						.isScorecardActive(scorecardId);
			} catch (PersistenceException e) {
				LOG.error(e.getMessage());
				throw new ServiceException(e.getMessage(), e);
			}
		}

		// check hole validity
		if (holeNumber > 0 && holeScore > 0 && scorecardIsActive) {
			try {
				scorecardDaoJpaImpl.scoreHole(scorecardId, holeNumber,
						holeScore, holeId);
			} catch (PersistenceException e) {
				LOG.error(e.getMessage());
				throw new ServiceException(e.getMessage(), e);
			}
		} else {
			if (!scorecardIsActive) {
				LOG.error("scorecard {} is not active", scorecardId);
				throw new ServiceException("scorecard is not active");
			}
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
	@Override
	public boolean checkScorecardScoreKeeper(String scorecardId,
			String scoreKeeperId) {

		// check the parameters
		if (scorecardId != null && scoreKeeperId != null) {
			throw new RuntimeException("not implemented");
		} else {
			LOG.error("method parameter(s) not valid");
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @return
	 * @throws ServiceException
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#submitScorecard(java
	 *      .lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED)
	public boolean submitScorecard(String scorecardId, String scoreKeeperId,
			String competitionId) throws ServiceException {

		// check the parameters
		if (scorecardId != null && scoreKeeperId != null
				&& competitionId != null) {
			throw new RuntimeException("not implemented");
		} else {
			LOG.error("method parameter(s) not valid");
		}
		throw new ServiceException("Should implement submitScorecard!");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws ServiceException
	 * @see com.gffny.ldrbrd.common.service.IScorecardService#signScorecard(java.
	 *      lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED)
	public boolean signScorecard(String scorecardId) throws ServiceException {

		LOG.debug("signing scorecard");
		UserProfile uProfile = authorisationService.getLoggedInUser();
		// check the parameters
		if (scorecardId != null && uProfile != null
				&& uProfile.getIdString() != null) {
			try {
				/*
				 * order: 1. check that the scorecard with id is active 2. check
				 * that the logged in user has authority to edit scorecard with
				 * this id 3. set Scorecard.isActive = false and apply signature
				 * 4. create and persist AnalysisScorecard
				 */
				// check if the scorecard is active and the user is authorised
				// to update it
				if (scorecardDaoJpaImpl.isScorecardActive(Integer
						.parseInt(scorecardId))
						&& authorisationService.isPermitted(
								uProfile.getIdString(), scorecardId)) {
					// set Scorecard.isActive = false and apply signature
					String signature = Security.encrypt(uProfile.getIdString()
							.concat(DateUtils.formatSignatureDate(new Date())));
					LOG.debug("signature for scorecard: {}", signature);
					boolean scorecardSigned = scorecardDaoJpaImpl
							.signScorecard(scorecardId, signature);
					// create an analysis scorecard and persist it
					// do this last because I'm not sure about transactional
					// support in Mongo (yet)

					return scorecardSigned;
				} else {
					// scorecard is inactive
					if (scorecardDaoJpaImpl.isScorecardActive(Integer
							.parseInt(scorecardId))) {
						LOG.error(
								"scorecard with id {} is not active and cannot be signed",
								scorecardId);
						throw new ValidationException(
								"scorecard is not active and cannot be signed");
					}
					if (authorisationService.isPermitted(
							uProfile.getIdString(), scorecardId)) {
						LOG.error(
								"user with id {} is not permited to update scorecard with id {}",
								uProfile.getIdString(), scorecardId);
						throw new ValidationException(
								"user is not permited to update scorecard");
					}
				}
			} catch (PersistenceException pex) {
				LOG.error("error retrieving scorecard for id {}", scorecardId);
				throw new ServiceException(pex);
			} catch (NumberFormatException nfex) {
				LOG.error("error converting scorecardId {} to an integer",
						scorecardId);
				throw new ServiceException(nfex);
			}
		} else {
			if (scorecardId == null) {
				LOG.error("scorecardId is null");
				throw new ValidationException("scorecardId is null");
			}
			if (uProfile == null || uProfile.getIdString() == null) {
				LOG.error("logged in user is null");
				throw new ValidationException(
						"logged in user is null or id is null");
			}
		}
		throw new ServiceException(
				"should not get to the end of the sign scorecard method without hitting some exception or, preferablly, returning!");

	}

	/**
	 * @param scorecard
	 * @return
	 */
	public final static String encodeScorecard(Scorecard scorecard) {
		if (scorecard != null) {
			throw new RuntimeException("not implemented");
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
	@Override
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
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IScorecardService#getLastXScorecards(
	 * java.lang.String)
	 */
	@Override
	public List<Scorecard> getLastXScorecards(String golferId)
			throws ServiceException {
		// TODO add the "last x" value to the profile
		return getLastXScorecards(golferId, NEED_TO_BE_PUSHED_INTO_PROFILE);
	}
}
