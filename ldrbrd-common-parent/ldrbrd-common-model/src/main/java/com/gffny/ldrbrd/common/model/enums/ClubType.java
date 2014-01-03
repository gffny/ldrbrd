/**
 * 
 */
package com.gffny.ldrbrd.common.model.enums;

/**
 * @author jdgaffney
 * 
 */
public enum ClubType {
	DRIVER, TWO_WOOD, THREE_WOOD, FOUR_WOOD, FIVE_WOOD, SIX_WOOD, SEVEN_WOOD, ONE_HYBRID, TWO_HYBRID, THREE_HYBRID, FOUR_HYBRID, FIVE_HYBRID, SIX_HYBRID, SEVEN_HYBRID, ONE_IRON, TWO_IRON, THREE_IRON, FOUR_IRON, FIVE_IRON, SIX_IRON, SEVEN_IRON, EIGHT_IRON, NINE_IRON, PITCHING_WEDGE, APPROACH_WEDGE, SAND_WEDGE, PUTTER;

	/**
	 * 
	 */
	public static ClubType DEFAULT = DRIVER;

	/**
	 * 
	 */
	public static ClubType[] DRIVER_ARRAY = { DRIVER };

	/**
	 * 
	 */
	public static ClubType[] WOOD_ARRAY = { TWO_WOOD, THREE_WOOD, FOUR_WOOD,
			FIVE_WOOD };

	/**
	 * 
	 */
	public static ClubType[] HYBRID_ARRAY = { ONE_HYBRID, TWO_HYBRID,
			THREE_HYBRID, FOUR_HYBRID, FIVE_HYBRID, SIX_HYBRID, SEVEN_HYBRID };

	/**
	 * 
	 */
	public static ClubType[] IRON_ARRAY = { ONE_IRON, TWO_IRON, THREE_IRON,
			FOUR_IRON, FIVE_IRON, SIX_IRON, SEVEN_IRON, EIGHT_IRON, NINE_IRON };

	/**
	 * 
	 */
	public static ClubType[] WEDGE_ARRAY = { PITCHING_WEDGE, APPROACH_WEDGE,
			SAND_WEDGE };

	/**
	 * 
	 */
	public static ClubType[] PUTTER_ARRAY = { PUTTER };

	/**
	 * 
	 * @return
	 */
	public static int getDefaultClubQuantity() {
		return DRIVER_ARRAY.length + WOOD_ARRAY.length + HYBRID_ARRAY.length
				+ IRON_ARRAY.length + WEDGE_ARRAY.length + PUTTER_ARRAY.length;
	}
}