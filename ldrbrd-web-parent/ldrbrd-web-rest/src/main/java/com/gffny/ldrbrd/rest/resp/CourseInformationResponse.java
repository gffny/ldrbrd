/**
 * 
 */
package com.gffny.ldrbrd.rest.resp;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.web.model.JSONable;

/**
 * @author jdgaffney
 *
 */
public class CourseInformationResponse implements JSONable {

	/**
	 * 
	 */
	@JsonIgnore
	List<Course> courseList = new ArrayList<Course>();

	/**
	 * @return the courseList
	 */
	public List<Course> getCourseList() {
		return courseList;
	}

	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
}
