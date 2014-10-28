/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.model.web.ScorecardSubmission;
import com.gffny.ldrbrd.common.model.web.StatusResponse;
import com.gffny.ldrbrd.common.service.IAuthorisationService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.utils.CollectionUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
@Controller
public class ScorecardRestController extends BaseRestController {

	/** */
	@Autowired
	private IScorecardService scorecardService;

	/** */
	@Autowired
	private IAuthorisationService authorisationService;

	/**
	 *  
	 */
	public ScorecardRestController() {
		super(ScorecardRestController.class);
	}

	/**
	 * @return
	 */
	@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/scorecard/start", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Scorecard> startScorecardWithCourseId(
			@RequestParam(required = true) final String courseId) {

		try {
			return new ResponseEntity<Scorecard>(scorecardService.startGeneralScorecard(courseId),
					HttpStatus.OK);
		} catch (AuthorisationException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (ServiceException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @return
	 */
	@Transactional(value = "ldrbrd_txnMgr", propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/scorecard/scoreHole", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<StatusResponse> scoreHole(
			@RequestParam(required = true) final int scorecardId,
			@RequestParam(required = false) final String holeId,
			@RequestParam(required = true) final int holeNumber,
			@RequestParam(required = true) final int holeScore) {
		try {
			// TODO create a return class for scoreHoleArray that will have the scores for a
			// competition or general (to par, stapleford, etc)
			scorecardService.scoreHole(scorecardId, holeNumber, holeScore, holeId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AuthorisationException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (ServiceException e) {
			LOG.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param scorecard
	 * @param scorecardId
	 */
	@RequestMapping(value = "/scorecard/submit", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<StatusResponse> submitScorecard(
			@RequestBody final ScorecardSubmission scorecardSubmission) {
		if (CollectionUtils.safeSize(scorecardSubmission.getScorecardArray()) > 0
				&& StringUtils.isNotBlank(scorecardSubmission.getScorecardId())) {
			LOG.debug("Array Size: "
					+ CollectionUtils.safeSize(scorecardSubmission.getScorecardArray()));
			try {
				scorecardService.scoreHoleArray(scorecardSubmission.getScorecardId(),
						scorecardSubmission.getScorecardArray());
				// TODO create a return class for scoreHoleArray that will have the scores for a
				// competition or general (to par, stapleford, etc)
				return new ResponseEntity<StatusResponse>(new StatusResponse("good",
						String.valueOf(0)), HttpStatus.OK);
			} catch (AuthorisationException e) {
				LOG.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} catch (ServiceException e) {
				LOG.error(e.getMessage());
				return new ResponseEntity<StatusResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<StatusResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param scorecard
	 * @param scorecardId
	 */
	@RequestMapping(value = "/scorecard/sign", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<StatusResponse> signScorecard(
			@RequestBody final Map<String, String> scorecardIdMap) {
		// check param validity
		LOG.debug("signing scorecard");
		if (scorecardIdMap != null && scorecardIdMap.get("scorecardId") != null) {
			try {
				scorecardService.signScorecard(scorecardIdMap.get("scorecardId"));
			} catch (ServiceException e) {
				LOG.error(e.getMessage());
				// check what the error is (might be a 500 or a 400)
				return new ResponseEntity<StatusResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<StatusResponse>(HttpStatus.OK);
	}
}
