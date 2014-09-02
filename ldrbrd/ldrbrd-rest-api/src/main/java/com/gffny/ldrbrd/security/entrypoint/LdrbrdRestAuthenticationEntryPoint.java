package com.gffny.ldrbrd.security.entrypoint;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author John D. Gaffney | gffny.com
 */
@Component("ldrbrdRestAuthenticationEntryPoint")
public class LdrbrdRestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * overiding the behaviour so requests to secured resources that are not authenticated return an
	 * unauthorised response and fail rather than redirect (non-Javadoc)
	 * baeldung.com/2011/10/31/securing-a-restful-web-service-with-spring-security-3-1-part-3/
	 * 
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}