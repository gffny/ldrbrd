/**
 * 
 */
package com.gffny.ldrbrd.common.model.web;

import java.util.List;

import com.gffny.ldrbrd.common.model.impl.CompetitionEntry;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.model.nosql.Course;

/**
 * @author John D. Gaffney | gffny.com
 */
public class GolferDigestResponse {

	private Golfer golfer;
	private List<com.gffny.ldrbrd.common.model.nosql.Course> favouriteCourseList;
	private List<CompetitionEntry> competitionListForGolfer;
	private List<CompetitionRound> nonCompetitionRoundList;
	private List<Scorecard> lastXScorecardList;
	private Scorecard activeScorecard;

	/**
	 * @param profile
	 */
	public GolferDigestResponse(Golfer profile) {
		this.golfer = profile;
	}

	/**
	 * @return the golfer
	 */
	public Golfer getGolfer() {
		return golfer;
	}

	/**
	 * @param golfer
	 *            the golfer to set
	 */
	public void setGolfer(Golfer golfer) {
		this.golfer = golfer;
	}

	/**
	 * @return the favouriteCourseList
	 */
	public List<Course> getFavouriteCourseList() {
		return favouriteCourseList;
	}

	/**
	 * @param favouriteCourseList
	 *            the favouriteCourseList to set
	 */
	public void setFavouriteCourseList(List<Course> favouriteCourseList) {
		this.favouriteCourseList = favouriteCourseList;
	}

	/**
	 * @return the competitionListForGolfer
	 */
	public List<CompetitionEntry> getUpcomingCompetitionEntryList() {
		return competitionListForGolfer;
	}

	/**
	 * @param list
	 *            the competitionListForGolfer to set
	 */
	public void setUpcomingCompetitionEntryList(List<CompetitionEntry> list) {
		this.competitionListForGolfer = list;
	}

	/**
	 * @return the nonCompetitionRoundList
	 */
	public List<CompetitionRound> getUpcomingNonCompetitionRoundList() {
		return nonCompetitionRoundList;
	}

	/**
	 * @param arrayList
	 *            the nonCompetitionRoundList to set
	 */
	public void setUpcomingNonCompetitionRoundList(
			List<CompetitionRound> competitionRoundList) {
		this.nonCompetitionRoundList = competitionRoundList;
	}

	/**
	 * @return the lastXScorecardList
	 */
	public List<Scorecard> getLastXScorecardList() {
		return lastXScorecardList;
	}

	/**
	 * @param lastXScorecardList
	 *            the lastXScorecardList to set
	 */
	public void setLastXScorecardList(List<Scorecard> lastXScorecardList) {
		this.lastXScorecardList = lastXScorecardList;
	}

	/**
	 * /**
	 * 
	 * @return
	 */
	public Scorecard getActiveScorecard() {
		return this.activeScorecard;
	}

	/**
	 * @param activeScorecard
	 */
	public void setActiveScorecard(Scorecard scorecard) {
		this.activeScorecard = scorecard;

	}

}