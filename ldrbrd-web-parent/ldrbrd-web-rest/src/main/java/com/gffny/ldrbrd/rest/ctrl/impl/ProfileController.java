/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.common.service.IGolfClubService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.service.IUserProfileService;
import com.gffny.ldrbrd.rest.ctrl.AbstractController;
import com.gffny.ldrbrd.rest.resp.GolferDigestResponse;

/**
 * @author jdgaffney
 * 
 */
@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(ProfileController.class);

	@Autowired
	private IUserProfileService profileService;

	@Autowired
	private IGolfClubService golfClubService;

	@Autowired
	private ICourseClubService courseClubService;

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private IScorecardService scorecardService;

	private Object principal;

	private String profileHandle;

	/**
	 * 
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<GolferProfile> getGolferProfile() {

		profileHandle = getProfileHandle();
		if (profileHandle != null) {
			try {
				GolferProfile profile = profileService
						.getGolferByHandle(profileHandle);
				if (profile != null) {
					return new ResponseEntity<GolferProfile>(profile,
							HttpStatus.OK);
				}
				LOG.debug(
						"No user with the profile handle {} is registered or is unavailable",
						profileHandle);
				return new ResponseEntity<GolferProfile>(HttpStatus.BAD_REQUEST);

			} catch (ServiceException srvEx) {
				LOG.error(srvEx.getMessage());
				return new ResponseEntity<GolferProfile>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			LOG.error("No user was returned from the security context. Unable to complete request!");
			return new ResponseEntity<GolferProfile>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get the digest of the golfer data including profile, favourite courses,
	 * upcoming competitions, upcoming non-competition rounds, and the last X
	 * scorecards (based on golfer preference)
	 * 
	 * @param profileRequest
	 * @return
	 */
	@RequestMapping(value = "/digest", method = RequestMethod.GET)
	public ResponseEntity<GolferDigestResponse> getGolferDigest() {

		profileHandle = getProfileHandle();
		if (profileHandle != null) {
			try {
				GolferProfile profile = getUser();
				if (profile == null) {
					profile = profileService.getGolferByHandle(profileHandle);
				}
				if (profile != null) {
					// create the response object instance
					GolferDigestResponse response = new GolferDigestResponse(
							profile);
					// add the golfer's favourite courses

					// TODO two favourite course lists being generated!
					response.setFavouriteCourseList(courseClubService
							.getFavouriteCourseList(profile.getId()));

					// add the golfer's upcoming events
					response.setUpcomingCompetitionRegistrationList(competitionService
							.getCompetitionListForGolfer(profile.getId()));

					// TODO make this a non - competition round somehow
					response.setUpcomingNonCompetitionRoundList(new ArrayList<CompetitionRound>());

					// add the golfer's last X scorecards (based preferences)
					// TODO add this number X to the golfer profile
					response.setLastXScorecardList(scorecardService
							.getLastXScorecards(profile.getId(), 5));

					// TODO set the data and start using it :) also look at the
					// implementation of the last two methods called here!

					return new ResponseEntity<GolferDigestResponse>(response,
							HttpStatus.OK);
				}
				LOG.debug(
						"No user with the profile handle {} is registered or is unavailable",
						profileHandle);
				return new ResponseEntity<GolferDigestResponse>(
						HttpStatus.BAD_REQUEST);

			} catch (ServiceException srvEx) {
				LOG.error(srvEx.getMessage());
				return new ResponseEntity<GolferDigestResponse>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			LOG.error("No user was returned from the security context. Unable to complete request!");
			return new ResponseEntity<GolferDigestResponse>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 */
	private String getProfileHandle() {
		String profileHandle = (String) (principal = SecurityContextHolder
				.getContext().getAuthentication().getName());
		if ((String) principal == "anonymousUser") {
			// which it will be until authentication is sorted (most likely)
			profileHandle = "gffny";
		}
		return profileHandle;
	}
}
