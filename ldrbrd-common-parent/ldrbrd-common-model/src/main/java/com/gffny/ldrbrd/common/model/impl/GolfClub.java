/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;
import com.gffny.ldrbrd.common.model.enums.ClubType;
import com.gffny.ldrbrd.common.model.enums.Manufacturer;

/**
 * @author John Gaffney (john@gffny.com) Apr 16, 2013
 * 
 */
public class GolfClub extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1022225457106091586L;
	private Manufacturer manufacturer;
	private ClubType clubType;
	private String clubCategory;
	private int clubLoft;
	private String clubName;

	// this indicates a club that is attributed to each golfer upon creating a
	// profile
	private boolean isDefault;

	/**
	 * @return the manufacturer
	 */
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the clubType
	 */
	public String getClubName() {
		return this.clubName;
	}

	/**
	 * @param clubType
	 *            the clubType to set
	 */
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	/**
	 * 
	 * @return
	 */
	public ClubType getClubType() {
		return this.clubType;
	}

	/**
	 * @return the clubType
	 */
	public String getClubTypeValue() {
		return clubType.toString();
	}

	/**
	 * @param clubType
	 *            the clubType to set
	 */
	public void setClubType(ClubType clubType) {
		this.clubType = clubType;
	}

	/**
	 * @return the clubCategory
	 */
	public String getClubCategory() {
		return clubCategory;
	}

	/**
	 * @param clubCategory
	 *            the clubCategory to set
	 */
	public void setClubCategory(String clubCategory) {
		this.clubCategory = clubCategory;
	}

	/**
	 * @return the clubLoft
	 */
	public int getClubLoft() {
		return clubLoft;
	}

	/**
	 * @param clubLoft
	 *            the clubLoft to set
	 */
	public void setClubLoft(Integer clubLoft) {
		this.clubLoft = null != clubLoft ? clubLoft.intValue() : 0;
	}

	/**
	 * @return the isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault
	 *            the isDefault to set
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
}
