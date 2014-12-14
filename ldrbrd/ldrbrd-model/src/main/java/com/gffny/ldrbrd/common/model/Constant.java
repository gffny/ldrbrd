/**
 * 
 */
package com.gffny.ldrbrd.common.model;

/**
 * @author John D. Gaffney | gffny.com
 */
public interface Constant {

	public static final String DB_ASTERISK = "*";
	public static final String DB_ID_FIELD = "id";

	public static final String DB_TABLE_PREFIX = "ldrbrd_";
	public static final String DB_TABLE_GOLFER = DB_TABLE_PREFIX + "golfer";
	public static final String DB_TABLE_COMPETITION = DB_TABLE_PREFIX
			+ "competition";
	public static final String DB_TABLE_COMPETITION_ROUND = DB_TABLE_PREFIX
			+ "competition_round";
	public static final String DB_TABLE_COMPETITION_ENTRY = DB_TABLE_PREFIX
			+ "competition_entry";
	public static final String DB_TABLE_SCORECARD = DB_TABLE_PREFIX
			+ "scorecard";
	public static final String DB_TABLE_HOLE_SCORE = DB_TABLE_PREFIX
			+ "hole_score";
	public static final String DB_TABLE_COMPETITION_ROUND_SCORE = DB_TABLE_PREFIX
			+ "competition_round_score";
	public static final String DB_TABLE_SCORING_FORMAT = DB_TABLE_PREFIX
			+ "scoring_format";

	public static final String MONGO_DB_CLUB_COLLECTION = DB_TABLE_PREFIX
			+ "club";
	public static final String MONGO_DB_COURSE_COLLECTION = DB_TABLE_PREFIX
			+ "course";
	public static final String MONGO_DB_SCORECARD_COLLECTION = DB_TABLE_PREFIX
			+ "scorecard";

	public static final String MONGO_DB_NAME = "ldrbrd";
	public static final String MONGO_MAP_PACKAGE = "com.gffny.ldrbrd.common.model.impl.mongo";

	public static final int EIGHTEEN_HOLE = 18;
	public static final int INITIAL_SCORE = 0;

	public static final int PROFILE = 1;
	public static final int EMAIL = 2;
	public static final int ADMIN = 3;

	public static final CharSequence EMAIL_AT_SYMBOL = "@";

	public static final String QUERY_PARAM_EMAIL_ADDRESS = "emailAddress";
	public static final String QUERY_PARAM_PROFILE_HANDLE = "profileHandle";
	public static final Object QUERY_PARAM_GOLFER_ID = "golferId";

	/* USER ROLES */
	public static final String ROLE_GOLFER = "ROLE_GOLFER";
	public static final String ROLE_USER = "ROLE_USER";

	/* TABLE SCORECARD FIELDS */
	public static final String DB_SCORECARD_ACTIVE_FIELD = "is_active";
	public static final String DB_SCORECARD_ACTIVE_VALUE = "1";
	public static final String DB_SCORECARD_INACTIVE_VALUE = "0";
	public static final String DB_SCORECARD_SCORECARD_ID = "scorecard_id";
	public static final String DB_SCORECARD_HOLE_NUMBER = "hole_number";
	public static final String DB_SCORECARD_GOLFER_ID = "golfer_id";
	public static final String DB_SCORECARD_SIGNATURE_FIELD = "scorecard_signature";

	/* APPLICATION CONFIGURATION FIELDS */
	public static final String PROPERTY_APPLICATION_ROOT = "application.root";
}
