/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author jdgaffney
 * 
 */
@Entity
@Table(name = "t_hole")
public class CourseHole extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2698132847477453497L;

	private String name;

	private int holeDistance;

	private String holeDescription;

	private int holeNumber;

	private String holeImageId;

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

}
