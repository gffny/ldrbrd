/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.nosql.IHoleScoreNoSqlDao;
import com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.nosql.HoleScore;
import com.gffny.ldrbrd.common.model.nosql.RoundScore;
import com.gffny.ldrbrd.common.service.ILeaderboardService;
import com.gffny.ldrbrd.common.utils.PublishScorecardUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Service
public class LeaderboardService extends AbstractService implements
		ILeaderboardService {

	/** */
	@Autowired
	private IRoundScoreNoSqlDao roundScoreMongoDaoImpl;

	/** */
	@Autowired
	private IHoleScoreNoSqlDao holeScoreMongoDaoImpl;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ILeaderboardService#startCompetitionRound(com.gffny.ldrbrd
	 *      .common.model.impl.Golfer, int,
	 *      com.gffny.ldrbrd.common.model.impl.CompetitionRound)
	 */
	@Override
	public void startCompetitionRound(Golfer golfer,
			CompetitionRound competitionRound, int handicap)
			throws ServiceException {
		// check params
		if (golfer != null && competitionRound != null && handicap >= 0) {
			LOG.debug("starting competition round");
			try {
				RoundScore rs = roundScoreMongoDaoImpl
						.findGolferRoundScoreByCompetitionRound(
								golfer.getIdString(),
								competitionRound.getIdString());
				// if rs is not null then the round exists
				if (rs == null) {
					LOG.debug(
							"round did not exist in document store for compeitionRoundId {} and golfer {}",
							competitionRound.getIdString(),
							golfer.getIdString());
					rs = RoundScore.instance(golfer.getIdString(),
							competitionRound.getIdString());
					rs.setCompetitionId(competitionRound.getCompetition()
							.getIdString());
					rs.setRoundNumber(competitionRound.getRoundNumber());
					if (competitionRound.getRoundNumber() > 1) {
						LOG.debug("getting round score for previous round");
						// set the overview score from the previous round as the
						// next round initial scores
						PublishScorecardUtils
								.initialiseRoundScoreValues(
										rs,
										roundScoreMongoDaoImpl
												.findGolferRoundScoreByCompetitionRound(
														golfer.getIdString(),
														competitionRound
																.getCompetition()
																.getIdString(),
														(competitionRound
																.getRoundNumber() - 1)));
					} else {
						LOG.debug("setting values for initial competition round");
						PublishScorecardUtils.initialiseRoundScoreValues(rs);
					}
					roundScoreMongoDaoImpl.persist(rs);
				}
			} catch (PersistenceException pex) {
				LOG.error(pex.getMessage(), pex);
				throw new ServiceException(pex.getMessage(), pex);
			}
		}
	}

	/**
	 * 
	 * @param golfer
	 * @param competitionRound
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 * @throws ServiceException
	 */
	@Override
	public void publishHoleScore(Golfer golfer,
			CompetitionRound competitionRound, int holeNumber, int holeScore,
			int toPar, int toHandicapPar, int competitionScore)
			throws ServiceException {
		if (golfer != null && competitionRound != null && holeNumber > 0
				&& holeScore > 0) {
			LOG.debug("starting competition round");
			try {
				publishToRoundScore(golfer, competitionRound, holeNumber,
						holeScore, toPar, toHandicapPar, competitionScore);
				publishToHoleScore(golfer, competitionRound, holeNumber,
						holeScore, toPar, toHandicapPar, competitionScore);
			} catch (PersistenceException pex) {
				LOG.error(pex.getMessage(), pex);
				throw new ServiceException(pex.getMessage(), pex);
			}
		}
	}

	/**
	 * @param golfer
	 * @param competitionRound
	 * @param holeNumber
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 * @throws PersistenceException
	 * @throws ServiceException
	 */
	private void publishToRoundScore(Golfer golfer,
			CompetitionRound competitionRound, int holeNumber, int holeScore,
			int toPar, int toHandicapPar, int competitionScore)
			throws PersistenceException, ServiceException {
		RoundScore rs = roundScoreMongoDaoImpl
				.findGolferRoundScoreByCompetitionRound(golfer.getIdString(),
						competitionRound.getIdString());
		if (rs != null) {
			PublishScorecardUtils.setScorecardHoleScore(rs, holeNumber,
					holeScore, toPar, toHandicapPar, competitionScore);
			roundScoreMongoDaoImpl.merge(rs);
		} else {
			LOG.error(
					"round score not started for golfer {} and competition round {}",
					golfer.getIdString(), competitionRound.getIdString());
			throw new ServiceException(
					"round score not started for golfer and competition round");
		}
	}

	/**
	 * @param golfer
	 * @param competitionRound
	 * @param holeNumber
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 * @throws PersistenceException
	 * @throws ServiceException
	 */
	private void publishToHoleScore(Golfer golfer,
			CompetitionRound competitionRound, int holeNumber, int holeScore,
			int toPar, int toHandicapPar, int competitionScore)
			throws PersistenceException, ServiceException {
		LOG.info(
				"publishing hole score; golfer {}, competitionRound {}, holeNumber {}, holeScore {}",
				golfer.getIdString(), competitionRound.getIdString(),
				holeNumber, holeScore);
		// check for an existing hole score
		HoleScore hs = holeScoreMongoDaoImpl
				.findGolferHoleScoreByCompetitionRound(golfer.getIdString(),
						competitionRound.getIdString(), holeNumber);
		// if hs is not null then there is an existing hole score
		if (hs == null) {
			LOG.debug("persisting a new hole score");
			// use previous hole to set the values
			if (holeNumber > 1) {
				HoleScore prevHs = holeScoreMongoDaoImpl
						.findGolferHoleScoreByCompetitionRound(
								golfer.getIdString(),
								competitionRound.getIdString(), holeNumber);
				if (prevHs != null) {
					hs = HoleScore.instance(competitionRound.getCompetition()
							.getIdString(), competitionRound.getIdString(),
							String.valueOf(competitionRound.getRoundNumber()),
							competitionRound.getCourseDocumentId(), golfer
									.getIdString(), new Date(), holeNumber,
							holeScore, competitionScore, toPar, toHandicapPar,
							prevHs.getOverviewScore() + holeScore,
							prevHs.getOverviewToPar() + toPar,
							prevHs.getOverviewToHandicapPar() + toHandicapPar,
							prevHs.getOverviewCompetitionScore()
									+ competitionScore);
				} else {
					LOG.error(
							"no previous hole {} recorded; setting overall values to 0",
							holeNumber - 1);
					hs = HoleScore.instance(competitionRound.getCompetition()
							.getIdString(), competitionRound.getIdString(),
							String.valueOf(competitionRound.getRoundNumber()),
							competitionRound.getCourseDocumentId(), golfer
									.getIdString(), new Date(), holeNumber,
							holeScore, competitionScore, toPar, toHandicapPar,
							holeScore, toPar, toHandicapPar, competitionScore);
				}
			} else {
				// set the hole score with the initial score value
				hs = HoleScore.instance(competitionRound.getCompetition()
						.getIdString(), competitionRound.getIdString(), String
						.valueOf(competitionRound.getRoundNumber()),
						competitionRound.getCourseDocumentId(), golfer
								.getIdString(), new Date(), holeNumber,
						holeScore, competitionScore, toPar, toHandicapPar,
						holeScore, toPar, toHandicapPar, competitionScore);
			}
			holeScoreMongoDaoImpl.persist(hs);
			// recursively set subsequent scores
			recursiveUpdateSubsequentHoles(golfer, hs);
		} else {
			LOG.debug("updating the existing score for the hole");
			// use previous hole to set the values
			refreshOverviewValues(holeNumber, hs);
			hs.setHoleScore(holeScore);
			hs.setHoleToPar(toPar);
			hs.setHoleToHandicapPar(toHandicapPar);
			hs.setHoleCompetitionScore(competitionScore);
			updateOverviewValues(hs, hs);
			recursiveUpdateSubsequentHoles(golfer, hs);
		}
	}

	/**
	 * @param holeNumber
	 * @param hs
	 */
	private void refreshOverviewValues(int holeNumber, HoleScore hs) {
		LOG.debug("resetting the overview values for the hole");
		if (holeNumber > 1) {
			// set the update overview values, then set values for the hole
			// hole score
			hs.setOverviewScore(hs.getOverviewScore() - hs.getHoleScore());
			// toPar
			hs.setOverviewToPar(hs.getOverviewToPar() - hs.getHoleToPar());
			// toHandicapPar
			hs.setOverviewToHandicapPar(hs.getOverviewToHandicapPar()
					- hs.getHoleToHandicapPar());
			// competitionScore
			hs.setOverviewCompetitionScore(hs.getOverviewCompetitionScore()
					- hs.getHoleCompetitionScore());
		}
	}

	/**
	 * @param hs
	 * @throws PersistenceException
	 */
	private void recursiveUpdateSubsequentHoles(Golfer golfer, HoleScore hs)
			throws PersistenceException {
		LOG.debug("recursively updating overview values for hole {}",
				hs.getHoleNumber() + 1);
		HoleScore nextHs = holeScoreMongoDaoImpl
				.findGolferHoleScoreByCompetitionRound(golfer.getIdString(),
						hs.getCompetitionRoundId(), hs.getHoleNumber() + 1);
		// if the next hole is null then it hasn't been previously scored, so no
		// need to update (i.e. stop)
		if (nextHs != null) {
			updateOverviewValues(hs, nextHs);
			recursiveUpdateSubsequentHoles(golfer, nextHs);
		} else {
			LOG.debug("recursive update finished");
		}
	}

	/**
	 * @param overview
	 * @param holeScore
	 */
	private void updateOverviewValues(HoleScore overview, HoleScore holeScore) {
		holeScore.setOverviewScore(overview.getOverviewScore()
				+ holeScore.getHoleScore());
		holeScore.setOverviewToPar(overview.getOverviewToPar()
				+ holeScore.getHoleToPar());
		holeScore.setOverviewToHandicapPar(overview.getOverviewToHandicapPar()
				+ holeScore.getHoleToHandicapPar());
		holeScore.setOverviewCompetitionScore(overview
				.getOverviewCompetitionScore()
				+ holeScore.getHoleCompetitionScore());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.ILeaderboardService#hasLeaderboardBeenStarted
	 * (com.gffny.ldrbrd.common.model.impl.Golfer,
	 * com.gffny.ldrbrd.common.model.impl.CompetitionRound)
	 */
	@Override
	public boolean hasLeaderboardBeenStarted(Golfer golfer, CompetitionRound cr) {
		// check params
		if (golfer != null && cr != null) {
			try {
				return roundScoreMongoDaoImpl
						.findGolferRoundScoreByCompetitionRound(
								golfer.getIdString(), cr.getIdString()) != null;
			} catch (PersistenceException e) {
				LOG.error(e.getMessage());
			}
		}
		LOG.error("invalid parameters; golfer or competition round was null or there was an error in connecting to the mongo instance");
		return false;
	}
}
