package com.gffny.ldrbrd.common.service;

import com.gffny.ldrbrd.common.exception.AuthorizationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.web.GolferDigestResponse;

public interface IUserProfileService {

	/**
	 * @param string
	 * @return
	 */
	public Golfer getGolferByHandle(String string) throws AuthorizationException, ServiceException;

	/**
	 * @param string
	 * @return
	 */
	public Golfer getGolferByEmail(String string) throws AuthorizationException, ServiceException;

	/**
	 * @param id
	 * @return
	 */
	public Golfer getGolferById(String id) throws AuthorizationException, ServiceException;

	/**
	 * @param id
	 * @return
	 * @throws AuthorizationException
	 * @throws ServiceException
	 */
	public GolferDigestResponse getDigestById(String id) throws AuthorizationException,
			ServiceException;
}