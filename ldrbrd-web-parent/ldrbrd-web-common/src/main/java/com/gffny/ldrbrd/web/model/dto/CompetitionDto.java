/**
 * 
 */
package com.gffny.ldrbrd.web.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.CompetitionType;

/**
 * @author John Gaffney (john@gffny.com) Apr 24, 2013
 * 
 */
public class CompetitionDto extends BaseDto {

	private int competitionId;
	private String competitionName;
	private boolean newEntity;
	private CompetitionType competitionScoringSystem;
	private String competitionVisibility;

	private Map<Integer, CompetitionRound> roundNumberMap;
	private Map<Date, CompetitionRound> roundDateMap;

	// private static final int DEFAULT_ROUND_NUMBER = 4;

	/**
	 * 
	 * 
	 * @see com.gffny.leaderboard.model.ISQLEntity#setId(java.lang.Integer)
	 */
	public void setId(Integer id) {
		this.competitionId = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#getName()
	 */
	public String getName() {
		return competitionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.competitionName = name;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#getId()
	 */
	public Object getId() {
		return Integer.valueOf(competitionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#setNew(boolean)
	 */
	public void setNew(boolean value) {
		this.newEntity = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.IEntity#isNew()
	 */
	public boolean isNew() {
		return newEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionId()
	 */
	public int getCompetitionId() {
		return this.competitionId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionIdAsString()
	 */
	public String getCompetitionIdAsString() {
		return String.valueOf(competitionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionName()
	 */
	public String getCompetitionName() {
		return competitionName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetition#getCompetitionScoringSystem()
	 */
	public CompetitionType getCompetitionScoringSystem() {
		return competitionScoringSystem;
	}

	/**
	 * @param competitionScoringSystem
	 *            the competitionScoringSystem to set
	 */
	public void setCompetitionScoringSystem(
			CompetitionType competitionScoringSystem) {
		this.competitionScoringSystem = competitionScoringSystem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionVisibility()
	 */
	public String getCompetitionVisibility() {
		return competitionVisibility;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetition#getRoundByDate(java.util.Date)
	 */
	public CompetitionRound getRoundByDate(Date roundDate) {
		return roundDateMap.get(roundDate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getRoundByNumber(int)
	 */
	public CompetitionRound getRoundByNumber(int roundNumber) {
		return roundNumberMap.get(Integer.valueOf(roundNumber));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionRoundList()
	 */
	public List<CompetitionRound> getCompetitionRoundList() {
		return new ArrayList<CompetitionRound>(roundNumberMap.values());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetition#addCompetitionRound(com.gffny
	 * .leaderboard.model.ICompetition.ICompetitionRound)
	 */
	public void addCompetitionRound(CompetitionRound round) {
		roundNumberMap.put(round.getRoundNumber(), round);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition#isIndividualCompetition()
	 */
	public boolean isIndividualCompetition() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.leaderboard.model.ICompetition#addCompetitionRoundList(java
	 * .util.List)
	 */
	public void addCompetitionRoundList(
			List<CompetitionRound> competitionRoundList) {
		for (CompetitionRound cr : competitionRoundList) {
			roundNumberMap.put(cr.getRoundNumber(), cr);
		}
	}
}
