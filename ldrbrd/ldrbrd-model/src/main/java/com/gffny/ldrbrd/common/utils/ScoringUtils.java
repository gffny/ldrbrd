/**
 * 
 */
package com.gffny.ldrbrd.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gffny.ldrbrd.common.model.nosql.Course;
import com.gffny.ldrbrd.common.model.nosql.CourseHole;
import com.gffny.ldrbrd.common.scoring.ScoringMethod;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public class ScoringUtils {

	/** */
	private static final Logger LOG = LoggerFactory
			.getLogger(ScoringUtils.class);

	/**
	 * @param course
	 * @param holeNumber
	 * @param holeScore
	 * @return
	 */
	public static int toPar(Course course, int holeNumber, int holeScore) {
		// check params
		if (isValidCourse(course, holeNumber)) {
			// get the courseHole
			CourseHole hole = courseHole(course, holeNumber);
			if (hole != null) {
				return holeScore - hole.getPar();
			}
			LOG.error("no course hole returned from course object");
		}
		return 0;
	}

	/**
	 * @param course
	 * @param holeNumber
	 * @param holeScore
	 * @param handicap
	 * @return
	 */
	public static int toHandicapPar(Course course, int holeNumber,
			int holeScore, int handicap) {
		// check params
		if (isValidCourse(course, holeNumber)) {
			// get the courseHole
			CourseHole hole = courseHole(course, holeNumber);
			if (hole != null) {
				return holeScore - hole.getPar()
						- calcHoleHandicap(hole.getIndex(), handicap);
			}
			LOG.error("no course hole returned from course object");
		}
		return 0;
	}

	/**
	 * @param course
	 * @param holeNumber
	 * @param holeScore
	 * @param i
	 * @param stableford
	 * @return
	 */
	public static int competitionScore(Course course, int holeNumber,
			int holeScore, int handicap, ScoringMethod scoringMethod) {
		// check params
		// TODO make this use the passed scoring format scorer
		switch (toHandicapPar(course, holeNumber, holeScore, handicap)) {
		case 1:
			return 1;
		case 0:
			return 2;
		case -1:
			return 3;
		case -2:
			return 4;
		case -3:
			return 5;
		case -4:
			return 6;
		default:
			return 0;
		}
	}

	/**
	 * @param course
	 * @param holeNumber
	 * @return
	 */
	private static CourseHole courseHole(Course course, int holeNumber) {
		return course.getCourseHoleList().get(holeNumber - 1);
	}

	/**
	 * @param index
	 * @param handicap
	 * @return
	 */
	public static int calcHoleHandicap(int index, int handicap) {
		return handicap % 18 >= index ? handicap / 18 + 1 : handicap / 18;
	}

	/**
	 * @param course
	 * @param holeNumber
	 * @return
	 */
	private static boolean isValidCourse(Course course, int holeNumber) {
		return course != null && course.getCourseHoleList() != null
				&& course.getCourseHoleList().size() <= holeNumber;
	}
}
