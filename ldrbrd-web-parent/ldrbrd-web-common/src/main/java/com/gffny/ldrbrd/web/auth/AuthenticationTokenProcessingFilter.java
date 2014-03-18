/**
 * 
 */
package com.gffny.ldrbrd.web.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import com.gffny.ldrbrd.common.service.IUserProfileService;
import com.gffny.ldrbrd.common.utils.TokenUtils;

/**
 * @author John Gaffney | gffny.com
 * 
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	@Autowired
	private IUserProfileService userService;
	@Autowired
	private TokenUtils tokenUtils;
	private AuthenticationManager authenticationManager;
	private AuthenticationEntryPoint authenticationEntryPoint;

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
	 */
	public AuthenticationTokenProcessingFilter(
			AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	// public void doFilter(ServletRequest request, ServletResponse response,
	// FilterChain chain) throws IOException, ServletException {
	// @SuppressWarnings("unchecked")
	// Map<String, String[]> parms = request.getParameterMap();
	//
	// if (parms.containsKey("token")) {
	// String token = parms.get("token")[0]; // grab the first "token"
	// // parameter
	//
	// // validate the token
	// if (tokenUtils.validate(token)) {
	// // determine the user based on the (already validated) token
	// UserDetails userDetails = tokenUtils.getUserFromToken(token);
	// // build an Authentication object with the user's info
	// UsernamePasswordAuthenticationToken authentication = new
	// UsernamePasswordAuthenticationToken(
	// userDetails.getUsername(), userDetails.getPassword());
	// authentication.setDetails(new WebAuthenticationDetailsSource()
	// .buildDetails((HttpServletRequest) request));
	// // set the authentication into the SecurityContext
	// SecurityContextHolder.getContext().setAuthentication(
	// authenticationManager.authenticate(authentication));
	// }
	// }
	// // continue thru the filter chain
	// chain.doFilter(request, response);
	// }

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// Pull out the Authorization header
		String authorization = request.getHeader("Authorization");

		// If the Authorization header is null, let the
		// ExceptionTranslationFilter provided by
		// the <http> namespace kick of the BasicAuthenticationEntryPoint to
		// provide the username and password dialog box
		if (authorization == null) {
			chain.doFilter(request, response);
			return;
		}

		String[] credentials = decodeHeader(authorization);
		assert credentials.length == 2;

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				credentials[0], credentials[1]);

		try {

			((UsernamePasswordAuthenticationToken) authentication)
					.setDetails(new WebAuthenticationDetails(request));
			// Request the authentication manager to authenticate the token
			Authentication successfulAuthentication = authenticationManager
					.authenticate(authentication);

			// Pass the successful token to the SecurityHolder where it can be
			// retrieved by this thread at any stage.
			SecurityContextHolder.getContext().setAuthentication(
					successfulAuthentication);
			// Continue with the Filters
			chain.doFilter(request, response);
		} catch (AuthenticationException authenticationException) {
			// If it fails clear this threads context and kick off the
			// authentication entry point process.
			SecurityContextHolder.clearContext();
			authenticationEntryPoint.commence(request, response,
					authenticationException);
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

}
