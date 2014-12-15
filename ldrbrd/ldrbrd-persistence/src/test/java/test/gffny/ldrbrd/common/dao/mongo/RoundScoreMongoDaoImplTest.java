/**
 * 
 */
package test.gffny.ldrbrd.common.dao.mongo;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.nosql.IRoundScoreNoSqlDao;
import com.gffny.ldrbrd.common.model.nosql.RoundScore;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class RoundScoreMongoDaoImplTest {

	/** */
	@Autowired
	private IRoundScoreNoSqlDao leaderboardRoundScoreMongoDaoImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	// @Test
	public void testTestStartCompetitionRound() throws Exception {
		// RoundScore rs = RoundScore.instance("1", "1");
		// rs.setCompetitionId("1");
		// rs.setRoundNumber(1);
		// leaderboardRoundScoreMongoDaoImpl.persist(rs);
	}

	@Test
	public void testFindGolferRoundScoreByCompetitionRound() throws Exception {
		RoundScore rs = leaderboardRoundScoreMongoDaoImpl
				.findGolferRoundScoreByCompetitionRound("1", "1");
		assertNotNull(rs);
	}

}
