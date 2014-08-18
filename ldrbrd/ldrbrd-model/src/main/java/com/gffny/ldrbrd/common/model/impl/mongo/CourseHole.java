/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl.mongo;

import org.mongodb.morphia.annotations.Entity;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 */
@Entity
public class CourseHole extends CommonUUIDEntity {

	/** */
	private static final long serialVersionUID = -2698132847477453497L;

	/** */
	private int holeDistance;

	/** */
	private String holeDescription;

	/** */
	private int holeNumber;

	/** */
	private String holeImageId;

	/** */
	private Course course;

	/** */
	private int holePar;

	/**
	 * @param name
	 * @param distance
	 * @param description
	 * @param holeNumber
	 * @param holeImageId
	 * @return
	 */
	public static CourseHole createCourseHole(Course course, String name, int par, int distance,
			String description, int holeNumber, String holeImageId) {
		CourseHole courseHole = new CourseHole();
		courseHole.setCourse(course);
		courseHole.setName(name);
		courseHole.setHoleDistance(distance);
		courseHole.setHoleDescription(description);
		courseHole.setHoleNumber(holeNumber);
		courseHole.setHoleImageId(holeImageId);
		courseHole.setPar(par);
		return courseHole;
	}

	/**
	 * @return the holeDistance
	 */
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

	/**
	 * @return
	 */
	public int getPar() {
		return this.holePar;
	}

	/**
	 * @param par
	 */
	public void setPar(int holePar) {
		this.holePar = holePar;
	}
}
