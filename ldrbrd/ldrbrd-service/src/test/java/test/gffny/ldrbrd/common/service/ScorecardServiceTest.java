/**
 * 
 */
package test.gffny.ldrbrd.common.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.exception.AuthorizationException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.service.impl.CourseClubService;
import com.gffny.ldrbrd.common.service.impl.ScorecardService;

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
	private ScorecardService scorecardService;

	/** */
	@Autowired
	private CourseClubService courseClubService;

	/** */
	@Test
	public void testStartGeneralScorecard() throws AuthorizationException, ServiceException {
		// courseClubService
		Scorecard scorecard = scorecardService.startGeneralScorecard("53ef8c0d0364fc04b2d8ed1e");
		System.out.println(scorecard.toString());
	}

	/** */
	@Test
	public void testGetScorecardById() throws AuthorizationException, ServiceException {
		// scorecardService.retriveScorecard("53ef8c0d0364fc04b2d8ed1e");
	}

	/** */
	@Test
	public void testScoreHole() throws ServiceException {
		scorecardService.scoreHole(0, 10, 5);
	}
}
