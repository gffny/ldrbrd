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
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.service.impl.ScorecardService;

/**
 * @author John D. Gaffney | gffny.com
 */
@Controller
public class ScorecardRestController extends BaseRestController {

	/** */
	@Autowired
	private ScorecardService scorecardService;

	/**
	 * @param clas
	 */
	public ScorecardRestController() {
		super(ScorecardRestController.class);
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/startscorecard", produces = { MediaType.APPLICATION_JSON_VALUE,
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
}
