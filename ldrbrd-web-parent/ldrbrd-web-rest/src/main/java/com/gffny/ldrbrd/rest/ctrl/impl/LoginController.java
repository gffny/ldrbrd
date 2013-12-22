/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.ldrbrd.common.model.impl.LeaderboardUserDetails;
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
public class LoginController extends AbstractController{
	
	/**
	 * 
	 */
	@Autowired
	private AuthorisationService authorisationService;

	/**
	 * Check to see if the controller is online!
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "loginusername")//, method = RequestMethod.POST)
	public ResponseEntity<JsonResponse<JSONable>> loginWithUsername(String username, final String password) {
		
		if(username == null) {
			username = "gffny";
		}
		UserDto userDto = new UserDto();
		try {
			userDto.setUser((LeaderboardUserDetails)authorisationService.loadUserByUsername(username));
			return returnSuccess(userDto, HttpStatus.OK);
		} catch (UsernameNotFoundException unfex) {
			return returnError(unfex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
		} catch (Exception ex) {
			return returnError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

}
