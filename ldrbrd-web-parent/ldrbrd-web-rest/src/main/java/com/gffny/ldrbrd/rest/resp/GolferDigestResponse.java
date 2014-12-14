package com.gffny.ldrbrd.rest.resp;

import java.util.List;

import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.model.mapping.CompetitionRegistration;
import com.gffny.ldrbrd.web.model.JSONable;

/**
 * 
 * @author jdgaffney
 * 
 */
public class GolferDigestResponse implements JSONable {

	private GolferProfile golferProfile;
	private List<Course> favouriteCourseList;
	private List<CompetitionRegistration> competitionListForGolfer;
	private List<CompetitionRound> nonCompetitionRoundList;
	private List<Scorecard> lastXScorecardList;

	/**
	 * 
	 * @param profile
	 */
	public GolferDigestResponse(GolferProfile profile) {
		this.golferProfile = profile;
	}

	/**
	 * @return the golferProfile
	 */
	public GolferProfile getGolferProfile() {
		return golferProfile;
	}

	/**
	 * @param golferProfile
	 *            the golferProfile to set
	 */
	public void setGolferProfile(GolferProfile golferProfile) {
		this.golferProfile = golferProfile;
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
	public List<CompetitionRegistration> getUpcomingCompetitionRegistrationList() {
		return competitionListForGolfer;
	}

	/**
	 * @param list
	 *            the competitionListForGolfer to set
	 */
	public void setUpcomingCompetitionRegistrationList(
			List<CompetitionRegistration> list) {
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

}
