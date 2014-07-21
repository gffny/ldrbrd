/**
 * 
 */
package test.gffny.ldrbrd.common.persistence;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.service.IUserProfileService;

/**
 * @author John Gaffney | gffny.com
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class ScorecardDaoTest {

	@Autowired
	private IScorecardService scorecardService;

	@Autowired
	private IUserProfileService golferProfileService;

	GolferProfile testProfile;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testProfile = golferProfileService.getGolferByHandle("gffny");
	}

	@Test
	public void test() throws DataAccessException, ServiceException {
		if (testProfile != null) {
			List<Scorecard> scorecardList = scorecardService
					.getLastXScorecards(testProfile.getId(), 5);
			Scorecard scorecardPrevious = null;
			for (Scorecard scorecard : scorecardList) {
				if (scorecardPrevious != null) {
					assert (scorecard.getScorecardDateDT()
							.isAfter(scorecardPrevious.getCreatedDateDT()));
				}
				scorecardPrevious = scorecard;
			}
		}

	}
}
