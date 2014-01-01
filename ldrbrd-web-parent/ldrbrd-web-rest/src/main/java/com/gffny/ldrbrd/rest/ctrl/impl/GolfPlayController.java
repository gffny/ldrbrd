/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.service.impl.CourseClubService;
import com.gffny.ldrbrd.rest.ctrl.AbstractController;
import com.gffny.ldrbrd.rest.req.HoleScoreMap;
import com.gffny.ldrbrd.rest.req.ScorecardRequest;
import com.gffny.ldrbrd.rest.resp.ScorecardResponse;
import com.gffny.ldrbrd.web.model.JSONable;
import com.gffny.ldrbrd.web.model.JsonResponse;

/**
 * @author jdgaffney
 *
 */
@Controller
@RequestMapping("/playgolf")
public class GolfPlayController extends AbstractController{
	
	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(CourseClubService.class);

	@Autowired
	private ICompetitionService competitionService;
	
	@Autowired
	private IScorecardService scorecardService;

	//1) start score card
	/**
	 * Check to see if the controller is online!
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/competitionscorecard")//, method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<JSONable>> startCompetitionScorecard(@RequestBody final ScorecardRequest scorecardRequest) {
		
		//create response object 
		ScorecardResponse response = new ScorecardResponse();
		
		//check for parameter validity
		if(null != scorecardRequest && StringUtils.isNotEmpty(scorecardRequest.getCompetitionId()) && StringUtils.isNotEmpty(scorecardRequest.getScoreKeeperId())) {
			
			//create golfer/scorecard map
			Map<String, String> golferScorecardMap = new HashMap<String, String>();

			//populate the map
			golferScorecardMap.put(scorecardRequest.getScoreKeeperId(), scorecardService.startCompetitionScorecard(scorecardRequest.getScoreKeeperId(), 
					scorecardRequest.getScoreKeeperId(), scorecardRequest.getCompetitionId(), scorecardRequest.getRoundNumber(), null).getId());
			//check if there are other golfers (which there should be from a business logic perspective)
			if(ArrayUtils.isNotEmpty(scorecardRequest.getGolferArray())) {
				for(String golferId : scorecardRequest.getGolferArray()) {
					golferScorecardMap.put(golferId, scorecardService.startCompetitionScorecard(golferId, scorecardRequest.getScoreKeeperId(),
							scorecardRequest.getCompetitionId(), scorecardRequest.getRoundNumber(), null).getId());
				}
			} else {
				LOG.error("no other golfers other than the scorer in the group");
			}
			//set the response map value
			response.setCompetitionId(scorecardRequest.getCompetitionId());
			response.setGolferScorecardMap(golferScorecardMap);
			
			//return the response with a success code
			return returnSuccess(response, HttpStatus.OK);
		}
		LOG.error("scorecard request is null or scoring golfer id/competition id is null");
		return returnError("scorecard request is null or scoring golfer id/competition id is null", HttpStatus.OK);
	}

	//1) start score card
	/**
	 * Check to see if the controller is online!
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/noncompetitionscorecard")//, method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<JSONable>> startNonCompetitionScorecard(@RequestBody final ScorecardRequest scorecardRequest) {
		//create response object 
		ScorecardResponse response = new ScorecardResponse();
		
		//check for parameter validity
		if(null != scorecardRequest && StringUtils.isNotEmpty(scorecardRequest.getCompetitionId()) && StringUtils.isNotEmpty(scorecardRequest.getScoreKeeperId())) {
			
			//create golfer/scorecard map
			Map<String, String> golferScorecardMap = new HashMap<String, String>();

			//populate the map
			golferScorecardMap.put(scorecardRequest.getScoreKeeperId(), scorecardService.startGeneralScorecard(scorecardRequest.getScoreKeeperId(), 
					scorecardRequest.getCourseId(), null, null).getId());
			//check if there are other golfers (which there should be from a business logic perspective)
			if(ArrayUtils.isNotEmpty(scorecardRequest.getGolferArray())) {
				for(String golferId : scorecardRequest.getGolferArray()) {
					golferScorecardMap.put(golferId, scorecardService.startGeneralScorecard(golferId, scorecardRequest.getScoreKeeperId(),
							scorecardRequest.getCourseId(), null, null).getId());
				}
			} else {
				LOG.error("no other golfers other than the scorer in the group");
			}
			//set the response map value
			response.setCompetitionId(scorecardRequest.getCompetitionId());
			response.setGolferScorecardMap(golferScorecardMap);
			
			//return the response with a success code
			return returnSuccess(response, HttpStatus.OK);
		}
		LOG.error("scorecard request is null or scoring golfer id/competition id is null");
		return returnError("scorecard request is null or scoring golfer id/competition id is null", HttpStatus.OK);
	}

	//4) series of holes recorded 
	//	[ HoleInformation ]
	/**
	 * Check to see if the controller is online!
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/holescorelist")//, method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<JSONable>> scoreHoleList(@RequestBody final ScorecardRequest scorecardRequest) {
		// check if the request is valid
		if(null != scorecardRequest && null != scorecardRequest.getScorecardHoleScoreMap()) {
			// get the set of keys for the scorecard map
			Set<String> keySet = scorecardRequest.getScorecardHoleScoreMap().keySet();
			// traverse key set to check each scorecard in the scorecard map
			for(String key : keySet) {
				HoleScoreMap holeScoreMap = scorecardRequest.getScorecardHoleScoreMap().get(key);
				// check if score map is valid (for the basic score)
				if(holeScoreMap.getScorecardScoreHoleMap() != null) {
					// check if score map is valid (for the shot map)
					if(holeScoreMap.doesIncludeShots() && holeScoreMap.getScorecardScoreShotMap() != null) {
						// score shot map
						scorecardService.scoreShotMap(key, holeScoreMap.getScorecardScoreShotMap());
						// TODO potentially score this to a redis server for the "shot analysis"
					}
					//score hole map
					scorecardService.scoreHoleScoreMap(key, holeScoreMap.getScorecardScoreHoleMap());
					// TODO potentially score this to a redis server for the "live scoreboard"
				}
			}
		} else {
			LOG.error("request not valid");
			return returnError("request not valid", HttpStatus.BAD_REQUEST);
		}
		return returnSuccess(HttpStatus.OK);
	}

	//n-1) submit card
	/**
	 * Check to see if the controller is online!
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/submitscorecard")//, method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<JSONable>> submitScorecard(@RequestBody final ScorecardRequest scorecardRequest) {
		if(scorecardRequest.getCompetitionId() != null && scorecardRequest.getScoreKeeperId() != null && scorecardRequest.getScorecardArray() != null) {
			for (String scorecardId : scorecardRequest.getScorecardArray()) {
				if(scorecardService.checkScorecardScoreKeeper(scorecardId, scorecardRequest.getScoreKeeperId())) {
					scorecardService.submitScorecard(scorecardId, scorecardRequest.getScoreKeeperId(), scorecardRequest.getCompetitionId());
				}
			}
			return returnSuccess(HttpStatus.OK);
		}
		LOG.error("error with the request parameters");
		return returnError("error with the request parameters", HttpStatus.BAD_REQUEST);
	}

	//n) sign card
	/**
	 * Check to see if the controller is online!
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/signscorecard")//, method = RequestMethod.GET)
	public ResponseEntity<JsonResponse<JSONable>> signScorecard(@RequestBody final ScorecardRequest scorecardRequest) {
		if(scorecardRequest.getCompetitionId() != null && scorecardRequest.getScoreKeeperId() != null && scorecardRequest.getScorecardArray() != null) {
			for (String scorecardId : scorecardRequest.getScorecardArray()) {
				if(scorecardService.checkScorecardScoreKeeper(scorecardId, scorecardRequest.getScoreKeeperId())) {
					scorecardService.signScorecard(scorecardId, scorecardRequest.getScoreKeeperId(), scorecardRequest.getCompetitionId());
				}
			}
			return returnSuccess(HttpStatus.OK);
		}
		LOG.error("error with the request parameters");
		return returnError("error with the request parameters", HttpStatus.BAD_REQUEST);
	}
}
