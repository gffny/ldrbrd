/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@NamedQueries(@NamedQuery(name = Competition.FIND_BY_COMPETITION_NAME, query = "SELECT c FROM Competition c WHERE c.competitionName = :competitionName"))
@Entity
@Table(name = Constant.DB_TABLE_COMPETITION)
public class Competition extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = -412837881642668059L;

	/** */
	public static final String FIND_BY_COMPETITION_NAME = "COMPETITION_FIND_BY_NAME";

	/** */
	private String competitionName;

	/** */
	private int competitorLimit;

	/** */
	@JsonIgnore
	private DateTime startDate;

	/** */
	@JsonIgnore
	private Society society;

	/** */
	@JsonIgnore
	private Golfer golfer;

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

	/**
	 * @return the golfer
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organising_society", nullable = true)
	public Society getSociety() {
		return society;
	}

	/**
	 * @param golfer
	 *            the golfer to set
	 */
	public void setSociety(Society society) {
		this.society = society;
	}

	/**
	 * @return the golfer
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organising_golfer", nullable = true)
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
}
