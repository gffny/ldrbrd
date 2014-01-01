package com.gffny.ldrbrd.common.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.model.impl.HoleShot;
import com.gffny.ldrbrd.common.model.impl.Scorecard;

public interface IScorecardService {

	/**
	 * Create a scorecard for a round played and scored by the same golfer, on a course
	 * @param golferId
	 * @param courseId
	 * @param hashMap
	 * @param linkedList
	 * @return
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId, HashMap<String, String> hashMap, LinkedList<GolfClub> clubList);

	/**
	 * Create a scorecard for a round played by one golfer, scored by another golfer, on a course, for a specific handicap
	 * @param golfer
	 * @param course
	 * @param handicap
	 * @param hashMap
	 * @param clubList
	 * @return
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId, int handicap, HashMap<String, String> hashMap, LinkedList<GolfClub> clubList);

	/**
	 * Create a scorecard for a round played by one golfer, scored by another golfer, on a course
	 * @param golferId
	 * @param courseId
	 * @param hashMap
	 * @param clubList
	 * @return
	 */
	public Scorecard startGeneralScorecard(String golferId, String scoreKeeperId, String courseId, HashMap<String, String> hashMap, LinkedList<GolfClub> clubList);

	/**
	 * Create a scorecard for a round played by one golfer, scored by another golfer, on a course, for a specific handicap
	 * @param golferId
	 * @param courseId
	 * @param hashMap
	 * @param clubList
	 * @return
	 */
	public Scorecard startGeneralScorecard(String golferId, String scoreKeeperId, String courseId, int handicap, HashMap<String, String> hashMap, LinkedList<GolfClub> clubList);

	/**
	 * Create a scorecard for a competition round
	 * @param scorerId
	 * @param scoredId
	 * @param competitionId
	 * @param roundNumber
	 * @param linkedList
	 * @return
	 */
	public Scorecard startCompetitionScorecard(String golfer, String scoreKeeperId, String competitionId, int roundNumber, LinkedList<GolfClub> clubList);

	/**
	 * 
	 * @param scorecardHoleScoreMap
	 */
	public void scoreHoleScoreMap(String scorecardId, Map<Integer, Integer> scorecardHoleScoreMap);

	/**
	 * 
	 * @param scorecardId
	 * @param holeNumber
	 * @param holeScore
	 */
	public void scoreHole(String scorecardId, Integer holeNumber, Integer holeScore);

	/**
	 * 
	 * @param scorecardId
	 * @param scorecardScoreShotMap
	 */
	public void scoreShotMap(String scorecardId, Map<Integer, HoleShot> scorecardScoreShotMap);

	/**
	 * 
	 * @param scorecardId
	 * @param holeNumber
	 * @param shot
	 */
	public void scoreHoleShot(String scorecardId, Integer holeNumber, HoleShot shot);

	/**
	 * 
	 * @param scorecardId
	 * @param scoreKeeperId
	 * @return
	 */
	public boolean checkScorecardScoreKeeper(String scorecardId, String scoreKeeperId);

	/**
	 * 
	 * @param scorecardId
	 * @param scoreKeeperId
	 * @param competitionId
	 */
	public void submitScorecard(String scorecardId, String scoreKeeperId, String competitionId);

	/**
	 * 
	 * @param scorecardId
	 * @param scoreKeeperId
	 * @param competitionId
	 */
	public void signScorecard(String scorecardId, String scoreKeeperId, String competitionId);

}