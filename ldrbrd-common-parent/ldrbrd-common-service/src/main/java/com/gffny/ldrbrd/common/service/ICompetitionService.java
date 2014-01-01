package com.gffny.ldrbrd.common.service;

import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Course;

public interface ICompetitionService {

	/**
	 * 
	 * @param competitionId
	 * @param roundNumber
	 * @return
	 */
	public abstract CompetitionRound getCompetitionRound(String competitionId,
			Integer roundNumber);

	/**
	 * 
	 * @param competitionName
	 * @return
	 */
	public abstract Competition createCompetition(String competitionName);

	/**
	 * 
	 * @param competition
	 * @param roundDate
	 * @param roundNumber
	 * @return
	 */
	public abstract CompetitionRound createCompetitionRound(
			Competition competition, DateTime roundDate, Integer roundNumber,
			Course course);

}