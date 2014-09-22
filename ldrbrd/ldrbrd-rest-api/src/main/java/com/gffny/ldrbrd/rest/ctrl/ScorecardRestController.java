/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl;

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

	/**
	 * @param clas
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
			int totalScore = 0;
			for (int i = 0; i < scorecardSubmission.getScorecardArray().length; i++) {
				totalScore += scorecardSubmission.getScorecardArray()[i];
			}
			return new ResponseEntity<StatusResponse>(new StatusResponse("gppd",
					String.valueOf(totalScore)), HttpStatus.OK);
		}
		return new ResponseEntity<StatusResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
