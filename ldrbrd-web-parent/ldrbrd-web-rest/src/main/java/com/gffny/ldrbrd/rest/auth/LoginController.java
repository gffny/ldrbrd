/**
 * 
 */
package com.gffny.ldrbrd.rest.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gffny.ldrbrd.rest.AbstractController;
import com.gffny.ldrbrd.web.model.JSONable;
import com.gffny.ldrbrd.web.model.JsonResponse;
import com.gffny.ldrbrd.web.model.dto.UserDto;

/**
 * @author jdgaffney
 *
 */
@Controller
@RequestMapping("/auth")
public class LoginController extends AbstractController{

	/**
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<JSONable>> authenticateUserToken() {
		UserDto user = new UserDto();
		user.setProfileHandle("GFFNY");
		user.setPassword("SUCCESS");
		return returnSuccess(user, HttpStatus.OK);
	}
}
