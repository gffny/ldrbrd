/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.HashMap;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.persistence.GenericDao;
import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.IScorecardService;

/**
 * @author jdgaffney
 * 
 */
@Service
public class ScorecardService implements IScorecardService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(ScorecardService.class);

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
	private GenericDao<Course> courseDao;

	/**
	 * 
	 */
	@Autowired
	private ICompetitionService competitionService;

	/* (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.IScorecardService#startGeneralScorecard(java.lang.String, java.lang.String, int, java.util.HashMap, java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId,
			int handicap, HashMap<String, String> hashMap,
			LinkedList<GolfClub> linkedList) {
		try {
			GolferProfile golfer = golferDao.findById(GolferProfile.class,
					golferId);
			Course course = courseDao.findById(Course.class, courseId);
			if (golfer != null && course != null) {
				scorecardDao.persist(Scorecard.createNewScorecard(golfer,
						course, handicap));
			} else {
				LOG.error("golfer is null: " + golferId);
			}
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.IScorecardService#startGeneralScorecard(java.lang.String, java.lang.String, java.util.HashMap, java.util.LinkedList)
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId,
			HashMap<String, String> hashMap, LinkedList<GolfClub> linkedList) {
		try {
			GolferProfile golfer = golferDao.findById(GolferProfile.class,
					golferId);
			Course course = courseDao.findById(Course.class, courseId);
			if (golfer != null && course != null) {
				golfer.getHandicap();
				scorecardDao.persist(Scorecard.createNewScorecard(golfer,
						course, golfer.getHandicap()));
			} else {
				LOG.error("golfer is null: " + golferId);
			}
		} catch (DataAccessException daEx) {
			LOG.error(daEx.toString());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.IScorecardService#startCompetitionScorecard(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.util.LinkedList)
	 */
	public Scorecard startCompetitionScorecard(String scorerId,
			String scoredId, String competitionId, Integer roundNumber,
			LinkedList<GolfClub> linkedList) {
		try {
			GolferProfile scorer = golferDao.findById(GolferProfile.class,
					scorerId);
			GolferProfile golfer = golferDao.findById(GolferProfile.class,
					scoredId);
			CompetitionRound competitionRound = competitionService
					.getCompetitionRound(competitionId, roundNumber);

			if (scorer != null && golfer != null && competitionRound != null) {
				scorecardDao.persist(Scorecard.createNewCompetitionScorecard(
						golfer, competitionRound, golfer.getHandicap()));
			}
		} catch (DataAccessException daEx) {
			LOG.error("error with competition scorecard");
		}
		return null;
	}
}
