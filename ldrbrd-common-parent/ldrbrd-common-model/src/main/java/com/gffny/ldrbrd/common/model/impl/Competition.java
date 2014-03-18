/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author jdgaffney
 * 
 */
@NamedQueries(@NamedQuery(name = Competition.FIND_BY_COMPETITION_NAME, query = "select c from Competition c where c.competitionName = :competitionName"))
@Entity
@Table(name = "t_competition")
public class Competition extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -412837881642668059L;

	/**
	 * 
	 */
	public static final String FIND_BY_COMPETITION_NAME = "COMPETITION_FIND_BY_NAME";

	/**
	 * 
	 */
	@JsonIgnore
	private String competitionName;

	/**
	 * 
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
	 * 
	 * @param competitionName
	 */
	private Competition(String competitionName) {
		this.competitionName = competitionName;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "cmpttn_nm")
	public String getCompetitionName() {
		return this.competitionName;
	}

	/**
	 * 
	 * @param competitionName
	 */
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}
}
