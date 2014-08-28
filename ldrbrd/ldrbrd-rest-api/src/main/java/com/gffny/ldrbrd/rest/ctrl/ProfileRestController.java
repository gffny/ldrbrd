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

import com.gffny.ldrbrd.common.exception.AuthorizationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.web.GolferDigestResponse;
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
			profileId = authorisationService.authorise(profileId);

			return new ResponseEntity<String>("Hello World", HttpStatus.OK);
		} catch (AuthorizationException ae) {
			LOG.error(ae.getMessage());
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		} catch (ServiceException se) {
			LOG.error(se.getMessage());
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/digest", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GolferDigestResponse> loadDigest(
			@RequestParam(value = "id", required = false) String profileId) {

		try {
			profileId = authorisationService.authorise(profileId);
			return new ResponseEntity<GolferDigestResponse>(
					profileService.getDigestById(profileId), HttpStatus.OK);
		} catch (AuthorizationException ae) {
			LOG.error(ae.getMessage());
			return new ResponseEntity<GolferDigestResponse>(HttpStatus.UNAUTHORIZED);
		} catch (ServiceException se) {
			LOG.error(se.getMessage());
			return new ResponseEntity<GolferDigestResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
