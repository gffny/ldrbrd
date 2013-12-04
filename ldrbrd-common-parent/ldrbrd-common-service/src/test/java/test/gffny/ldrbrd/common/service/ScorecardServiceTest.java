/**
 * 
 */
package test.gffny.ldrbrd.common.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.service.impl.ScorecardService;

/**
 * @author jdgaffney
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class ScorecardServiceTest {

	@Autowired
	private ScorecardService scorecardService;

	private GolferProfile golferProfile;
	private List<GolfClub> golfbag;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testStartScorecard() {
		// startScorecard w/ scorerId, scoredId, courseId, handicap,
		// conditionsMap, golfBagMap
		String scorerId = "scorerId";
		String scoredId = "scoredId";
		String courseId = "courseId";
		String competitionId = "competitionId";
		Integer roundNumber = 1;

		Scorecard scorecard = scorecardService.startGeneralScorecard(scoredId,
				courseId, 18, new HashMap<String, String>(),
				new LinkedList<GolfClub>());
		scorecard = scorecardService.startGeneralScorecard(scoredId, courseId,
				new HashMap<String, String>(), new LinkedList<GolfClub>());

		scorecard = scorecardService.startCompetitionScorecard(scorerId,
				scoredId, competitionId, roundNumber,
				new LinkedList<GolfClub>());

	}
}
