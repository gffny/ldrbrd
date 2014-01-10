package com.gffny.ldrbrd.common.service;

import java.util.List;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.security.enums.AuthenticationResult;
import com.gffny.ldrbrd.common.security.token.AuthenticationToken;

public interface IUserProfileService {

	/**
	 * 
	 * @param golfer
	 * @throws DataAccessException
	 */
	public void addGolferProfile(GolferProfile golfer);

	/**
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<GolferProfile> fetchAllPersons();

	/**
	 * 
	 * @param golfer
	 */
	public void createDefaultGolfBagForGolfer(GolferProfile golfer);

	/**
	 * 
	 * @param authToken
	 * @return
	 */
	public AuthenticationResult authenticateUser(AuthenticationToken authToken);

	/**
	 * 
	 * @param string
	 * @return
	 */
	public GolferProfile getGolferByHandle(String string);

	/**
	 * 
	 * @param golferHandle
	 * @return
	 */
	public abstract GolferProfile getGolferWithBagByHandle(String golferHandle);

	/**
	 * 
	 * @param string
	 * @return
	 */
	public GolferProfile getGolferByEmail(String string);
}