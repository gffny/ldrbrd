/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;

/**
 * @author John D. Gaffney | gffny.com
 */
@NamedQueries(@NamedQuery(name = Competition.FIND_BY_COMPETITION_NAME, query = "select c from Competition c where c.competitionName = :competitionName"))
@Entity
@Table(name = Constant.DB_TABLE_COMPETITION)
public class Competition extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = -412837881642668059L;

	/** */
	public static final String FIND_BY_COMPETITION_NAME = "COMPETITION_FIND_BY_NAME";

	/** */
	@JsonIgnore
	private String competitionName;

	/** */
	@JsonIgnore
	private int competitorLimit;

	/** */
	@JsonIgnore
	private DateTime startDate;

	/**
	 * @param competitionName
	 * @return
	 */
	public static Competition createNewCompetition(String competitionName) {
		return new Competition(competitionName);
	}

	/**
	 * 
	 */
	public Competition() {
		// hibernate required non-private zero-argument constructor
	}

	/**
	 * @param competitionName
	 */
	private Competition(String competitionName) {
		this.competitionName = competitionName;
	}

	/**
	 * @return
	 */
	@Column(name = "competition_name")
	public String getCompetitionName() {
		return this.competitionName;
	}

	/**
	 * @param competitionName
	 */
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	/**
	 * @return
	 */
	@Column(name = "start_date")
	public Date getStartDate() {
		return this.startDate.toDate();
	}

	/**
	 * @return
	 */
	@Transient
	public DateTime getStartDateDT() {
		return this.startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		setStartDateDT(new DateTime(startDate));
	}

	/**
	 * @param startDate
	 */
	@Transient
	public void setStartDateDT(DateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return
	 */
	@Column(name = "competitor_limit")
	public int getCompetitorLimit() {
		return this.competitorLimit;
	}

	/**
	 * @param competitionName
	 */
	public void setCompetitorLimit(int competitorLimit) {
		this.competitorLimit = competitorLimit;
	}

	// TODO add the organising society and golfer
	// TODO make organising golf not-null in database
}
