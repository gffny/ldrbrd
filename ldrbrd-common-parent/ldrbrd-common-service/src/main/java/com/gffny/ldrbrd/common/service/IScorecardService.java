package com.gffny.ldrbrd.common.service;

import java.util.HashMap;
import java.util.LinkedList;

import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.model.impl.Scorecard;

public interface IScorecardService {

	/**
	 * 
	 * @param golfer
	 * @param course
	 * @param handicap
	 * @param hashMap
	 * @param linkedList
	 * @return
	 */
	public abstract Scorecard startGeneralScorecard(String golferId,
			String courseId, int handicap, HashMap<String, String> hashMap,
			LinkedList<GolfClub> linkedList);

	/**
	 * 
	 * @param golferId
	 * @param courseId
	 * @param hashMap
	 * @param linkedList
	 * @return
	 */
	public abstract Scorecard startGeneralScorecard(String golferId,
			String courseId, HashMap<String, String> hashMap,
			LinkedList<GolfClub> linkedList);

	/**
	 * 
	 * @param scorerId
	 * @param scoredId
	 * @param competitionId
	 * @param roundNumber
	 * @param linkedList
	 * @return
	 */
	public abstract Scorecard startCompetitionScorecard(String scorerId,
			String scoredId, String competitionId, Integer roundNumber,
			LinkedList<GolfClub> linkedList);

}