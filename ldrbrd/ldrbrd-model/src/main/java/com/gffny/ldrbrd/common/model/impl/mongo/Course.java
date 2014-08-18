/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl.mongo;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.mongodb.morphia.annotations.Entity;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;
import com.gffny.ldrbrd.common.model.enums.TeeColour;

/**
 * @author John D. Gaffney | gffny.com
 */
@Entity
public class Course extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2501872481702879896L;

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
	 * @param courseName
	 * @param club
	 * @param teeColour
	 * @param slopeIndex
	 * @param par
	 * @param courseImageReference
	 * @return
	 */
	public static Course createCourse(String courseName, Club club, TeeColour teeColour,
			Double slopeIndex, Integer par, String courseImageReference) {
		Course newCourse = new Course(courseName, club, teeColour, slopeIndex, par,
				courseImageReference);
		return newCourse;
	}

	/**
	 * @param courseName
	 * @param club
	 * @param teeColour
	 * @param slopeIndex
	 * @param par
	 * @param courseImageReference
	 */
	private Course(String courseName, Club club, TeeColour teeColour, Double slopeIndex,
			Integer par, String courseImageReference) {
		this.setName(courseName);
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
	public String getCourseName() {
		return this.getName();
	}

	/**
	 * @param courseName
	 *            the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.setName(courseName);
	}

	/**
	 * @return the club
	 */
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
	 * @return
	 */
	public List<CourseHole> getCourseHoleList() {
		return this.courseHoleList;
	}

	/**
	 * @param courseHoleList
	 */
	public void setCourseHoleList(final List<CourseHole> courseHoleList) {
		this.courseHoleList = courseHoleList;
	}

	/**
	 * @return
	 */
	public boolean isNineHole() {
		return par < 40;
	}
}
