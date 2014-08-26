/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gffny.ldrbrd.common.exception.AuthorizationException;
import com.gffny.ldrbrd.common.service.IAuthorisationService;
import com.gffny.ldrbrd.common.service.IUserProfileService;

/**
 * @author John D. Gaffney | Isobar US
 */
@Controller
public class ProfileRestController extends BaseRestController {

	/** */
	@Autowired
	private IUserProfileService profileService;

	/** */
	@Autowired
	private IAuthorisationService authorisationService;

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
	@RequestMapping(value = "/health", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<String>("alive and kickin'", HttpStatus.OK);
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/profile", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> loadProfile(
			@RequestParam(value = "id", required = false) String profileId) {

		try {
			authorisationService.authorise(profileId);
			if (StringUtils.isEmpty(profileId)) {
				// if the logged in user is a golfer (ROLE_GOLFER)

				// if the logged in user is not a golfer

			} else {
				// authorise the current user to view this profile

			}
			return new ResponseEntity<String>("Hello World", HttpStatus.OK);
		} catch (AuthorizationException ae) {
			LOG.error(ae.getMessage());
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
	}
}
