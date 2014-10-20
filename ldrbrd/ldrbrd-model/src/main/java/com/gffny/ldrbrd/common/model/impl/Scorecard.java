/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;

/**
 * @author John Gaffney (john@gffny.com) Jul 30, 2012
 */

@NamedQueries({ @NamedQuery(name = Scorecard.FIND_ACTIVE_SCORECARD, query = "SELECT s FROM Scorecard s WHERE s.active = true and s.golfer.id = :golferId") })
@Entity
@Table(name = Constant.DB_TABLE_SCORECARD)
public class Scorecard extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = 641411664200798837L;

	/** */
	public static final String FIND_ACTIVE_SCORECARD = "SCORECARD.FIND_ACTIVE_SCORECARD";

	/** */
	public static final String FIND_SCORECARD_BY_COMPETITION_ROUND_AND_GOLFER = "findScorecardByCompetitionRoundAndGolfer";

	/** */
	public static final String FIND_SCORECARDS_BY_GOLFER_ID = "findScorecardsByGolferId";

	/** */
	private Golfer golfer;

	/** */
	private String courseDocumentId;

	/** */
	private Course course;

	/** */
	private DateTime roundDate;

	/** */
	private int handicap;

	/** */
	private String conditions;

	/** */
	private String scorecardNotes;

	/** TODO MOVE TO COMMON_ID_ENTITY */
	private boolean isActive;

	/** */
	private List<Integer> holeScoreArray = new ArrayList<Integer>();

	/**
	 * Factory method to create a instance of Scorecard
	 * 
	 * @param golferId
	 * @param courseId
	 * @param handicap
	 * @return
	 */
	public static Scorecard createNewScorecard(Golfer golfer, Course course, int handicap) {
		Scorecard scorecard = new Scorecard(golfer, course, handicap);
		scorecard.initDates(new DateTime(System.currentTimeMillis()));
		return scorecard;
	}

	/**
	 *
	 */
	public Scorecard() {
		// hibernate required non-private zero-argument constructor
	}

	/**
	 * @param golfer
	 * @param scoreKeeper
	 * @param course
	 * @param handicap
	 */
	private Scorecard(Golfer golfer, Course course, int handicap) {
		this.golfer = golfer;
		this.course = course;
		this.courseDocumentId = course.getIdString();
		this.handicap = handicap;
		this.isActive = true;
	}

	/**
	 * @return
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "golfer_id", nullable = false)
	@ForeignKey(name = "id")
	public Golfer getGolfer() {
		return this.golfer;
	}

	/**
	 * @param golferId
	 */
	public void setGolfer(Golfer golfer) {
		this.golfer = golfer;
	}

	/**
	 * @return
	 */
	@Column(name = "course_did")
	public String getCourseDocumentId() {
		return this.courseDocumentId;
	}

	/**
	 * @param courseId
	 */
	public void setCourseDocumentId(String courseDocumentId) {
		this.courseDocumentId = courseDocumentId;
	}

	/**
	 * TODO populate this field in the competition service
	 * 
	 * @return
	 */
	@Transient
	public Course getCourse() {
		return this.course;
	}

	/**
	 * @param course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 
	 */
	@Column(name = "round_date")
	public Date getRoundDate() {
		return this.roundDate.toDate();
	}

	/**
	 * @param scorecardDate
	 */
	public void setRoundDate(Date scorecardDate) {
		this.roundDate = new DateTime(scorecardDate);
	}

	/**
	 * @return
	 */
	@Transient
	public DateTime getRoundDateDT() {
		return this.roundDate;
	}

	/**
	 * @param scorecardDateTime
	 */
	@Transient
	public void setRoundDateDT(DateTime scorecardDateTime) {
		this.roundDate = scorecardDateTime;
	}

	/**
	 * @return
	 */
	@Column(name = "handicap")
	public int getHandicap() {
		return handicap;
	};

	/**
	 * @param handicap
	 */
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

	/**
	 * 
	 */
	@Column(name = "conditions")
	public String getConditions() {
		return this.conditions;
	}

	/**
	 * @param scorecardNotes
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/**
	 * 
	 */
	@Column(name = "scorecard_notes")
	public String getScorecardNotes() {
		return this.scorecardNotes;
	}

	/**
	 * @param scorecardNotes
	 */
	public void setScorecardNotes(String scorecardNotes) {
		this.scorecardNotes = scorecardNotes;
	}

	/**
	 * @return
	 */
	@Column(name = "is_active", columnDefinition = "BIT", length = 1)
	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * @param isActive
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = Constant.DB_TABLE_HOLE_SCORE, joinColumns = @JoinColumn(name = "scorecard_id"))
	@Column(name = "score")
	public List<Integer> getHoleScoreArray() {
		return this.holeScoreArray;
	}

	/**
	 * @param holeScoreArray
	 */
	public void setHoleScoreArray(List<Integer> holeScoreArray) {
		this.holeScoreArray = holeScoreArray;
	}

	/**
	 * @param dateTime
	 */
	private void initDates(DateTime dateTime) {
		this.setRoundDateDT(dateTime);
		this.setCreatedDateDT(dateTime);
	}

	/**
	 * TODO recreate this toString method when class is full
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Transient
	@Override
	public String toString() {
		return "Scorecard [id=" + getId() + ", golfer=" + golfer + ", courseDocumentId="
				+ courseDocumentId + ", course=" + course + ", roundDate=" + roundDate
				+ ", handicap=" + handicap + ", conditions=" + conditions + ", scorecardNotes="
				+ scorecardNotes + ", holeScoreArray=" + holeScoreArray + "]";
	}

}