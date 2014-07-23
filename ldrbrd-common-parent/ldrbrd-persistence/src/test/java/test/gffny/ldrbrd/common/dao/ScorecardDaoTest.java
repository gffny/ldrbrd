/**
 * 
 */
package test.gffny.ldrbrd.common.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.Scorecard;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class ScorecardDaoTest {

	@Autowired
	private GenericDao<Scorecard> scorecardDao;

	Scorecard testScorecard;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testScorecard = new Scorecard();
		testScorecard.setId(0);
		Golfer golfer = new Golfer();
		golfer.setId(0);
		testScorecard.setGolfer(golfer);
		testScorecard.setCourseDocumentId("abcd-1234-efgh-5678");
		testScorecard.setRoundDate(new Date());
		testScorecard.setConditions("fair, breezy, sunny, humid");
		testScorecard.setHandicap(25);
		testScorecard.setScorecardNotes("played well");
	}

	@Test
	public void testPersistScorecard() {
		try {
			scorecardDao.persist(testScorecard);
		} catch (Exception dae) {
			dae.printStackTrace();
			fail(dae.getMessage());
		} finally {
			assertTrue(true);
		}
	}

	@Test
	public void testFindScorecardById() {
		try {
			Scorecard scorecard = scorecardDao.findById(Scorecard.class, 0);
			System.out.println(scorecard.toString());
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
