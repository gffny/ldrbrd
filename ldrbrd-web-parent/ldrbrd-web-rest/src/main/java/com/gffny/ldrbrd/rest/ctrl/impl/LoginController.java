/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.ldrbrd.common.service.impl.AuthorisationService;
import com.gffny.ldrbrd.rest.ctrl.AbstractController;

/**
 * @author John Gaffney | gffny.com
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
	 * Check to see if the controller is online!
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "loginusername")
	public ResponseEntity<String> loginWithUsername(HttpServletRequest request,
			String username, final String password) {

		// TODO May not be necessary to do this! Authentication is handled by
		// the server or something!

		try {

			// check something to do with authentication
			return new ResponseEntity<String>(new String("blah!"),
					HttpStatus.OK);
		} catch (UsernameNotFoundException unfex) {
			return new ResponseEntity<String>(unfex.getMessage(),
					HttpStatus.UNAUTHORIZED);
		} catch (Exception ex) {
			return new ResponseEntity<String>(ex.getLocalizedMessage(),
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
