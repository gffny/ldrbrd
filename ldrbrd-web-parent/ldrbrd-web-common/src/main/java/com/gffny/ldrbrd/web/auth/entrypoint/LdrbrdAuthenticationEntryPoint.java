/**
 * 
 */
package com.gffny.ldrbrd.web.auth.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author jdgaffney
 * 
 */
public class LdrbrdAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.security.core.AuthenticationException)
	 */
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"Unauthorized: Authentication token was either missing or invalid.");
	}

}
