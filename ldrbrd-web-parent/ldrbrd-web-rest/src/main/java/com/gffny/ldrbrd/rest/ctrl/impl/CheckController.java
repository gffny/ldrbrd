/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gffny.ldrbrd.rest.ctrl.AbstractController;
import com.gffny.ldrbrd.rest.resp.StatusResponse;
import com.gffny.ldrbrd.rest.resp.StatusResponse.StatusCode;

/**
 * @author John Gaffney | gffny.com
 * 
 */
@Controller
@RequestMapping("/check")
public class CheckController extends AbstractController {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(CheckController.class);

	/**
	 * Check to see if the controller is online!
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/c_online", method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<StatusResponse> applicationOnlineCheck() {
		return new ResponseEntity<StatusResponse>(new StatusResponse(
				StatusCode.SUCCESS, "Online"), HttpStatus.OK);

	}

	/**
	 * 
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/c_password", method = RequestMethod.GET)
	public ResponseEntity<String> applicationPasswordCheck(final String password) {
		LOG.debug("encoding password: {}", password);
		PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		return new ResponseEntity<String>(encodedPassword, HttpStatus.OK);
	}
}
