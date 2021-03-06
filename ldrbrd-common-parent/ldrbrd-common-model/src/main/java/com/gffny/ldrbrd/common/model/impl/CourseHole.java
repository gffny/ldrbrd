/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author jdgaffney
 * 
 */
@NamedQueries({
		@NamedQuery(name = CourseHole.FIND_BY_COURSE_ID_AND_HOLE_NUMBER, query = "SELECT ch FROM CourseHole ch WHERE ch.course.id = :courseId and ch.holeNumber = :holeNumber"),
		@NamedQuery(name = CourseHole.FIND_BY_COURSE_ID, query = "SELECT ch FROM CourseHole ch WHERE ch.course.id = :courseId ORDER BY holeNumber ASC") })
@Entity
@Table(name = "t_hole")
public class CourseHole extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2698132847477453497L;

	public static final String FIND_BY_COURSE_ID_AND_HOLE_NUMBER = "findCourseHoleByCourseIdAndHoleNumber";

	public static final String FIND_BY_COURSE_ID = "findCourseHoleByCourseId";

	private String name;

	private int holeDistance;

	private String holeDescription;

	private int holeNumber;

	private String holeImageId;

	private Course course;

	// TODO ADD HOLE PAR!

	/**
	 * 
	 * @param name
	 * @param distance
	 * @param description
	 * @param holeNumber
	 * @param holeImageId
	 * @return
	 */
	public static CourseHole createCourseHole(Course course, String name,
			int distance, String description, int holeNumber, String holeImageId) {
		CourseHole courseHole = new CourseHole();
		courseHole.setCourse(course);
		courseHole.setName(name);
		courseHole.setHoleDistance(distance);
		courseHole.setHoleDescription(description);
		courseHole.setHoleNumber(holeNumber);
		courseHole.setHoleImageId(holeImageId);
		return courseHole;
	}

	/**
	 * @return the name
	 */
	@Column(name = "hl_nm")
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the holeDistance
	 */
	@Column(name = "hl_dstnc")
	public int getHoleDistance() {
		return holeDistance;
	}

	/**
	 * @param holeDistance
	 *            the holeDistance to set
	 */
	public void setHoleDistance(final int holeDistance) {
		this.holeDistance = holeDistance;
	}

	/**
	 * @return the holeDescription
	 */
	@Column(name = "hl_dsc")
	public String getHoleDescription() {
		return holeDescription;
	}

	/**
	 * @param holeDescription
	 *            the holeDescription to set
	 */
	public void setHoleDescription(final String holeDescription) {
		this.holeDescription = holeDescription;
	}

	/**
	 * @return the holeNumber
	 */
	@Column(name = "hl_nmbr")
	public int getHoleNumber() {
		return holeNumber;
	}

	/**
	 * @param holeNumber
	 *            the holeNumber to set
	 */
	public void setHoleNumber(final int holeNumber) {
		this.holeNumber = holeNumber;
	}

	/**
	 * @return the holeImageId
	 */
	@Column(name = "hl_img_ref")
	public String getHoleImageId() {
		return holeImageId;
	}

	/**
	 * @param holeImageId
	 *            the holeImageId to set
	 */
	public void setHoleImageId(String holeImageId) {
		this.holeImageId = holeImageId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the course
	 */
	@ManyToOne
	@JoinColumn(name = "crs_id", nullable = false)
	@ForeignKey(name = "id")
	@JsonIgnore
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

}
