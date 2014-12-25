/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.web.GolferDigestResponse;
import com.gffny.ldrbrd.common.service.IUserProfileService;

/**
 * @author John D. Gaffney | Isobar US
 */
@Controller
public class ProfileRestController extends BaseRestController {

	/** */
	@Autowired
	private IUserProfileService profileService;

	/** TODO MOVE THE HEALTH METHOD TO ANOTHER CONTROLLER */

	/**
	 * default zero-argument constructor
	 */
	public ProfileRestController() {
		// Initializing logging in super class;
		super(ProfileRestController.class);
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/profile", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> loadProfile(
			@RequestParam(value = "id", required = false) String profileId) {
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/profile/digest", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GolferDigestResponse> loadDigest(
			@RequestParam(value = "id", required = false) String profileId) {

		try {
			return new ResponseEntity<GolferDigestResponse>(
					profileService.getLoggedInGolferDigest(), HttpStatus.OK);
		} catch (AuthorisationException ae) {
			LOG.error(ae.getMessage());
			return new ResponseEntity<GolferDigestResponse>(
					HttpStatus.UNAUTHORIZED);
		} catch (ServiceException se) {
			LOG.error(se.getMessage());
			return new ResponseEntity<GolferDigestResponse>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
