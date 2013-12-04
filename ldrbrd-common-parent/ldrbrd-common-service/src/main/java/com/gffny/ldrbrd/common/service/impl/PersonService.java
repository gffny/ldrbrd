/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.persistence.GenericDao;
import com.gffny.ldrbrd.common.security.enums.AuthenticationResult;
import com.gffny.ldrbrd.common.security.token.AuthenticationToken;
import com.gffny.ldrbrd.common.security.token.impl.EmailPasswordToken;

/**
 * 
 * @author jdgaffney
 * 
 */
@Service
public class PersonService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(PersonService.class);

	/**
	 * 
	 */
	@Autowired
	private GenericDao<GolferProfile> personDao;

	/**
	 * 
	 * @param golfer
	 * @throws DataAccessException
	 */
	public void addPerson(GolferProfile golfer) throws DataAccessException {
		if (golfer != null) {
			LOG.debug("persisting golfer: " + golfer.toString());
			personDao.persist(golfer);
		} else {
			LOG.error("golfer is null");
			throw new DataAccessException("golfer is null");
		}
	}

	/**
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public List<GolferProfile> fetchAllPersons() throws DataAccessException {
		return new ArrayList<GolferProfile>();
	}

	/**
	 * 
	 * @param authToken
	 * @return
	 */
	public AuthenticationResult authenticateUser(AuthenticationToken authToken) {
		// check if authToken is not null
		if (authToken != null) {
			// check token type
			// EmailPasswordToken
			if (authToken instanceof EmailPasswordToken) {
				try {
					GolferProfile golferProfile = personDao
							.findById(GolferProfile.class,
									authToken.getTokenIdentifier());
					if (golferProfile != null) {
						if (golferProfile.getPassword().equals(
								authToken.getTokenAuthenticator())) {
							// reset user login attempts
							return AuthenticationResult.SUCCESS;
						} else {
							// check the number of authentication attempts
							// increase the number of authentication attempts
							return AuthenticationResult.FAILED;
						}
					}
					// update user login attempts
					return AuthenticationResult.FAILED;
				} catch (DataAccessException daex) {
					return AuthenticationResult.ERROR;
				}
			}
		}
		return AuthenticationResult.ERROR;
	}
}