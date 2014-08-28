/**
 * 
 */
package com.gffny.ldrbrd.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.service.impl.AbstractService;
import com.gffny.ldrbrd.common.utils.CollectionUtils;
import com.gffny.ldrbrd.common.utils.StringUtils;
import com.gffny.ldrbrd.security.token.LdrbrdAuthenticationToken;

/**
 * @author John D. Gaffney | gffny.com
 */
public class LdrbrdAuthenticationProvider extends AbstractService implements AuthenticationProvider {

	/** */
	private static final Logger LOG = LoggerFactory.getLogger(LdrbrdAuthenticationProvider.class);

	/** */
	@Autowired
	private GenericDao<Golfer> userDao;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.
	 * springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		try {
			String name = authentication.getName();
			String password = authentication.getCredentials().toString();
			Golfer user = null;

			switch (authenticationType(name)) {
			case Constant.EMAIL:
				user = getGolferFromList(userDao.findByNamedQuery(Golfer.FIND_BY_EMAIL,
						populateParamMap(Constant.QUERY_PARAM_EMAIL_ADDRESS, name)));
				break;
			case Constant.PROFILE:
				user = getGolferFromList(userDao.findByNamedQuery(Golfer.FIND_BY_HANDLE,
						populateParamMap(Constant.QUERY_PARAM_PROFILE_HANDLE, name)));
				break;
			case Constant.ADMIN:
				// TODO IMPLEMENT ADMIN LOGIN
				break;
			default:
				throw new BadCredentialsException("username or email is incorrect");
			}
			// use the credentials to try to authenticate against the third party system
			if (user != null) {
				// TODO HANDLE AUTHORITIES FOR REALZ!
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_GOLFER"));
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				return new LdrbrdAuthenticationToken(user, password, grantedAuths);
			}
		} catch (PersistenceException se) {
			LOG.error(se.getMessage());
		}
		throw new BadCredentialsException("Unable to auth against third party systems");
	}

	/**
	 * @param golferList
	 * @return
	 */
	private Golfer getGolferFromList(List<Golfer> golferList) throws AuthenticationException {

		if (CollectionUtils.isNotEmpty(golferList)) {
			if (golferList.size() == 1) {
				return golferList.get(0);
			}
			LOG.error("list of golfers returned from the data has size {}", golferList.size());
		}
		throw new UsernameNotFoundException("username was not found in the database");
	}

	/**
	 * checks the passed parameter to see if it's an Open ID, Facebook Token, profile handle, or
	 * email address
	 * 
	 * @param name
	 * @return
	 */
	private int authenticationType(String name) {
		// check for not empty
		if (StringUtils.isNotEmpty(name)) {
			// if the string contains an at symbol it's an email address
			if (name.contains(Constant.EMAIL_AT_SYMBOL)) {
				return Constant.EMAIL;
			} else {
				return Constant.PROFILE;
			}
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(LdrbrdAuthenticationToken.class)
				|| authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
