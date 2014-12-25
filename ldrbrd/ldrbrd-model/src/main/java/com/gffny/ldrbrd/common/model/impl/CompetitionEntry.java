/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;

/**
 * @author John D. Gaffney | gffny.com
 */
@NamedQueries({
		@NamedQuery(name = CompetitionEntry.FIND_BY_COMPETITION_AND_GOLFER, query = "select c from CompetitionEntry c where c.competition.id = :competitionId and c.golfer.id = :golferId"),
		@NamedQuery(name = CompetitionEntry.FIND_BY_GOLFER_ID, query = "select c from CompetitionEntry c where c.golfer.id = :golferId"),
		@NamedQuery(name = CompetitionEntry.FIND_BY_COMPETITION_ID_LIST_BY_GOLFER_ID, query = "select c.competition.id from CompetitionEntry c where c.golfer.id = :golferId") })
@Entity
@Table(name = Constant.DB_TABLE_COMPETITION_ENTRY)
public class CompetitionEntry extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = -6660447296357325296L;

	/** */
	public static final String FIND_BY_COMPETITION_AND_GOLFER = "FIND_BY_COMPETITION_AND_GOLFER";

	/** */
	public static final String FIND_BY_GOLFER_ID = "FIND_BY_GOLFER_ID";

	/** */
	public static final String FIND_BY_COMPETITION_ID_LIST_BY_GOLFER_ID = "FIND_BY_COMPEITION_ID_LIST_BY_GOLFER_ID";

	/** */
	private Competition competition;

	/** */
	private Golfer golfer;

	/** */
	private DateTime entryDate;

	/** */
	private List<Scorecard> entryScorecardList;

	/** */
	public CompetitionEntry() {
		// hibernate required non-zero constructor
	}

	/**
	 * 
	 * @param competition
	 * @param golfer
	 */
	public CompetitionEntry(Competition competition, Golfer golfer) {
		this.competition = competition;
		this.golfer = golfer;
		this.entryDate = new DateTime(new Date());
	}

	/**
	 * @return the competition
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competition_id", nullable = false)
	public Competition getCompetition() {
		return competition;
	}

	/**
	 * @param competition
	 *            the competition to set
	 */
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	/**
	 * @return the golfer
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "golfer_id", nullable = false)
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
	 * @return the entryDate
	 */
	@Column(name = "entry_date")
	public Date getEntryDate() {
		return entryDate.toDate();
	}

	/**
	 * @return
	 */
	@Transient
	public DateTime getEntryDateDT() {
		return this.entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		setEntryDateDT(new DateTime(entryDate));
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDateDT(DateTime entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = Constant.DB_TABLE_COMPETITION_ROUND_SCORE, joinColumns = { @JoinColumn(name = "competition_entry_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "scorecard_id", nullable = false, updatable = false) })
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Scorecard> getEntryScorecardList() {
		return this.entryScorecardList;
	}

	/**
	 * @param holeScoreArray
	 */
	public void setEntryScorecardList(List<Scorecard> entryScorecardList) {
		this.entryScorecardList = entryScorecardList;
	}

}
