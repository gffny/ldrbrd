package com.gffny.ldrbrd.common.service;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Golfer;

public interface IUserProfileService {

	/**
	 * @param string
	 * @return
	 */
	public Golfer getGolferByHandle(String string) throws ServiceException;

	/**
	 * @param string
	 * @return
	 */
	public Golfer getGolferByEmail(String string) throws ServiceException;

	/**
	 * @param id
	 * @return
	 */
	public Golfer getGolferById(int id) throws ServiceException;
}