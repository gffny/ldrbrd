/**
 * 
 */
package com.gffny.ldrbrd.utils;

//import com.gffny.leaderboard.model.ICompetition;
//import com.gffny.leaderboard.model.ICompetition.ICompetitionRound;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class CompetitionFunction {

	/**
	 * 
	 * @param competition
	 * @param round
	 * @return
	 */
//	public static boolean checkCompetitionRoundType(ICompetition competition,
//			ICompetitionRound round) {
//		// TODO implement in meaningful way
//		/*
//		 * check if the competition type (individual vs team) is compatible with
//		 * the round. this function will be handy for checking in the scorer and
//		 * the schedulers
//		 */
//		return true;
//	}

	/**
	 * @param strokes
	 * @param scoreArray
	 * @return
	 */
	public static boolean strokesMatchesScoreArray(String strokes,
			String[] scoreArray) {
		int score = Integer.parseInt(strokes);
		int cumulativeHoleScores = 0;
		for (int i = 0; i < scoreArray.length; i++) {
			cumulativeHoleScores += Integer.parseInt(scoreArray[i]);
		}
		return score == cumulativeHoleScores;
	}

}
