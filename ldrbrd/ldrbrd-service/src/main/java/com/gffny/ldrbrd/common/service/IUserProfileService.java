package com.gffny.ldrbrd.common.service;

import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.web.GolferDigestResponse;

public interface IUserProfileService {

	/**
	 * @param string
	 * @return
	 * @throws AuthorisationException
	 * @throws ServiceException
	 */
	public Golfer getGolferByHandle(String string)
			throws AuthorisationException, ServiceException;

	/**
	 * @param string
	 * @return
	 * @throws AuthorisationException
	 * @throws ServiceException
	 */
	public Golfer getGolferByEmail(String string)
			throws AuthorisationException, ServiceException;

	/**
	 * @param id
	 * @return
	 * @throws AuthorisationException
	 * @throws ServiceException
	 */
	public Golfer getGolferById(String id) throws AuthorisationException,
			ServiceException;

	/**
	 * @param id
	 * @return
	 * @throws AuthorisationException
	 * @throws ServiceException
	 */
	public GolferDigestResponse getDigestById(String id)
			throws AuthorisationException, ServiceException;

	/**
	 * returns the digest for user who is logged in
	 * 
	 * @return
	 * @throws AuthorisationException
	 * @throws ServiceException
	 */
	public GolferDigestResponse getLoggedInGolferDigest()
			throws AuthorisationException, ServiceException;
}