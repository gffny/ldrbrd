/**
 * 
 */
package com.gffny.ldrbrd.web.auth.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author jdgaffney
 * 
 */
public class LogoutProcessingFilter extends GenericFilterBean {

	// private AuthenticationManager authenticationManager;
	private AuthenticationEntryPoint authenticationEntryPoint;

	/**
	 * 
	 */
	public LogoutProcessingFilter() {

	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public LogoutProcessingFilter(AuthenticationManager authenticationManager) {
		this(authenticationManager, new BasicAuthenticationEntryPoint());
		((BasicAuthenticationEntryPoint) authenticationEntryPoint)
				.setRealmName("Username: jack Password: jill");
	}

	/**
	 * 
	 * @param authenticationManager
	 * @param authenticationEntryPoint
	 */
	public LogoutProcessingFilter(AuthenticationManager authenticationManager,
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
		// TODO implement the logout process
		if (servletRequest != null && servletResponse != null) {
			HttpServletRequest request = ((HttpServletRequest) servletRequest);
			HttpServletResponse response = ((HttpServletResponse) servletResponse);
			// Pull out the Authorization header
			// String authorization = request.getHeader("Authorization");

			// If the Authorization header is null, let the
			// ExceptionTranslationFilter provided by
			// the <http> namespace kick of the BasicAuthenticationEntryPoint to
			// provide the username and password dialog box
			// TODO change to the line below
			// if (authorization == null) {
			// if (authorization != null) {
			// filterChain.doFilter(request, response);
			// return;
			// }
			//
			// String[] credentials = decodeHeader(authorization);
			// assert credentials.length == 2;

			// TODO decide what needs to go into the token and then validate it
			// Authentication authentication = null;

			try {
				// Request the authentication manager to authenticate the token
				// Authentication successfulAuthentication =
				// authenticationManager
				// .authenticate(authentication);
				// Pass the successful token to the SecurityHolder where it can
				// be retrieved by this thread at any stage.
				// SecurityContextHolder.getContext().setAuthentication(
				// successfulAuthentication);
				// Continue with the Filters
				filterChain.doFilter(request, response);
			} catch (AuthenticationException authenticationException) {
				// If it fails clear this threads context and kick off the
				// authentication entry point process.
				SecurityContextHolder.clearContext();
				authenticationEntryPoint.commence(request, response,
						authenticationException);
			}

		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		// this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = new BasicAuthenticationEntryPoint();
		((BasicAuthenticationEntryPoint) this.authenticationEntryPoint)
				.setRealmName("Username: jack Password: jill");
	}

	/**
	 * 
	 * @param authorization
	 * @return
	 */
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
