/**
 * 
 */
package com.gffny.ldrbrd.web.auth.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.UserProfile;
import com.gffny.ldrbrd.common.service.IUserProfileService;
import com.gffny.ldrbrd.web.auth.token.LeaderboardRestToken;

/**
 * @author John Gaffney | gffny.com
 * 
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	/**
	 * 
	 */
	private Logger LOG = LoggerFactory
			.getLogger(AuthenticationTokenProcessingFilter.class);

	/**
	 * 
	 */
	@Autowired
	private IUserProfileService userProfileService;

	/**
	 * 
	 */
	private AuthenticationEntryPoint authenticationEntryPoint;

	/**
	 * 
	 */
	public AuthenticationTokenProcessingFilter() {

	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public AuthenticationTokenProcessingFilter(
			AuthenticationManager authenticationManager) {
		this(authenticationManager, new BasicAuthenticationEntryPoint());
		((BasicAuthenticationEntryPoint) authenticationEntryPoint)
				.setRealmName("Username: jack Password: jill");
	}

	/**
	 * 
	 * @param authenticationManager
	 * @param authenticationEntryPoint
	 */
	public AuthenticationTokenProcessingFilter(
			AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		// this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO implement this method taking a token from the request and
		// getting a user from a user service
		if (servletRequest != null && servletResponse != null) {
			HttpServletRequest request = ((HttpServletRequest) servletRequest);
			HttpServletResponse response = ((HttpServletResponse) servletResponse);
			// Pull out the Authorization header
			// String authorization = request.getHeader("Authorization");

			// If the Authorization header is null, let the
			// ExceptionTranslationFilter provided by
			// the <http> namespace kick of the BasicAuthenticationEntryPoint to
			// provide the username and password dialog box
			// if (authorization == null) {
			// filterChain.doFilter(request, response);
			// return;
			// }

			try {
				UserProfile userProfile = userProfileService
						.getGolferByHandle("gffny");
				// TODO add mechanism to get the role authorities from the
				// database
				Collection<SimpleGrantedAuthority> grantedAuthorityCollection = new ArrayList<SimpleGrantedAuthority>();
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						"ROLE_USER");
				grantedAuthorityCollection.add(authority);

				LeaderboardRestToken authentication = new LeaderboardRestToken(
						"gffny", grantedAuthorityCollection, userProfile);

				// Request the authentication manager to authenticate the token

				// Pass the successful token to the SecurityHolder where it can
				// be retrieved by this thread at any stage.
				SecurityContextHolder.getContext().setAuthentication(
						authentication);
				// Continue with the Filters
				filterChain.doFilter(request, response);
			} catch (AuthenticationException authenticationException) {
				// If it fails clear this threads context and kick off the
				// authentication entry point process.
				SecurityContextHolder.clearContext();
				authenticationEntryPoint.commence(request, response,
						authenticationException);
			} catch (ServiceException e) {
				LOG.error(e.getMessage());
				throw new ServletException(e.getMessage());
			}
		}
	}

	public String[] decodeHeader(String authorization) {
		// Decode the Auth Header to get the username and password
		try {
			byte[] decoded = Base64.decode(authorization.substring(6).getBytes(
					"UTF-8"));
			String credentials = new String(decoded);
			return credentials.split(":");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		// this.authenticationManager = authenticationManager;
	}

}
