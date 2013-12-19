/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.impl.LeaderboardUserDetails;
import com.gffny.ldrbrd.common.persistence.GenericDao;
import com.gffny.ldrbrd.common.service.IAuthorisationService;
import com.gffny.ldrbrd.utils.MapUtils;

/**
 * @author John Gaffney (john@gffny.com) Dec 23, 2012
 * 
 */
@Service
public class AuthorisationService implements IAuthorisationService,
		UserDetailsService {

	private static Logger log = Logger.getLogger(AuthorisationService.class);

	@Autowired
	private GenericDao<GolferProfile> golferDao;

	/* (non-Javadoc)
	 * @see com.gffny.ldrbrd.common.service.impl.IAuthorisationService#isPermitted(java.lang.String, java.lang.String)
	 */
	public boolean isPermitted(String userId, String enterScorecard) {
		// TODO handle authorisation
		return true;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("profileHandle", username);
			List<GolferProfile> golferList = golferDao.findByNamedQuery(GolferProfile.FIND_BY_HANDLE, queryMap, 1);
			if(golferList !=null && golferList.size() == 1) {
				return new LeaderboardUserDetails(golferList.get(0));
			} else if (golferList !=null && golferList.size() > 1) {
				log.error("No unique user has been found for user ("+username+")");
				throw new UsernameNotFoundException("No unique user has been found for user ("+username+")");
			} else if (golferList !=null && golferList.size() < 1) {
				log.error("User (" + username + ") has not been found");
				throw new UsernameNotFoundException("User (" + username
						+ ") has not been found");
			} else {
				log.error("other issues here");
				throw new UsernameNotFoundException("Other issues!");
			}
		} catch (DataAccessException dae) {
			log.error("User (" + username + ") has not been found");
			throw new UsernameNotFoundException("User (" + username
					+ ") has not been found");
		}
	}
}
