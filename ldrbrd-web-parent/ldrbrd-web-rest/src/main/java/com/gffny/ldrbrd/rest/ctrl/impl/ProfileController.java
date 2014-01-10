/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gffny.ldrbrd.rest.ctrl.AbstractController;
import com.gffny.ldrbrd.rest.req.ProfileRequest;
import com.gffny.ldrbrd.web.model.JSONable;
import com.gffny.ldrbrd.web.model.JsonResponse;

/**
 * @author jdgaffney
 * 
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	/**
	 * Check to see if the controller is online!
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<JSONable>> applicationOnlineCheck(
			@RequestBody final ProfileRequest profileRequest) {

		return returnSuccess(HttpStatus.OK);
	}
}
