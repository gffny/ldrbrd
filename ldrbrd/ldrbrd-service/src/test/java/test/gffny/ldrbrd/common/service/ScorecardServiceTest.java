/**
 * 
 */
package test.gffny.ldrbrd.common.service;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.security.token.LdrbrdAuthenticationToken;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-service.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class ScorecardServiceTest {

	/** */
	@Autowired
	private IScorecardService scorecardService;

	/** */
	@Autowired
	private ICourseClubService courseClubService;

	/** */
	@Test
	public void testStartGeneralScorecard() throws AuthorisationException,
			ServiceException {
		// courseClubService
		Scorecard scorecard = scorecardService
				.startGeneralScorecard("53ef8c0d0364fc04b2d8ed1e");
		System.out.println(scorecard.toString());
	}

	/** */
	@Test
	public void testStartCompetitionScorecard() throws AuthorisationException,
			ServiceException {
		Golfer golfer = new Golfer();
		golfer.setId(1);
		LdrbrdAuthenticationToken lat = new LdrbrdAuthenticationToken(golfer,
				Arrays.asList(new GrantedAuthorityImpl("ROLE_ADMIN")));
		SecurityContextHolder.getContext().setAuthentication(lat);
		// courseClubService
		// TODO LOGGED IN USER
		Scorecard scorecard = scorecardService.startCompetitionScorecard("1",
				"1", "1", 1, null);
		System.out.println(scorecard.toString());
	}

	/** */
	@Test
	public void testGetScorecardById() throws AuthorisationException,
			ServiceException {
		// scorecardService.retriveScorecard("53ef8c0d0364fc04b2d8ed1e");
	}

	/** */
	@Test
	public void testScoreHole() throws AuthorisationException, ServiceException {
		scorecardService.scoreHole(2, 3, 3, null);
	}
}
