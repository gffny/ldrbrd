/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.mapping.CompetitionRegistration;
import com.gffny.ldrbrd.common.service.ICompetitionService;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
@Service
public class CompetitionService extends AbstractService implements
		ICompetitionService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(CompetitionService.class);

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
	private GenericDao<CompetitionRegistration> competitionRegistrationDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.impl.ICompetitionService#createCompetition
	 * (java.lang.String)
	 */
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
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
			throw new ServiceException(daEx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.impl.ICompetitionService#
	 * createCompetitionRound(com.gffny.ldrbrd.common.model.impl.Competition,
	 * org.joda.time.DateTime, java.lang.Integer,
	 * com.gffny.ldrbrd.common.model.impl.Course)
	 */
	public CompetitionRound createCompetitionRound(Competition competition,
			DateTime roundDate, Integer roundNumber, Course course)
			throws ServiceException {
		CompetitionRound newCompetitionRound = CompetitionRound
				.createNewCompetitionRound(competition, roundDate, roundNumber,
						course);
		try {
			return competitionRoundDao.persist(newCompetitionRound);
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
			throw new ServiceException(daEx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionById
	 * (java.lang.String)
	 */
	public Competition getCompetitionById(String competitionId)
			throws ServiceException {
		try {
			// get the competition by ID!
			return competitionDao.findById(Competition.class, competitionId);
			// contain error and bubble (expected pattern)
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
			throw new ServiceException(daEx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionByName
	 * (java.lang.String)
	 */
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
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
			throw new ServiceException(daEx);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.impl.ICompetitionService#getCompetitionRound
	 * (java.lang.String, java.lang.Integer)
	 */
	// TODO consider changing return type to List<CompetitionRound>
	@Transactional(readOnly = true)
	public CompetitionRound getCompetitionRound(String competitionId,
			Integer roundNumber) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("competitionId", competitionId);
		params.put("roundNumber", roundNumber);
		return namedQuerySingleResultOrNull(competitionRoundDao,
				CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR, params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#
	 * getCompetitionListForGolfer(java.lang.String)
	 */
	public List<CompetitionRegistration> getCompetitionListForGolfer(
			String golferId) throws ServiceException {

		// competitionRegistrationDao
		try {
			// build param map
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("golferId", golferId);
			// query database for competitions
			List<CompetitionRegistration> competitionRegistrationList = competitionRegistrationDao
					.findByNamedQuery(
							CompetitionRegistration.COMPETITION_LIST_BY_GOLFER_ID,
							paramMap);
			// check the return
			if (competitionRegistrationList != null
					&& competitionRegistrationList.size() >= 0) {
				return competitionRegistrationList;
			} else {
				// if bad / give out
				LOG.error("competition list return is null");
				throw new ServiceException("competition list return is null");
			}
		} catch (DataAccessException daEx) {
			// log 'n' bubble pattern
			LOG.error(daEx.getMessage());
			throw new ServiceException(daEx);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionRegistrationForGolfer(com.gffny.ldrbrd.common.model.impl.GolferProfile,
	 *      com.gffny.ldrbrd.common.model.impl.Competition)
	 */
	public CompetitionRegistration getCompetitionRegistrationForGolfer(
			GolferProfile golfer, Competition competition)
			throws ServiceException {
		if (golfer != null && competition != null) {
			try {
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("golferId", golfer.getId());
				paramMap.put("competitionId", competition.getId());
				List<CompetitionRegistration> competitionRegistrationList = competitionRegistrationDao
						.findByNamedQuery(
								CompetitionRegistration.REGISTRATION_BY_GOLFER_ID_AND_COMPETITION_ID,
								paramMap);
				if (competitionRegistrationList != null
						&& competitionRegistrationList.size() == 1) {
					return competitionRegistrationList.get(0);
				} else {
					LOG.error("incorrect results from the competition registration query");
					throw new ServiceException(
							"incorrect results from the competition registration query");
				}
			} catch (DataAccessException daEx) {
				LOG.error(daEx.getMessage());
				throw new ServiceException(daEx);
			}
		} else {
			LOG.error("parameters are malformed");
			throw new ServiceException("parameters are malformed");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#
	 *      registerGolferForCompetitionWithHandicap
	 *      (com.gffny.ldrbrd.common.model.impl.GolferProfile,
	 *      com.gffny.ldrbrd.common.model.impl.Competition, int)
	 */
	public void registerGolferForCompetitionWithHandicap(GolferProfile golfer,
			Competition competition, int handicap) throws ServiceException {

		// check if the values are valid
		if (golfer != null && competition != null && handicap > 0) {
			try {
				CompetitionRegistration registration = new CompetitionRegistration(
						golfer, competition, handicap);
				competitionRegistrationDao.persist(registration);
			} catch (DataAccessException daEx) {
				// log 'n' bubble
				LOG.error(daEx.getMessage());
				throw new ServiceException(daEx);
			}
		}
	}

	/**
	 * Uses the golfers handicap to register the golfer in the competition
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#
	 *      registerGolferForCompetition
	 *      (com.gffny.ldrbrd.common.model.impl.GolferProfile,
	 *      com.gffny.ldrbrd.common.model.impl.Competition)
	 */
	public void registerGolferForCompetition(GolferProfile golfer,
			Competition competition) throws ServiceException {
		registerGolferForCompetitionWithHandicap(golfer, competition,
				golfer.getHandicap());
	}
}
