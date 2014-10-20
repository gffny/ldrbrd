/**
 * 
 */
package com.gffny.ldrbrd.common.exception;

import org.hibernate.service.spi.ServiceException;

import com.gffny.ldrbrd.common.model.impl.Scorecard;

/**
 * @author John D. Gaffney | gffny.com
 */
public class ActiveScorecardServiceException extends ServiceException {

	private Scorecard scorecard;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3624732914524557089L;

	/**
	 * @param m
	 * @param s
	 */
	public ActiveScorecardServiceException(String m, Scorecard s) {
		super(m);
		this.scorecard = s;
	}

	public ActiveScorecardServiceException(String m) {
		super(m);
	}

	/**
	 * @return
	 */
	public Scorecard getScorecard() {
		return this.scorecard;
	}

}
