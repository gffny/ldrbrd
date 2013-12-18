package com.gffny.ldrbrd.common.service;

import java.util.List;

import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Club;
import com.gffny.ldrbrd.common.model.impl.Course;

public interface ICourseClubService {

	/**
	 * 
	 * @return
	 */
	public abstract Course createCourse(String courseName, Club club,
			TeeColour teeColour, Double slopeIndex, Integer par,
			String courseImageReference);

	/**
	 * 
	 * @param clubName
	 * @return
	 */
	public abstract Club createClub(String clubName);

	/**
	 * 
	 * @return
	 */
	public abstract List<Club> getClubList();

	/**
	 * 
	 * @param courseName
	 * @param teeColour
	 * @return
	 */
	public abstract Course getCourseByNameAndTeeColour(String courseName,
			TeeColour teeColour);

}