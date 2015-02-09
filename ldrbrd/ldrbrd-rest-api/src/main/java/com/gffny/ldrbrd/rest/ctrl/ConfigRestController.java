/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.ldrbrd.common.model.web.StatusResponse;

/**
 * @author John D. Gaffney | Isobar US
 */
@Controller
public class ConfigRestController extends BaseRestController {

	/**
	 * default zero-argument constructor
	 */
	public ConfigRestController() {
		// Initializing logging in super class;
		super(ConfigRestController.class);
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/health", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<StatusResponse> healthCheck() {

		return new ResponseEntity<StatusResponse>(new StatusResponse("200",
				"alive and kickin'"), HttpStatus.OK);
	}
}
