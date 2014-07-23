package com.gffny.ldrbrd.common.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

@Inheritance
public abstract class AbstractGolfRound extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -161361476840121097L;

	/**
	 * 
	 */
	protected DateTime roundDate;

	/**
	 * 
	 */
	protected Course course;

	/**
	 * default zero arg constructor for Hibernate
	 */
	public AbstractGolfRound() {
	}

	/**
	 * 
	 * @param course
	 * @param startDate
	 */
	public AbstractGolfRound(Course course, DateTime roundDate) {
		this.roundDate = roundDate;
		this.course = course;
	}

	/**
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crs_id", nullable = false)
	@ForeignKey(name = "id")
	public Course getCourse() {
		return this.course;
	}

	/**
	 * 
	 * @param course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "rnd_dt")
	public Date getRoundDate() {
		return this.roundDate.toDate();
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setRoundDate(Date roundDate) {
		this.roundDate = new DateTime(roundDate);
	}

	/**
	 * 
	 * @return
	 */
	@Transient
	public DateTime getRoundDateDT() {
		return this.roundDate;
	}

	/**
	 * 
	 * @param startDate
	 */
	public void setRoundDateDT(DateTime roundDate) {
		this.roundDate = roundDate;
	}

}