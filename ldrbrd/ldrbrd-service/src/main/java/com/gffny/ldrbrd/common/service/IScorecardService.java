package com.gffny.ldrbrd.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.impl.Scorecard;

@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
public interface IScorecardService {

	/**
	 * @param golferId
	 * @param courseId
	 * @return
	 * @throws AuthorisationException
	 * @throws ServiceException
	 */
	@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
	public Scorecard startGeneralScorecard(String courseId) throws AuthorisationException,
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
			Map<String, String> hashMap, List<CommonIDEntity> clubList);

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
			Map<String, String> hashMap, List<CommonIDEntity> clubList);

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
			Map<String, String> hashMap, List<CommonIDEntity> clubList);

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
			int handicap, Map<String, String> hashMap, List<CommonIDEntity> clubList);

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
			String competitionId, int roundNumber, List<CommonIDEntity> clubList)
			throws AuthorisationException, ServiceException;

	/**
	 * @param scorecardHoleScoreMap
	 * @throws ServiceException
	 */
	public void scoreHoleScoreMap(int scorecardId, Map<Integer, Integer> scorecardHoleScoreMap)
			throws AuthorisationException, ServiceException;

	/**
	 * @param scorecardId
	 * @param holeNumber
	 * @param holeScore
	 * @throws ServiceException
	 */
	public void scoreHole(int scorecardId, int holeNumber, int holeScore, String holeId)
			throws AuthorisationException, ServiceException;

	/**
	 * @param scorecardId
	 * @param scorecardArray
	 */
	public void scoreHoleArray(String scorecardId, int[] scorecardArray)
			throws AuthorisationException, ServiceException;

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
	 * @throws ServiceException
	 */
	public boolean submitScorecard(String scorecardId, String scoreKeeperId, String competitionId)
			throws ServiceException;

	/**
	 * @param scorecardId
	 * @param scoreKeeperId
	 * @param competitionId
	 * @return
	 */
	public boolean signScorecard(String scorecardId) throws ServiceException;

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
			String competitionId, int roundNumber, List<CommonIDEntity> clubList,
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

	/**
	 * returns if the golfer (with golferId) has a scorecard that is currently active
	 * 
	 * @param golferId
	 * @return
	 */
	public boolean hasActiveScorecard(String golferId);

	/**
	 * @param golferId
	 * @return
	 * @throws ServiceException
	 */
	public Scorecard getActiveScorecard(String golferId) throws ServiceException;

}