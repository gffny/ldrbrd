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
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.Golfer;

/**
 * @author jdgaffney
 * 
 */
@NamedQueries({
		@NamedQuery(name = CompetitionRegistration.COMPETITION_LIST_BY_GOLFER_ID, query = "SELECT cr FROM CompetitionRegistration cr WHERE cr.golfer.id = :golferId"),
		@NamedQuery(name = CompetitionRegistration.REGISTRATION_BY_GOLFER_ID_AND_COMPETITION_ID, query = "SELECT cr FROM CompetitionRegistration cr WHERE cr.golfer.id = :golferId AND cr.competition.id = :competitionId") })
@Entity
@Table(name = "t_competition_registration")
public class CompetitionRegistration extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1182398514781958951L;

	/**
	 * 
	 */
	public static final String COMPETITION_LIST_BY_GOLFER_ID = "registration_competition_list_by_golfer_id";

	/**
	 * 
	 */
	public static final String REGISTRATION_BY_GOLFER_ID_AND_COMPETITION_ID = "registration_by_golfer_id_and_competition_id";

	/**
	 * 
	 */
	private Golfer golfer;

	/**
	 * 
	 */
	private Competition competition;

	/**
	 * 
	 */
	int competitionHandicap;

	/**
	 * default zero arg constructor
	 */
	public CompetitionRegistration() {
		super();
	}

	/**
	 * 
	 * @param golfer
	 * @param course
	 */
	public CompetitionRegistration(Golfer golfer, Competition competition) {
		super();
		this.golfer = golfer;
		this.competition = competition;
	}

	/**
	 * 
	 * @param golfer
	 * @param competition
	 * @param handicap
	 */
	public CompetitionRegistration(Golfer golfer,
			Competition competition, int handicap) {
		super();
		this.competition = competition;
		this.golfer = golfer;
		this.competitionHandicap = handicap;
	}

	/**
	 * @return the golfer
	 */
	@XmlTransient
	@JsonIgnore
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cmpttn_id", nullable = false)
	@ForeignKey(name = "id")
	public Competition getCompetition() {
		return competition;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	/**
	 * @return the favouritePosition
	 */
	@Column(name = "cmpttn_hndcp")
	public int getCompetitionHandicap() {
		return competitionHandicap;
	}

	/**
	 * @param favouritePosition
	 *            the favouritePosition to set
	 */
	public void setCompetitionHandicap(int competitionHandicap) {
		this.competitionHandicap = competitionHandicap;
	}

}
