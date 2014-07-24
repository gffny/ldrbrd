package com.gffny.ldrbrd.common.service;

import java.util.List;

import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionEntry;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.Golfer;

public interface ICompetitionService {

	/**
	 * @param competitionName
	 * @return
	 */
	public abstract Competition createCompetition(String competitionName) throws ServiceException;

	/**
	 * @param competition
	 * @param roundDate
	 * @param roundNumber
	 * @return
	 */
	public abstract CompetitionRound createCompetitionRound(Competition competition,
			DateTime roundDate, Integer roundNumber, Course course) throws ServiceException;

	/**
	 * @param competitionId
	 * @return
	 * @throws ServiceException
	 */
	public abstract Competition getCompetitionById(String competitionId) throws ServiceException;

	/**
	 * @param competitionName
	 * @return
	 */
	public abstract Competition getCompetitionByName(String competitionName)
			throws ServiceException;

	/**
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 * @throws ServiceException
	 */
	public abstract CompetitionRound getCompetitionRound(String competitionId, Integer roundNumber)
			throws ServiceException;

	/**
	 * Should return a list of competitions for which the golfer is registered and have not been
	 * completed (TODO heuristic required for competition completion)
	 * 
	 * @param golferId
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<CompetitionEntry> getCompetitionListForGolfer(String golferId)
			throws ServiceException;

	/**
	 * @param golfer
	 * @param competition
	 * @return
	 */
	public abstract CompetitionEntry getCompetitionRegistrationForGolfer(Golfer golfer,
			Competition competition) throws ServiceException;

	/**
	 * @param golfer
	 * @param competition
	 * @param handicap
	 */
	public abstract CompetitionEntry registerGolferForCompetitionWithHandicap(Golfer golfer,
			Competition competition, int handicap) throws ServiceException;

	/**
	 * @param golfer
	 * @param competition
	 */
	public abstract CompetitionEntry registerGolferForCompetition(Golfer golfer,
			Competition competition) throws ServiceException;

}