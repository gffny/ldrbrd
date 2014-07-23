/**
 * 
 */
package com.gffny.ldrbrd.common.model;

/**
 * @author John D. Gaffney | gffny.com
 */
public interface Constant {

	public static final String DB_ID_FIELD = "id";

	public static final String DB_TABLE_PREFIX = "ldrbrd_";
	public static final String DB_TABLE_GOLFER = DB_TABLE_PREFIX + "golfer";
	public static final String DB_TABLE_COMPETITION = DB_TABLE_PREFIX + "competition";
	public static final String DB_TABLE_COMPETITION_ROUND = DB_TABLE_PREFIX + "competition_round";
	public static final String DB_TABLE_COMPETITION_ENTRY = DB_TABLE_PREFIX + "competition_entry";
	public static final String DB_TABLE_SCORECARD = DB_TABLE_PREFIX + "scorecard";
	public static final String DB_TABLE_HOLE_SCORE = DB_TABLE_PREFIX + "hole_score";
	public static final String DB_TABLE_COMPETITION_ROUND_SCORE = DB_TABLE_PREFIX
			+ "competition_round_score";

}
