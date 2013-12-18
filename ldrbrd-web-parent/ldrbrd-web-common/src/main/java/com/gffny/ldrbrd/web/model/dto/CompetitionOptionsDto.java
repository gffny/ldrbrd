/**
 * 
 */
package com.gffny.ldrbrd.web.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.gffny.ldrbrd.common.model.impl.CompetitionType;

//mport com.gffny.leaderboard.model.ICompetitionType;

/**
 * @author John Gaffney (john@gffny.com) Apr 22, 2013
 * 
 */
public class CompetitionOptionsDto extends BaseDto {

	private List<CompetitionType> competitionTypeList = new ArrayList<CompetitionType>();
	private List<String> competitionVisibilityChoiceList = new ArrayList<String>();

	/**
	 * 
	 * @param competitionTypeList
	 * @param competitionVisibilityChoiceList
	 */
	public CompetitionOptionsDto(List<CompetitionType> competitionTypeList,
			List<String> competitionVisibilityChoiceList) {
		this.competitionTypeList = competitionTypeList;
		this.competitionVisibilityChoiceList = competitionVisibilityChoiceList;
	}

	/**
	 * @return the competitionTypeList
	 */
	public List<CompetitionType> getCompetitionTypeList() {
		return competitionTypeList;
	}

	/**
	 * @return the competitionVisibilityChoiceList
	 */
	public List<String> getCompetitionVisibilityChoiceList() {
		return competitionVisibilityChoiceList;
	}
}
