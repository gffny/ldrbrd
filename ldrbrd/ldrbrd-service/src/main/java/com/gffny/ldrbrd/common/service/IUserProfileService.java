package com.gffny.ldrbrd.common.service;

import java.util.List;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.security.enums.AuthenticationResult;
import com.gffny.ldrbrd.common.security.token.AuthenticationToken;

public interface IUserProfileService {

	/**
	 * @param golfer
	 * @throws ServiceException
	 */
	public void addGolfer(Golfer golfer) throws ServiceException;

	/**
	 * @return
	 * @throws ServiceException
	 */
	public List<Golfer> fetchAllPersons() throws ServiceException;

	/**
	 * @param golfer
	 */
	public void createDefaultGolfBagForGolfer(Golfer golfer) throws ServiceException;

	/**
	 * @param authToken
	 * @return
	 */
	public AuthenticationResult authenticateUser(AuthenticationToken authToken)
			throws ServiceException;

	/**
	 * @param string
	 * @return
	 */
	public Golfer getGolferByHandle(String string) throws ServiceException;

	/**
	 * @param golferHandle
	 * @return
	 */
	public abstract Golfer getGolferWithBagByHandle(String golferHandle) throws ServiceException;

	/**
	 * @param string
	 * @return
	 */
	public Golfer getGolferByEmail(String string) throws ServiceException;
}