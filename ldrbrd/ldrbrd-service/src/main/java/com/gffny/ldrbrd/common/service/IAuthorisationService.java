package com.gffny.ldrbrd.common.service;

import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.UserProfile;

public interface IAuthorisationService {

	/**
	 * Check that the user (with userId) has privileges to update the scorecard (with scorecardId).
	 * Rules: non-competition scorecard: user is the player. competition scorecard: user is the
	 * scorer, the competition organiser, or organising society admin. society scorecard: user is
	 * the player, or organising society admin
	 * 
	 * @param userId
	 * @param enterScorecard
	 * @return
	 */
	public abstract boolean isPermitted(String userId, String scorecardId);

	/**
	 * @param profileId
	 *            - profile on which the currently logged in user is operating or null for their own
	 *            profile
	 * @return - the profile id to show/operate
	 * @throws AuthorisationException
	 *             - if user isn't authorized
	 * @throws ServiceException
	 *             - if there is a service error
	 */
	public abstract String authorise(String profileId) throws AuthorisationException,
			ServiceException;

	/**
	 * @return
	 * @throws ServiceException
	 */
	public UserProfile getLoggedInUser() throws ServiceException;

	/**
	 * @param priviledge
	 * @return
	 */
	public abstract boolean hasLoggedInUserPrivilege(String priviledge);

	// TODO potentially add new methods to authorize different behaviours, such as society admin
	// behaviours, etc.
}