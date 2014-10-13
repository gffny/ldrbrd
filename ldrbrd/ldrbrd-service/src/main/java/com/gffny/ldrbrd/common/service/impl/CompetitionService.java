/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionEntry;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;
import com.gffny.ldrbrd.common.service.ICompetitionService;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 */
@Service
public class CompetitionService extends AbstractService implements ICompetitionService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory.getLogger(CompetitionService.class);

	/**
	 * 
	 */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<Competition> competitionDao;

	/**
	 * 
	 */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<CompetitionRound> competitionRoundDao;

	/**
	 * 
	 */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<CompetitionEntry> competitionRegistrationDao;

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.ICompetitionService#createCompetition
	 * (java.lang.String)
	 */
	@Transactional(value = "lrdbrd_txnMgr", propagation = Propagation.REQUIRED)
	public Competition createCompetition(String competitionName) throws ServiceException {
		try {

			// check if the competition already exists
			Competition competition = getCompetitionByName(competitionName);
			if (competition != null) {
				return competition;
			} else {
				// else, create a new one
				Competition newCompetition = Competition.createNewCompetition(competitionName);
				return competitionDao.persist(newCompetition);
			}
		} catch (PersistenceException daEx) {
			LOG.error(daEx.toString());
			throw new ServiceException(daEx);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.ICompetitionService#
	 * createCompetitionRound(com.gffny.ldrbrd.common.model.impl.Competition,
	 * org.joda.time.DateTime, java.lang.Integer, com.gffny.ldrbrd.common.model.impl.Course)
	 */
	@Transactional(value = "lrdbrd_txnMgr", propagation = Propagation.REQUIRED)
	public CompetitionRound createCompetitionRound(Competition competition, DateTime roundDate,
			Integer roundNumber, Course course) throws ServiceException {

		// TODO fix the competition service to createCompetitionRound
		return new CompetitionRound();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#registerGolferForCompetitionWithHandicap(com.gffny.ldrbrd.common.model.impl.Golfer,
	 *      com.gffny.ldrbrd.common.model.impl.Competition, int)
	 */
	@Transactional(value = "lrdbrd_txnMgr", propagation = Propagation.REQUIRED)
	public CompetitionEntry registerGolferForCompetitionWithHandicap(Golfer golfer,
			Competition competition, int handicap) throws ServiceException {

		// check if the values are valid
		if (golfer != null && competition != null && handicap > 0) {

			// TODO fix the competition service to register
			return new CompetitionEntry();

		}
		throw new ServiceException("invalid parameter: golfer " + golfer.toString()
				+ ", competition " + competition.toString() + ", handicap " + handicap);
	}

	/**
	 * Uses the golfers handicap to register the golfer in the competition (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService# registerGolferForCompetition
	 *      (com.gffny.ldrbrd.common.model.impl.Golfer,
	 *      com.gffny.ldrbrd.common.model.impl.Competition)
	 */
	@Transactional(value = "lrdbrd_txnMgr", propagation = Propagation.REQUIRED)
	public CompetitionEntry registerGolferForCompetition(Golfer golfer, Competition competition)
			throws ServiceException {
		return registerGolferForCompetitionWithHandicap(golfer, competition, golfer.getHandicap());
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionById
	 * (java.lang.String)
	 */
	public Competition getCompetitionById(String competitionId) throws ServiceException {
		try {
			// get the competition by ID!
			return competitionDao.findById(Competition.class, competitionId);
			// contain error and bubble (expected pattern)
		} catch (PersistenceException daEx) {
			LOG.error(daEx.toString());
			throw new ServiceException(daEx);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionByName
	 * (java.lang.String)
	 */
	public Competition getCompetitionByName(String competitionName) throws ServiceException {
		try {

			// check if the competition already exists
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("competitionName", competitionName);
			List<Competition> existingCompetitionList = competitionDao.findByNamedQuery(
					Competition.FIND_BY_COMPETITION_NAME, paramMap);
			if (existingCompetitionList != null && existingCompetitionList.size() == 1) {

				// if it does, return it
				return existingCompetitionList.get(0);
			} else {
				if (existingCompetitionList.size() == 0) {
					LOG.debug("no competition found with the name {}", competitionName);
					return null;
				} else if (existingCompetitionList.size() > 1) {
					LOG.error("more than one competition found with the name {}", competitionName);
					throw new ServiceException("more than one competition found with the name "
							+ competitionName);
				}
				LOG.error("error with the result set returned for the competition name {}",
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionRound(java.lang.String,
	 * java.lang.Integer)
	 */
	@Transactional(readOnly = true)
	public CompetitionRound getCompetitionRound(String competitionId, Integer roundNumber)
			throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("competitionId", competitionId);
		params.put("roundNumber", roundNumber);
		return namedQuerySingleResultOrNull(competitionRoundDao,
				CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR, params);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionListForGolfer(java.lang.String)
	 */
	public List<CompetitionEntry> getCompetitionListForGolfer(String golferId)
			throws ServiceException {
		if (golferId != null) {
			// TODO fix the competition service to register
			return new ArrayList<CompetitionEntry>();
		}
		throw new ServiceException("invalid parameter: golfer " + golferId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ICompetitionService#getCompetitionRegistrationForGolfer(com.gffny.ldrbrd.common.model.impl.Golfer,
	 *      com.gffny.ldrbrd.common.model.impl.Competition)
	 */
	public CompetitionEntry getCompetitionRegistrationForGolfer(Golfer golfer,
			Competition competition) throws ServiceException {
		if (golfer != null && competition != null) {

			// TODO fix the competition service to get competition
			return new CompetitionEntry();

		} else {
			LOG.error("parameters are malformed");
			throw new ServiceException("invalid parameter: golfer " + golfer + ", competition "
					+ competition);
		}
	}

}
