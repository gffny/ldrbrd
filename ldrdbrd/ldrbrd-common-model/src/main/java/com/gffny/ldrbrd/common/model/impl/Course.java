/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;
import com.gffny.ldrbrd.common.model.enums.TeeColour;

/**
 * @author jdgaffney
 * 
 */
@NamedQueries({
		@NamedQuery(name = Course.FIND_BY_NAME_AND_TEE_COLOUR, query = "SELECT c FROM Course c WHERE c.courseName = :courseName and c.teeColour = :teeColour"),
		@NamedQuery(name = Course.FIND_BY_CLUB_ID_AND_COURSE_NAME, query = "SELECT c FROM Course c WHERE c.club.id = :clubId and c.courseName = :courseName") })
@Entity
@Table(name = "t_course")
public class Course extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2501872481702879896L;

	/**
	 * 
	 */
	public static final String FIND_BY_NAME_AND_TEE_COLOUR = "find_by_name_and_tee_colour";

	/**
	 * 
	 */
	public static final String FIND_BY_CLUB_ID_AND_COURSE_NAME = "find_by_club_id_and_course_name";

	/**
	 * 
	 */
	private String courseName;

	/**
	 * 
	 */
	private Club club;

	/**
	 * 
	 */
	@Enumerated(EnumType.ORDINAL)
	private TeeColour teeColour;

	/**
	 * 
	 */
	private Double slopeIndex;

	/**
	 * 
	 */
	private Integer par;

	/**
	 * 
	 */
	private String courseImageReference;

	/**
	 * 
	 */
	private List<CourseHole> courseHoleList;

	/**
	 * 
	 * @param courseName
	 * @param club
	 * @param teeColour
	 * @param slopeIndex
	 * @param par
	 * @param courseImageReference
	 * @return
	 */
	public static Course createCourse(String courseName, Club club,
			TeeColour teeColour, Double slopeIndex, Integer par,
			String courseImageReference) {
		Course newCourse = new Course(courseName, club, teeColour, slopeIndex,
				par, courseImageReference);
		return newCourse;
	}

	/**
	 * 
	 * @param courseName
	 * @param club
	 * @param teeColour
	 * @param slopeIndex
	 * @param par
	 * @param courseImageReference
	 */
	private Course(String courseName, Club club, TeeColour teeColour,
			Double slopeIndex, Integer par, String courseImageReference) {
		this.courseName = courseName;
		this.club = club;
		this.teeColour = teeColour;
		this.slopeIndex = slopeIndex;
		this.par = par;
		this.courseImageReference = courseImageReference;
	}

	/**
	 * 
	 */
	public Course() {

	}

	/**
	 * @return the courseName
	 */
	@Column(name = "crs_nm")
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName
	 *            the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the club
	 */
	@ManyToOne
	@JoinColumn(name = "clb_id", nullable = false)
	@ForeignKey(name = "id")
	public Club getClub() {
		return club;
	}

	/**
	 * @param club
	 *            the club to set
	 */
	public void setClub(Club club) {
		this.club = club;
	}

	/**
	 * @return the teeColour
	 */
	@Column(name = "tee_clr")
	public TeeColour getTeeColour() {
		return teeColour;
	}

	/**
	 * @param teeColour
	 *            the teeColour to set
	 */
	public void setTeeColour(TeeColour teeColour) {
		this.teeColour = teeColour;
	}

	/**
	 * @return the slopeIndex
	 */
	@Column(name = "slp_indx")
	public Double getSlopeIndex() {
		return slopeIndex;
	}

	/**
	 * @param slopeIndex
	 *            the slopeIndex to set
	 */
	public void setSlopeIndex(Double slopeIndex) {
		this.slopeIndex = slopeIndex;
	}

	/**
	 * @return the par
	 */
	@Column(name = "par")
	public Integer getPar() {
		return par;
	}

	/**
	 * @param par
	 *            the par to set
	 */
	public void setPar(Integer par) {
		this.par = par;
	}

	/**
	 * @return the courseImageRef
	 */
	@Column(name = "crs_img_ref")
	public String getCourseImageRef() {
		return courseImageReference;
	}

	/**
	 * @param courseImageRef
	 *            the courseImageRef to set
	 */
	public void setCourseImageRef(String courseImageRef) {
		this.courseImageReference = courseImageRef;
	}

	/**
	 * 
	 * @return
	 */
	// @ElementCollection(fetch = FetchType.EAGER)
	// @CollectionTable(name = "t_hole", joinColumns = @JoinColumn(name =
	// "crs_id"))
	@Transient
	public List<CourseHole> getCourseHoleList() {
		return this.courseHoleList;
	}

	/**
	 * 
	 * @param courseHoleList
	 */
	public void setCourseHoleList(final List<CourseHole> courseHoleList) {
		this.courseHoleList = courseHoleList;
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	public boolean isNineHole() {
		return par < 40;
	}
}
