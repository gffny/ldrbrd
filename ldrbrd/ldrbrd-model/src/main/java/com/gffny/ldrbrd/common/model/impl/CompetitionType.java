/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import com.gffny.ldrbrd.common.model.CommonIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 */
public class CompetitionType extends CommonIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3911469317943886156L;

	private String name;
	private String scheduler;
	private String scorer;
	private boolean isTeam;
	private boolean isPair;
	private boolean isIndividual;

	/**
	 * @param name
	 * @param scheduler
	 * @param scorer
	 * @param isTeam
	 * @param isPair
	 * @param isIndividual
	 */
	public CompetitionType(String name, String scheduler, String scorer, boolean isTeam,
			boolean isPair, boolean isIndividual) {
		this.name = name;
		this.scheduler = scheduler;
		this.scorer = scorer;
		this.isTeam = isTeam;
		this.isPair = isPair;
		this.isIndividual = isIndividual;
	}

	/**
	 * @param name
	 * @param scheduler
	 * @param scorer
	 * @param isTeam
	 * @param isPair
	 * @param isIndividual
	 */
	public CompetitionType(int id, String name, String scheduler, String scorer, boolean isTeam,
			boolean isPair, boolean isIndividual) {
		this.name = name;
		this.setId(id);
		this.scheduler = scheduler;
		this.scorer = scorer;
		this.isTeam = isTeam;
		this.isPair = isPair;
		this.isIndividual = isIndividual;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the scheduler
	 */
	public String getScheduler() {
		return scheduler;
	}

	/**
	 * @return the scorer
	 */
	public String getScorer() {
		return scorer;
	}

	/**
	 * @return the isTeam
	 */
	public boolean isTeam() {
		return isTeam;
	}

	/**
	 * @return the isPair
	 */
	public boolean isPair() {
		return isPair;
	}

	/**
	 * @return the isIndividual
	 */
	public boolean isIndividual() {
		return isIndividual;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompetitionType [name=" + getName() + ", scheduler=" + scheduler + ", scorer="
				+ scorer + ", isTeam=" + isTeam + ", isPair=" + isPair + ", isIndividual="
				+ isIndividual + "]";
	}
}
