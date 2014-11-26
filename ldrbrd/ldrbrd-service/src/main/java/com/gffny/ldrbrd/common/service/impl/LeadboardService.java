/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.service.ILeaderboardService;

/**
 * @author John D. Gaffney | gffny.com
 */
public class LeadboardService extends AbstractService implements ILeaderboardService {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.ILeaderboardService#startCompetitionRound(com.gffny.ldrbrd
	 *      .common.model.impl.Golfer, int, com.gffny.ldrbrd.common.model.impl.CompetitionRound)
	 */
	public void startCompetitionRound(Golfer golfer, int handicap, CompetitionRound competitionRound)
			throws ServiceException {
		throw new ServiceException("not implemented");
	}

}
