/**
 * 
 */
package com.gffny.ldrbrd.web.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author jdgaffney
 * 
 */
public class BasicAuthenticationProcessingFilter extends GenericFilterBean {

	// TODO subclass one of the other custom filters

	// private AuthenticationManager authenticationManager;
	private AuthenticationEntryPoint authenticationEntryPoint;

	/**
	 * 
	 */
	public BasicAuthenticationProcessingFilter() {
		this.authenticationEntryPoint = new BasicAuthenticationEntryPoint();
		((BasicAuthenticationEntryPoint) authenticationEntryPoint)
				.setRealmName("Username: jack Password: jill");
	}

	/**
	 * 
	 * @param authenticationManager
	 */
	public BasicAuthenticationProcessingFilter(
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
	public BasicAuthenticationProcessingFilter(
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
		// TODO implement this method taking a uname/password from the request,
		// authenticating, and generate a token for the response
		if (servletRequest != null && servletResponse != null) {
			HttpServletRequest request = ((HttpServletRequest) servletRequest);
			HttpServletResponse response = ((HttpServletResponse) servletResponse);

			// Try to authenticate
			try {

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
	}

}
