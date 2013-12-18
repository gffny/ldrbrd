package com.gffny.ldrbrd.common.service;

import java.util.List;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.security.enums.AuthenticationResult;
import com.gffny.ldrbrd.common.security.token.AuthenticationToken;

public interface IPersonService {

	/**
	 * 
	 * @param golfer
	 * @throws DataAccessException
	 */
	public abstract void addPerson(GolferProfile golfer)
			throws DataAccessException;

	/**
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public abstract List<GolferProfile> fetchAllPersons()
			throws DataAccessException;

	/**
	 * 
	 * @param authToken
	 * @return
	 */
	public abstract AuthenticationResult authenticateUser(
			AuthenticationToken authToken);

}