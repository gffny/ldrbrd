/**
 * 
 */
package com.gffny.ldrbrd.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.nosql.RoundScore;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public class PublishScorecardUtils {

	/** */
	private static final Logger LOG = LoggerFactory
			.getLogger(PublishScorecardUtils.class);

	/**
	 * @param holeNumber
	 * @param holeScore
	 * @param toPar
	 * @param toHandicapPar
	 * @param competitionScore
	 */
	public static void setScorecardHoleScore(RoundScore roundScore,
			int holeNumber, int holeScore, int toPar, int toHandicapPar,
			int competitionScore) {
		// check params
		if (roundScore != null) {
			LOG.debug(
					"scoring hole {} with hole score {}, toPar {}, toHandicapPar {}, and competitionScore {}",
					holeNumber, holeScore, toPar, toHandicapPar,
					competitionScore);
			switch (holeNumber) {
			case 1:
				roundScore.setHole01ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 2:
				roundScore.setHole02ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 3:
				roundScore.setHole03ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 4:
				roundScore.setHole04ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 5:
				roundScore.setHole05ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 6:
				roundScore.setHole06ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 7:
				roundScore.setHole07ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 8:
				roundScore.setHole08ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 9:
				roundScore.setHole08ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 10:
				roundScore.setHole10ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 11:
				roundScore.setHole11ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 12:
				roundScore.setHole12ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 13:
				roundScore.setHole13ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 14:
				roundScore.setHole14ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 15:
				roundScore.setHole15ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 16:
				roundScore.setHole16ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 17:
				roundScore.setHole17ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			case 18:
				roundScore.setHole18ScoreDetails(holeScore, toPar,
						toHandicapPar, competitionScore);
				break;
			}
			updateOverviewScore(roundScore);
		}
	}

	/**
	 * @param rs
	 */
	public static void updateOverviewScore(RoundScore rs) {
		// check params
		if (rs != null) {
			LOG.debug("updating the round score");
			rs.setRoundScore(rs.getHole01Score() + rs.getHole02Score()
					+ rs.getHole03Score() + rs.getHole04Score()
					+ rs.getHole05Score() + rs.getHole06Score()
					+ rs.getHole07Score() + rs.getHole08Score()
					+ rs.getHole09Score() + rs.getHole10Score()
					+ rs.getHole11Score() + rs.getHole12Score()
					+ rs.getHole13Score() + rs.getHole14Score()
					+ rs.getHole15Score() + rs.getHole16Score()
					+ rs.getHole17Score() + rs.getHole18Score());
			rs.setOverviewScore(rs.getInitialScore() + rs.getRoundScore());

			rs.setRoundToPar(rs.getHole01ToPar() + rs.getHole02ToPar()
					+ rs.getHole03ToPar() + rs.getHole04ToPar()
					+ rs.getHole05ToPar() + rs.getHole06ToPar()
					+ rs.getHole07ToPar() + rs.getHole08ToPar()
					+ rs.getHole09ToPar() + rs.getHole10ToPar()
					+ rs.getHole11ToPar() + rs.getHole12ToPar()
					+ rs.getHole13ToPar() + rs.getHole14ToPar()
					+ rs.getHole15ToPar() + rs.getHole16ToPar()
					+ rs.getHole17ToPar() + rs.getHole18ToPar());
			rs.setOverviewToPar(rs.getInitialToPar() + rs.getRoundToPar());

			rs.setRoundToHandicapPar(rs.getHole01ToHandicapPar()
					+ rs.getHole02ToHandicapPar() + rs.getHole03ToHandicapPar()
					+ rs.getHole04ToHandicapPar() + rs.getHole05ToHandicapPar()
					+ rs.getHole06ToHandicapPar() + rs.getHole07ToHandicapPar()
					+ rs.getHole08ToHandicapPar() + rs.getHole09ToHandicapPar()
					+ rs.getHole10ToHandicapPar() + rs.getHole11ToHandicapPar()
					+ rs.getHole12ToHandicapPar() + rs.getHole13ToHandicapPar()
					+ rs.getHole14ToHandicapPar() + rs.getHole15ToHandicapPar()
					+ rs.getHole16ToHandicapPar() + rs.getHole17ToHandicapPar()
					+ rs.getHole18ToHandicapPar());
			rs.setOverviewToHandicapPar(rs.getInitialToHandicapPar()
					+ rs.getRoundToHandicapPar());

			rs.setRoundCompetitionScore(rs.getHole01CompetitionScore()
					+ rs.getHole02CompetitionScore()
					+ rs.getHole03CompetitionScore()
					+ rs.getHole04CompetitionScore()
					+ rs.getHole05CompetitionScore()
					+ rs.getHole06CompetitionScore()
					+ rs.getHole07CompetitionScore()
					+ rs.getHole08CompetitionScore()
					+ rs.getHole09CompetitionScore()
					+ rs.getHole10CompetitionScore()
					+ rs.getHole11CompetitionScore()
					+ rs.getHole12CompetitionScore()
					+ rs.getHole13CompetitionScore()
					+ rs.getHole14CompetitionScore()
					+ rs.getHole15CompetitionScore()
					+ rs.getHole16CompetitionScore()
					+ rs.getHole17CompetitionScore()
					+ rs.getHole18CompetitionScore());
			rs.setOverviewCompetitionScore(rs.getInitialCompetitionScore()
					+ rs.getRoundCompetitionScore());
		}
	}

	/**
	 * @param rs
	 */
	public static void initialiseRoundScoreValues(RoundScore rs) {
		rs.setInitialScore(Constant.INITIAL_SCORE);
		rs.setInitialToHandicapPar(Constant.INITIAL_SCORE);
		rs.setInitialToPar(Constant.INITIAL_SCORE);
		rs.setInitialCompetitionScore(Constant.INITIAL_SCORE);
		rs.setRoundScore(Constant.INITIAL_SCORE);
		rs.setRoundToPar(Constant.INITIAL_SCORE);
		rs.setRoundToHandicapPar(Constant.INITIAL_SCORE);
		rs.setRoundCompetitionScore(Constant.INITIAL_SCORE);
	}

	/**
	 * @param newRs
	 * @param prevRs
	 */
	public static void initialiseRoundScoreValues(RoundScore newRs,
			RoundScore prevRs) {
		if (prevRs != null) {
			LOG.debug(
					"setting values for previous round as score {}, toPar {}, toHandicapPar {}, competitionScore {}",
					prevRs.getOverviewScore(), prevRs.getOverviewToPar(),
					prevRs.getOverviewToHandicapPar(),
					prevRs.getOverviewCompetitionScore());
			newRs.setInitialScore(prevRs.getOverviewScore());
			newRs.setInitialToHandicapPar(prevRs.getOverviewToHandicapPar());
			newRs.setInitialToPar(prevRs.getOverviewToPar());
			newRs.setInitialCompetitionScore(prevRs
					.getInitialCompetitionScore());
			newRs.setRoundScore(Constant.INITIAL_SCORE);
			newRs.setRoundToPar(Constant.INITIAL_SCORE);
			newRs.setRoundToHandicapPar(Constant.INITIAL_SCORE);
			newRs.setRoundCompetitionScore(Constant.INITIAL_SCORE);
		}
	}
}
