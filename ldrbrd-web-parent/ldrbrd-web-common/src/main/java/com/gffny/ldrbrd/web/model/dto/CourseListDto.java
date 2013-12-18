/**
 * 
 */
package com.gffny.ldrbrd.web.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.gffny.ldrbrd.common.model.impl.Course;

//import com.gffny.leaderboard.model.IGolfCourse;

/**
 * @author John Gaffney (john@gffny.com) May 1, 2013
 * 
 */
public class CourseListDto extends BaseDto {

	private List<Course> golfCourseList = null;

	/**
	 * 
	 * @param golfCourseList
	 */
	public CourseListDto(List<Course> golfCourseList) {
		if (golfCourseList != null) {
			setCourseList(golfCourseList);
		} else {
			this.golfCourseList = new ArrayList<Course>();
		}
	}

	/**
	 * @return the golfCourseList
	 */
	public List<Course> getCourseList() {
		return golfCourseList;
	}

	/**
	 * @param golfCourseList
	 *            the golfCourseList to set
	 */
	public void setCourseList(List<Course> golfCourseList) {
		this.golfCourseList = golfCourseList;
	}
}