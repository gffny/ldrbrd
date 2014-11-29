/**
 * 
 */
package test.gffny.ldrbrd.common.dao.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.nosql.ILeaderboardNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class LeaderboardMongoDaoImplTest {

	// private static final String ROUND_NUMBER = "1";
	// private static final String COMPETITION_ID = "0001";
	// private static final String PLAYER_ID = "0001";

	/** */
	@Autowired
	private ILeaderboardNoSqlDao leaderboardMongoDaoImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Test method for {@link
	 * com.gffny.ldrbrd.common.dao.nosql.ILeaderboardNoSqlDao#} .
	 * 
	 * @throws PersistenceException
	 */
	@Test
	public void testCompetitionRoundScoreInsert() throws PersistenceException {
	}
}
