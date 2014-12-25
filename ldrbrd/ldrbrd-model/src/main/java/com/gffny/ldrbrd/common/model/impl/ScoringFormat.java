/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
@Entity
@Table(name = Constant.DB_TABLE_SCORING_FORMAT)
public class ScoringFormat extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = -6843437766601784323L;

	/** */
	private String formatName;

	/** */
	private String scoringClass;

	/** */
	public ScoringFormat() {

	}

	/**
	 * 
	 * @param formatName
	 * @param scoringClass
	 */
	public ScoringFormat(String formatName, String scoringClass) {
		this.formatName = formatName;
		this.scoringClass = scoringClass;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "format_name")
	public String getFormatName() {
		return this.formatName;
	}

	/**
	 * 
	 * @param formatName
	 */
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "scoring_class")
	public String getScoringClass() {
		return this.scoringClass;
	}

	/**
	 * 
	 * @param scoringClass
	 */
	public void setScoringClass(String scoringClass) {
		this.scoringClass = scoringClass;
	}
}
