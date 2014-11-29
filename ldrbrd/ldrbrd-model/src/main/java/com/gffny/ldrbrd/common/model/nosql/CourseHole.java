/**
 * 
 */
package com.gffny.ldrbrd.common.model.nosql;

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
	private int holePar;

	/** */
	private int holeIndex;

	/**
	 * @param name
	 * @param distance
	 * @param description
	 * @param holeNumber
	 * @param holeImageId
	 * @return
	 */
	public static CourseHole createCourseHole(String name, int par,
			int distance, int index, String description, int holeNumber,
			String holeImageId) {
		CourseHole courseHole = new CourseHole();
		courseHole.setName(name);
		courseHole.setHoleDistance(distance);
		courseHole.setHoleDescription(description);
		courseHole.setHoleNumber(holeNumber);
		courseHole.setHoleImageId(holeImageId);
		courseHole.setPar(par);
		courseHole.setIndex(index);
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

	/**
	 * @return
	 */
	public int getIndex() {
		return this.holeIndex;
	}

	/**
	 * @param par
	 */
	public void setIndex(int index) {
		this.holeIndex = index;
	}
}
