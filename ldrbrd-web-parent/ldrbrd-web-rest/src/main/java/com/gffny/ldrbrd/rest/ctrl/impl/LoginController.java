/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.ldrbrd.common.service.impl.AuthorisationService;
import com.gffny.ldrbrd.rest.ctrl.AbstractController;
import com.gffny.ldrbrd.web.model.JSONable;
import com.gffny.ldrbrd.web.model.JsonResponse;
import com.gffny.ldrbrd.web.model.dto.UserDto;

/**
 * @author jdgaffney
 * 
 */
@Controller
@RequestMapping("/auth")
public class LoginController extends AbstractController {

	/**
	 * 
	 */
	@Autowired
	private AuthorisationService authorisationService;

	/**
	 * 
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * 
	 */
	private UserCache userCache = new NullUserCache();

	/**
	 * 
	 */
	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

	/**
	 * Check to see if the controller is online!
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "loginusername")
	public ResponseEntity<JsonResponse<JSONable>> loginWithUsername(
			HttpServletRequest request, String username, final String password) {

		UserDto userDto = new UserDto();
		try {

			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					username, password);
			authenticationDetailsSource.buildDetails(request);
			authRequest.setDetails(authenticationDetailsSource
					.buildDetails(request));

			// check if userDetails is null!
			Authentication authentication = authenticationManager
					.authenticate(authRequest);

			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			// check something to do with authentication
			return returnSuccess(userDto, HttpStatus.OK);
		} catch (UsernameNotFoundException unfex) {
			return returnError(unfex.getLocalizedMessage(),
					HttpStatus.UNAUTHORIZED);
		} catch (Exception ex) {
			return returnError(ex.getLocalizedMessage(),
					HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout")
	public ResponseEntity<String> logout() {

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		// set the context authentication to null?
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ResponseEntity<String>(username, HttpStatus.OK);
	}
}
