/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.dao.nosql.GenericNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionEntry;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.CompetitionRoundScore;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.model.nosql.Club;
import com.gffny.ldrbrd.common.model.nosql.Course;
import com.gffny.ldrbrd.common.service.ICompetitionService;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 */
@Service
public class CompetitionService extends AbstractService implements
		ICompetitionService {

	/** TODO: Make this a config */
	private static final int NUM_COMP_ROUNDS = 5;

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(CompetitionService.class);

	/** */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<Competition> competitionDao;

	/** */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<CompetitionRound> competitionRoundDao;

	/** */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<CompetitionEntry> competitionRegistrationDao;

	/** */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<CompetitionRoundScore> competitionRoundScoreDao;

	/** */
	@Autowired
	private GenericNoSqlDao<Club> clubMongoDaoImpl;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.impl.ICompetitionService#createCompetition
	 *      (java.lang.String)
	 */
	@Override
	@Transactional(value = "lrdbrd_txnMgr", propagation = Propagation.REQUIRED)
	public Competition createCompetition(String competitionName)
			throws ServiceException {
		try {

			// check if the competition already exists
			Competition competition = getCompetitionByName(competitionName);
			if (competition != null) {
				return competition;
			} else {
				// else, create a new one
				Competition newCompetition = Competition
						.createNewCompetition(competitionName);
				return competitionDao.persist(newCompetition);
			}
		} catch (PersistenceException daEx) {
			LOG.error(daEx.toString());
			throw new ServiceException(daEx);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionById
	 *      (java.lang.String)
	 */
	@Override
	public Competition getCompetitionById(String competitionId)
			throws ServiceException {
		try {
			// get the competition by ID!
			return competitionDao.findById(Competition.class,
					Integer.parseInt(competitionId));
			// contain error and bubble (expected pattern)
		} catch (PersistenceException daEx) {
			LOG.error(daEx.toString(), daEx);
			throw new ServiceException(daEx);
		} catch (NumberFormatException nfe) {
			LOG.error(nfe.getMessage(), nfe);
			throw new ServiceException(nfe);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionByName
	 *      (java.lang.String)
	 */
	@Override
	public Competition getCompetitionByName(String competitionName)
			throws ServiceException {
		try {

			// check if the competition already exists
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("competitionName", competitionName);
			List<Competition> existingCompetitionList = competitionDao
					.findByNamedQuery(Competition.FIND_BY_COMPETITION_NAME,
							paramMap);
			if (existingCompetitionList != null
					&& existingCompetitionList.size() == 1) {

				// if it does, return it
				return existingCompetitionList.get(0);
			} else {
				if (existingCompetitionList.size() == 0) {
					LOG.debug("no competition found with the name {}",
							competitionName);
					return null;
				} else if (existingCompetitionList.size() > 1) {
					LOG.error(
							"more than one competition found with the name {}",
							competitionName);
					throw new ServiceException(
							"more than one competition found with the name "
									+ competitionName);
				}
				LOG.error(
						"error with the result set returned for the competition name {}",
						competitionName);
				throw new ServiceException(
						"error with the result set returned for the competition name "
								+ competitionName);
			}
		} catch (PersistenceException daEx) {
			LOG.error(daEx.toString());
			throw new ServiceException(daEx);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionListForGolfer(java.lang.String)
	 */
	@Override
	public List<CompetitionEntry> getCompetitionListForGolfer(String golferId)
			throws ServiceException {
		if (StringUtils.isNotBlank(golferId)) {
			// TODO fix the competition service to register
			return new ArrayList<CompetitionEntry>();
		}
		throw new ServiceException("invalid parameter: golfer " + golferId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#listCompetitionRoundForGolfer(java.lang.String)
	 */
	@Override
	public List<CompetitionRound> listCompetitionRoundForGolfer(
			String golferId) throws ServiceException {
		// check params
		if (StringUtils.isNotBlank(golferId)) {
			List<CompetitionRound> competitionRoundList = namedQueryListResultOrNull(
					competitionRoundDao,
					CompetitionRound.FIND_NON_COMPLETE_BY_GOLFER_ID_IN_COMPETITION_ENTRY,
					populateParamMap("golferId", Integer.parseInt(golferId)),
					NUM_COMP_ROUNDS);
			// check the list is not null or empty
			if (CollectionUtils.isNotEmpty(competitionRoundList)) {
				// traverse list and populate course;
				Map<String, Course> courseMap = new HashMap<String, Course>();
				int i = 0;
				for (CompetitionRound cr : competitionRoundList) {
					// check if the course has already been retrieved from mongo
					if (courseMap.containsKey(cr.getCourseDocumentId())) {
						cr.setCourse(courseMap.get(cr.getCourseDocumentId()));
					} else {
						// otherwise get retrieve it, inject it and store it
						try {
							cr = populateCourseForCompetitionRound(cr);
							courseMap.put(cr.getCourseDocumentId(),
									cr.getCourse());
						} catch (PersistenceException pe) {
							LOG.error(pe.getMessage(), pe);
							throw new ServiceException(pe.getMessage(), pe);
						}
					}
					competitionRoundList.set(i, cr);
					i++;
				}
			}
			return competitionRoundList;
		} else {
			LOG.error("invalid parameter; golferId was null or empty");
			throw new ServiceException(
					"invalid parameter; golferId was null or empty");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.impl.ICompetitionService#
	 *      createCompetitionRound(com.gffny.ldrbrd.common.model.impl.Competition,
	 *      org.joda.time.DateTime, java.lang.Integer,
	 *      com.gffny.ldrbrd.common.model.impl.Course)
	 */
	@Override
	@Transactional(value = "lrdbrd_txnMgr", propagation = Propagation.REQUIRED)
	public CompetitionRound createCompetitionRound(Competition competition,
			DateTime roundDate, Integer roundNumber, Course course)
			throws ServiceException {

		// TODO fix the competition service to createCompetitionRound
		return new CompetitionRound();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionRound
	 *      (java.lang.String, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public CompetitionRound getCompetitionRound(String competitionId,
			Integer roundNumber) throws ServiceException {
		try {
			CompetitionRound competitionRound = namedQuerySingleResultOrNull(
					competitionRoundDao,
					CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR,
					populateParamMap("competitionId",
							Integer.valueOf(competitionId), "roundNumber",
							roundNumber));
			return populateCourseForCompetitionRound(competitionRound);
		} catch (ServiceException | NumberFormatException
				| PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws ServiceException
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionRoundByScorecardId(java.lang.String)
	 */
	@Override
	public CompetitionRound getCompetitionRoundByScorecardId(String scorecardId)
			throws ServiceException {
		if (StringUtils.isNotBlank(scorecardId)) {
			try {
				LOG.debug("retrieving competition round by scorecard id {}",
						scorecardId);

				CompetitionRoundScore competitionRoundScore = namedQuerySingleResultOrNull(
						competitionRoundScoreDao,
						CompetitionRoundScore.FIND_BY_SCORECARD_ID,
						populateParamMap("scorecardId",
								Integer.valueOf(scorecardId)));
				if (competitionRoundScore != null
						&& competitionRoundScore.getCompetitionRound() != null) {
					return populateCourseForCompetitionRound(competitionRoundScore
							.getCompetitionRound());
				}
				LOG.error(
						"no round score returned for scorecard id {}, or round was null",
						scorecardId);
			} catch (ServiceException | PersistenceException
					| NumberFormatException e) {
				LOG.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage(), e);
			}
		}
		LOG.error("invalid parameters: scorecardId and/or roundNumber is null");
		return null;
	}

	/**
	 * @param competitionRound
	 * @return
	 * @throws PersistenceException
	 */
	private CompetitionRound populateCourseForCompetitionRound(
			CompetitionRound competitionRound) throws PersistenceException {
		// check params
		if (competitionRound != null) {
			LOG.debug("populating course object with courseId {}",
					competitionRound.getCourseDocumentId());
			competitionRound.setCourse(clubMongoDaoImpl.findById(Course.class,
					competitionRound.getCourseDocumentId()));
		}
		return competitionRound;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws ServiceException
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionRoundById(java.lang.String)
	 */
	@Override
	public CompetitionRound getCompetitionRoundById(String competitionRoundId)
			throws ServiceException {
		// check params
		if (StringUtils.isNotBlank(competitionRoundId)) {
			LOG.debug("getting the competition round by id {}",
					competitionRoundId);
			try {
				CompetitionRound cr = competitionRoundDao.findById(
						CompetitionRound.class,
						Integer.parseInt(competitionRoundId));
				return populateCourseForCompetitionRound(cr);
			} catch (NumberFormatException | PersistenceException e) {
				LOG.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage(), e);
			}
		}
		LOG.error("invalid parameters: competitionRoundId is null or blank");
		throw new ServiceException(
				"invalid parameters: competitionRoundId is null or blank");
	}

	/**
	 * Uses the golfers handicap to register the golfer in the competition
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#
	 *      registerGolferForCompetition
	 *      (com.gffny.ldrbrd.common.model.impl.Golfer,
	 *      com.gffny.ldrbrd.common.model.impl.Competition)
	 */
	@Override
	@Transactional(value = "lrdbrd_txnMgr", propagation = Propagation.REQUIRED)
	public CompetitionEntry registerGolferForCompetition(Golfer golfer,
			Competition competition) throws ServiceException {

		// check if the values are valid
		if (golfer != null && competition != null) {
			try {
				LOG.debug("registering golfer {} for competition {}",
						golfer.getId(), competition.getId());
				return competitionRegistrationDao.persist(new CompetitionEntry(
						competition, golfer));
			} catch (PersistenceException e) {
				LOG.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage(), e);
			}
		}
		LOG.error("invalid parameter: golfer and/or competition invalid");
		throw new ServiceException(
				"invalid parameter: golfer and/or competition invalid");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionRegistrationForGolfer(com.gffny.ldrbrd.common.model.impl.Golfer,
	 *      com.gffny.ldrbrd.common.model.impl.Competition)
	 */
	@Override
	public CompetitionEntry getCompetitionRegistrationForGolfer(
			String golferId, String competitionId) throws ServiceException {
		if (StringUtils.isNotBlank(golferId)
				&& StringUtils.isNotBlank(competitionId)) {

			return namedQuerySingleResultOrNull(
					competitionRegistrationDao,
					CompetitionEntry.FIND_BY_COMPETITION_AND_GOLFER,
					populateParamMap("competitionId",
							Integer.valueOf(competitionId), "golferId",
							Integer.valueOf(golferId)));

		} else {
			LOG.error("parameters are malformed");
			throw new ServiceException(
					"invalid parameter: golfer id or competition id");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#
	 *      getCompetitionRegistrationByScorecardId(java.lang.String)
	 */
	@Override
	public CompetitionEntry getCompetitionRegistrationByScorecardId(
			String scorecardId) throws ServiceException {
		// check params
		if (StringUtils.isNotBlank(scorecardId)) {
			CompetitionRoundScore competitionRoundScore = namedQuerySingleResultOrNull(
					competitionRoundScoreDao,
					CompetitionRoundScore.FIND_BY_SCORECARD_ID,
					populateParamMap("scorecardId",
							Integer.valueOf(scorecardId)));
			return (competitionRoundScore == null ? null
					: competitionRoundScore.getCompetitionEntry());

		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#registerCompetitionScorecard(int,
	 *      com.gffny.ldrbrd.common.model.impl.Scorecard)
	 */
	@Override
	public CompetitionRoundScore registerCompetitionScorecard(
			CompetitionEntry competitionEntry, Scorecard scorecard,
			CompetitionRound competitionRound) throws ServiceException {
		// check params
		if (scorecard != null && competitionEntry != null
				&& competitionRound != null) {
			try {
				return competitionRoundScoreDao
						.persist(CompetitionRoundScore.instance(
								competitionEntry, scorecard, competitionRound));
			} catch (PersistenceException e) {
				LOG.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage(), e);
			}
		}
		LOG.error("invalid parameters: scorecard, competitionEntry, or competitionRound is null");
		throw new ServiceException(
				"invalid parameters: scorecard, competitionEntry, or competitionRound is null");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionRoundScoreByScorecardId(java.lang.String)
	 */
	@Override
	public CompetitionRoundScore getCompetitionRoundScoreByScorecardId(
			String scorecardId) throws ServiceException {
		// check params
		if (StringUtils.isNotBlank(scorecardId)) {
			return namedQuerySingleResultOrNull(
					competitionRoundScoreDao,
					CompetitionRoundScore.FIND_BY_SCORECARD_ID,
					populateParamMap("scorecardId",
							Integer.valueOf(scorecardId)));
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#
	 *      isCompetitionRoundScoreCompleteForScorecardId(java.lang.String)
	 */
	@Override
	public boolean isCompetitionRoundScoreCompleteForScorecardId(
			String scorecardId) {
		// check params
		if (StringUtils.isNotBlank(scorecardId)) {
			try {
				CompetitionRoundScore crs = getCompetitionRoundScoreByScorecardId(scorecardId);
				return crs != null ? crs.isComplete() : false;
			} catch (ServiceException e) {
				LOG.error(e.getMessage(), e);
				return false;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public boolean isCompetitionRoundScoreCompleteForScorecard(String golferId,
			String competitionRoundId) {
		// check params
		if (StringUtils.isNotBlank(golferId)
				&& StringUtils.isNotBlank(competitionRoundId)) {
			try {
				CompetitionRoundScore crs = namedQuerySingleResultOrNull(
						competitionRoundScoreDao,
						CompetitionRoundScore.FIND_BY_COMPETITION_ROUND_ID_AND_GOLFER_ID,
						populateParamMap("golferId", Integer.valueOf(golferId),
								"competitionRoundId",
								Integer.valueOf(competitionRoundId)));
				return crs != null ? crs.isComplete() : false;
			} catch (ServiceException e) {
				LOG.error(e.getMessage(), e);
				return false;
			}
		}
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#signScorecard(java.lang.String)
	 */
	@Override
	public void signScorecard(String scorecardId, String scorerSignature,
			String golferSignature) throws ServiceException {
		// get the crs (param checking done there)
		CompetitionRoundScore crs = getCompetitionRoundScoreByScorecardId(scorecardId);
		// check if the round score exists and if is no already complete
		if (crs != null && !crs.isComplete()) {
			crs.setComplete(true);
			crs.setScorerSignature(scorerSignature);
			crs.setGolferSignature(golferSignature);
			try {
				competitionRoundScoreDao.merge(crs);
				LOG.debug("signed competition round score");
			} catch (PersistenceException e) {
				LOG.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage(), e);
			}
		}
	}
}
