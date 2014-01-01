/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author jdgaffney
 * 
 */
@NamedQueries({
	@NamedQuery(name = HoleScore.FIND_BY_SCORECARD_ID_AND_HOLE_NUMBER, query = "SELECT hs FROM HoleScore hs WHERE hs.scorecard.id = :scorecardId AND hs.courseHole.holeNumber = :holeNumber")
})
@Entity
@Table(name = "t_hole_score")
public class HoleScore extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4364295284099333222L;

	/**
	 * 
	 */
	public static final String FIND_BY_SCORECARD_ID_AND_HOLE_NUMBER = "findByScorecardIdAndHoleNumber";

	/**
	 * 
	 */
	private Scorecard scorecard;

	/**
	 * 
	 */
	private CourseHole hole;

	/**
	 * 
	 */
	private int holeScore;

	/**
	 * 
	 * @param hsScorecard
	 * @param hsCourseHole
	 * @param score
	 * @return
	 */
	public static HoleScore createInstance(Scorecard hsScorecard,
			CourseHole hsCourseHole, Integer score) {
		HoleScore holeScore = new HoleScore();
		holeScore.setScorecard(hsScorecard);
		holeScore.setCourseHole(hsCourseHole);
		holeScore.setHoleScore(score.intValue());
		return holeScore;
	}
	
	/**
	 * 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "scrcrd_id", nullable = false)
	@ForeignKey(name = "id")
	public Scorecard getScorecard() {
		return this.scorecard;
	}

	/**
	 * 
	 * @param scorecard
	 */
	public void setScorecard(Scorecard scorecard) {
		this.scorecard = scorecard;
	}

	/**
	 * 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name = "hl_id", nullable = false)
	@ForeignKey(name = "id")
	public CourseHole getCourseHole() {
		return this.hole;
	}

	/**
	 * 
	 * @param courseHole
	 */
	public void setCourseHole(CourseHole courseHole) {
		this.hole = courseHole;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "hl_scr", nullable = false)
	public int getHoleScore() {
		return this.holeScore;
	}

	/**
	 * 
	 * @param holeScore
	 */
	public void setHoleScore(int holeScore) {
		this.holeScore = holeScore;
	}
}
