/**
 * 
 */
package com.gffny.ldrbrd.common.model.enums;

/**
 * @author jdgaffney
 * 
 */
public enum ClubCategory {
	DRIVER("Driver", ClubType.DRIVER_ARRAY), WOOD("Wood", ClubType.WOOD_ARRAY), HYBRID(
			"Hybrid", ClubType.HYBRID_ARRAY), IRON("Iron", ClubType.IRON_ARRAY), WEDGE(
			"Wedge", ClubType.WEDGE_ARRAY), PUTTER("Putter",
			ClubType.PUTTER_ARRAY);

	public static final ClubCategory DEFAULT = DRIVER;

	private String categoryName;
	private ClubType[] associatedClubTypeArray;

	ClubCategory(final String name, final ClubType[] associatedClubTypeArray) {
		this.associatedClubTypeArray = associatedClubTypeArray;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @return the associatedClubTypeArray
	 */
	public ClubType[] getAssociatedClubTypeArray() {
		return associatedClubTypeArray;
	}
}
