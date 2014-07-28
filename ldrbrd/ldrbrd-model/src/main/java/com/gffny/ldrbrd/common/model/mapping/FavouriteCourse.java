/**
 * 
 */
package com.gffny.ldrbrd.common.model.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.Golfer;

/**
 * @author John D. Gaffney | gffny.com
 */
@NamedQueries({ @NamedQuery(name = FavouriteCourse.FAVOURITE_LIST_BY_GOLFER_ID, query = "SELECT fc from FavouriteCourse fc WHERE fc.golfer.id = :golferId") })
@Entity
@Table(name = "t_favourite_course")
public class FavouriteCourse extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1182398514781958951L;

	/**
	 * 
	 */
	public static final String FAVOURITE_LIST_BY_GOLFER_ID = "favourite_list_by_golfer_id";

	/**
	 * 
	 */
	private Golfer golfer;

	/**
	 * 
	 */
	private Course course;

	/**
	 * 
	 */
	int favouritePosition;

	/**
	 * default zero arg constructor
	 */
	public FavouriteCourse() {
		super();
	}

	/**
	 * @param golfer
	 * @param course
	 */
	public FavouriteCourse(Golfer golfer, Course course) {
		super();
		this.golfer = golfer;
		this.course = course;
	}

	/**
	 * @return the golfer
	 */
	@JsonIgnore
	// No need to show the golfer
	@XmlTransient
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "glfr_id", nullable = false)
	@ForeignKey(name = "id")
	public Golfer getGolfer() {
		return golfer;
	}

	/**
	 * @param golfer
	 *            the golfer to set
	 */
	public void setGolfer(Golfer golfer) {
		this.golfer = golfer;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the favouritePosition
	 */
	@Column(name = "fvrt_pstn")
	public int getFavouritePosition() {
		return favouritePosition;
	}

	/**
	 * @param favouritePosition
	 *            the favouritePosition to set
	 */
	public void setFavouritePosition(int favouritePosition) {
		this.favouritePosition = favouritePosition;
	}

}
