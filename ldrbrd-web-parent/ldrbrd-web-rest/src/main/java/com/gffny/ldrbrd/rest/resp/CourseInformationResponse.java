/**
 * 
 */
package com.gffny.ldrbrd.rest.resp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.CourseHole;
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

	@JsonIgnore
	Course course;

	/**
	 * maps course id to a map of hole number to course hole
	 */
	@JsonIgnore
	Map<String, Map<Integer, CourseHole>> courseHoleDetailMap = new HashMap<String, Map<Integer, CourseHole>>();

	/**
	 * @return the courseList
	 */
	public List<Course> getCourseList() {
		return courseList;
	}

	/**
	 * @param courseList
	 *            the courseList to set
	 */
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	/**
	 * 
	 * @return
	 */
	public Course getCourse() {
		return this.course;
	}

	/**
	 * 
	 * @param course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	public Map<String, Map<Integer, CourseHole>> getCourseHoleMap() {
		return this.courseHoleDetailMap;
	}

	/**
	 * 
	 * @param id
	 * @param holeListByCourseId
	 */
	public void addCourseHoleMap(String id,
			Map<Integer, CourseHole> courseHoleMap) {
		if (id != null && courseHoleMap != null) {
			courseHoleDetailMap.put(id, courseHoleMap);
		}
	}

}
