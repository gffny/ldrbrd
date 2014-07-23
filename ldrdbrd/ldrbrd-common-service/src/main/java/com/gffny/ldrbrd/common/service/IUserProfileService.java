package com.gffny.ldrbrd.common.service;

import java.util.List;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.security.enums.AuthenticationResult;
import com.gffny.ldrbrd.common.security.token.AuthenticationToken;

public interface IUserProfileService {

	/**
	 * 
	 * @param golfer
	 * @throws ServiceException
	 */
	public void addGolferProfile(GolferProfile golfer) throws ServiceException;

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<GolferProfile> fetchAllPersons() throws ServiceException;

	/**
	 * 
	 * @param golfer
	 */
	public void createDefaultGolfBagForGolfer(GolferProfile golfer)
			throws ServiceException;

	/**
	 * 
	 * @param authToken
	 * @return
	 */
	public AuthenticationResult authenticateUser(AuthenticationToken authToken)
			throws ServiceException;

	/**
	 * 
	 * @param string
	 * @return
	 */
	public GolferProfile getGolferByHandle(String string)
			throws ServiceException;

	/**
	 * 
	 * @param golferHandle
	 * @return
	 */
	public abstract GolferProfile getGolferWithBagByHandle(String golferHandle)
			throws ServiceException;

	/**
	 * 
	 * @param string
	 * @return
	 */
	public GolferProfile getGolferByEmail(String string)
			throws ServiceException;
}