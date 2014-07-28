package com.gffny.ldrbrd.common.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.gffny.ldrbrd.common.exception.AuthorizationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.model.impl.Scorecard;

public interface IScorecardService {

	/**
	 * @param golferId
	 * @param courseId
	 * @return
	 * @throws AuthorizationException
	 * @throws ServiceException
	 */
	public Scorecard startGeneralScorecard(String courseId) throws AuthorizationException,
			ServiceException;

	/**
	 * Create a scorecard for a round played and scored by the same golfer, on a course
	 * 
	 * @param golferId
	 * @param courseId
	 * @param hashMap
	 * @param linkedList
	 * @return
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId,
			HashMap<String, String> hashMap, LinkedList<GolfClub> clubList);

	/**
	 * Create a scorecard for a round played by one golfer, scored by another golfer, on a course,
	 * for a specific handicap
	 * 
	 * @param golfer
	 * @param course
	 * @param handicap
	 * @param hashMap
	 * @param clubList
	 * @return
	 */
	public Scorecard startGeneralScorecard(String golferId, String courseId, int handicap,
			HashMap<String, String> hashMap, LinkedList<GolfClub> clubList);

	/**
	 * Create a scorecard for a round played by one golfer, scored by another golfer, on a course
	 * 
	 * @param golferId
	 * @param courseId
	 * @param hashMap
	 * @param clubList
	 * @return
	 */
	public Scorecard startGeneralScorecard(String golferId, String scoreKeeperId, String courseId,
			HashMap<String, String> hashMap, LinkedList<GolfClub> clubList);

	/**
	 * Create a scorecard for a round played by one golfer, scored by another golfer, on a course,
	 * for a specific handicap
	 * 
	 * @param golferId
	 * @param courseId
	 * @param hashMap
	 * @param clubList
	 * @return
	 */
	public Scorecard startGeneralScorecard(String golferId, String scoreKeeperId, String courseId,
			int handicap, HashMap<String, String> hashMap, LinkedList<GolfClub> clubList);

	/**
	 * Create a scorecard for a competition round
	 * 
	 * @param scorerId
	 * @param scoredId
	 * @param competitionId
	 * @param roundNumber
	 * @param linkedList
	 * @return
	 * @throws ServiceException
	 */
	public Scorecard startCompetitionScorecard(String golfer, String scoreKeeperId,
			String competitionId, int roundNumber, LinkedList<GolfClub> clubList)
			throws ServiceException;

	/**
	 * @param scorecardHoleScoreMap
	 * @throws ServiceException
	 */
	public void scoreHoleScoreMap(int scorecardId, Map<Integer, Integer> scorecardHoleScoreMap)
			throws ServiceException;

	/**
	 * @param scorecardId
	 * @param holeNumber
	 * @param holeScore
	 * @throws ServiceException
	 */
	public void scoreHole(int scorecardId, int holeNumber, int holeScore) throws ServiceException;

	/**
	 * @param scorecardId
	 * @param scorecardScoreShotMap
	 */
	// public void scoreShotMap(String scorecardId, Map<Integer, HoleShot> scorecardScoreShotMap);

	/**
	 * @param scorecardId
	 * @param holeNumber
	 * @param shot
	 */
	// public void scoreHoleShot(String scorecardId, Integer holeNumber, HoleShot shot);

	/**
	 * @param scorecardId
	 * @param scoreKeeperId
	 * @return
	 */
	public boolean checkScorecardScoreKeeper(String scorecardId, String scoreKeeperId);

	/**
	 * @param scorecardId
	 * @param scoreKeeperId
	 * @param competitionId
	 */
	public void submitScorecard(String scorecardId, String scoreKeeperId, String competitionId);

	/**
	 * @param scorecardId
	 * @param scoreKeeperId
	 * @param competitionId
	 */
	public void signScorecard(String scorecardId, String scoreKeeperId, String competitionId);

	/**
	 * @param golferId
	 * @param scoreKeeperId
	 * @param competitionId
	 * @param roundNumber
	 * @param clubList
	 * @param competitionHandicap
	 * @return
	 * @throws ServiceException
	 */
	public abstract Scorecard startCompetitionScorecard(String golferId, String scoreKeeperId,
			String competitionId, int roundNumber, LinkedList<GolfClub> clubList,
			int competitionHandicap) throws ServiceException;

	/**
	 * @param profile
	 * @param i
	 * @return
	 */
	public abstract List<Scorecard> getLastXScorecards(String golferId, int xScorecards)
			throws ServiceException;

	/**
	 * @param profile
	 * @return
	 */
	public abstract List<Scorecard> getLastXScorecards(String golferId) throws ServiceException;

}