/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gffny.ldrbrd.rest.ctrl.AbstractController;
import com.gffny.ldrbrd.web.model.JSONable;
import com.gffny.ldrbrd.web.model.JsonResponse;
import com.gffny.ldrbrd.web.model.dto.UserDto;

/**
 * @author jdgaffney
 *
 */
@Controller
@RequestMapping("/check")
public class CheckController extends AbstractController{

	/**
	 * Check to see if the controller is online!
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/c_online", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<JSONable>> authenticateUserToken() {
		UserDto user = new UserDto();
		return returnSuccess(user, HttpStatus.OK);
	}
}
