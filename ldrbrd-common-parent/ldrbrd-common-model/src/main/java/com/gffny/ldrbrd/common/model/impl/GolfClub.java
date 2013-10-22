/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;
import com.gffny.ldrbrd.common.model.enums.ClubCategory;
import com.gffny.ldrbrd.common.model.enums.ClubType;
import com.gffny.ldrbrd.common.model.enums.Manufacturer;

/**
 * @author John Gaffney (john@gffny.com) Apr 16, 2013
 * 
 * 
 */
@Entity
// @Table(name = "t_golfclub", schema = "ldrbrd", uniqueConstraints =
// @UniqueConstraint(name = "nameManufacturerTypeConstraint", columnNames = {
// "clb_typ", "clb_mnfctrr", "clb_nm" }))
@Table(name = "t_golfclub", schema = "ldrbrd")
public class GolfClub extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1022225457106091586L;

	private Manufacturer manufacturer;

	private ClubType clubType;

	private int clubLoft;

	private ClubCategory clubCategory;

	private String clubName;

	// this indicates a club that is attributed to each golfer upon creating a
	// profile
	private boolean isDefault;

	/**
	 * @return the manufacturer
	 */
	@Column(name = "clb_mnfctrr")
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = getDefaultNotNullValue(manufacturer,
				Manufacturer.DEFAULT);
	}

	/**
	 * @return the clubType
	 */
	@Column(name = "clb_nm")
	public String getClubName() {
		return this.clubName;
	}

	/**
	 * @param clubType
	 *            the clubType to set
	 */
	public void setClubName(String clubName) {
		this.clubName = getDefaultNotNullValue(clubName, "");
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "clb_typ")
	public ClubType getClubType() {
		return this.clubType;
	}

	//
	// /**
	// * @return the clubType
	// */
	// public String getClubTypeValue() {
	// return clubType.toString();
	// }

	/**
	 * @param clubType
	 *            the clubType to set
	 */
	@Column(name = "clb_typ")
	public void setClubType(ClubType clubType) {
		this.clubType = getDefaultNotNullValue(clubType, ClubType.DEFAULT);
	}

	/**
	 * @return the clubCategory
	 */
	@Column(name = "clb_ctgry")
	public ClubCategory getClubCategory() {
		return clubCategory;
	}

	/**
	 * @param clubCategory
	 *            the clubCategory to set
	 */
	public void setClubCategory(ClubCategory clubCategory) {
		this.clubCategory = getDefaultNotNullValue(clubCategory,
				ClubCategory.DEFAULT);
	}

	/**
	 * @return the clubLoft
	 */
	@Column(name = "clb_lft")
	public int getClubLoft() {
		return clubLoft;
	}

	/**
	 * @param clubLoft
	 *            the clubLoft to set
	 */
	public void setClubLoft(Integer clubLoft) {
		this.clubLoft = getDefaultNotNullValue(clubLoft, 0).intValue();
	}

	/**
	 * @return the isDefault
	 */
	@Column(name = "is_dflt", columnDefinition = "BIT", length = 1)
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault
	 *            the isDefault to set
	 */
	public void setDefault(Boolean isDefault) {
		this.isDefault = getDefaultNotNullValue(isDefault, false)
				.booleanValue();
	}
}
