/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.persistence.GenericDao;

/**
 * @author jdgaffney
 * 
 */
@Service
public class CompetitionService {

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
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 */
	@Transactional(readOnly = true)
	public CompetitionRound getCompetitionRound(String competitionId,
			Integer roundNumber) {
		Map<String, Object> params = Collections.emptyMap();
		params.put("competitionId", competitionId);
		params.put("roundNumber", roundNumber);
		List<CompetitionRound> competitionRoundList = competitionRoundDao
				.findByNamedQuery(
						CompetitionRound.FIND_BY_COMP_ID_AND_RND_NMBR, params);
		return competitionRoundList.get(0);
	}

	/**
	 * 
	 * @param competitionName
	 * @return
	 */
	public Competition createCompetition(String competitionName) {
		Competition newCompetition = Competition
				.createNewCompetition(competitionName);
		try {
			return competitionDao.persist(newCompetition);
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return newCompetition;
	}

	/**
	 * 
	 * @param competition
	 * @param roundDate
	 * @param roundNumber
	 * @return
	 */
	public CompetitionRound createCompetitionRound(Competition competition,
			DateTime roundDate, Integer roundNumber, Course course) {
		CompetitionRound newCompetitionRound = CompetitionRound
				.createNewCompetitionRound(competition, roundDate, roundNumber,
						course);
		try {
			return competitionRoundDao.persist(newCompetitionRound);
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return newCompetitionRound;
	}
}
