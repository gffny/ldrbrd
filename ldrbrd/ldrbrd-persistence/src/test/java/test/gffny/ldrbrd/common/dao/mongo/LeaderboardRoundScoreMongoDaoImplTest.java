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

import com.gffny.ldrbrd.common.dao.ILeaderboardRoundNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.mongo.RoundScore;
import com.gffny.ldrbrd.common.model.impl.mongo.PlayerLeaderboardRound;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class LeaderboardRoundScoreMongoDaoImplTest {

	private static final String ROUND_NUMBER = "1";
	private static final String COMPETITION_ID = "0001";
	private static final String PLAYER_ID = "0001";

	/** */
	@Autowired
	private ILeaderboardRoundNoSqlDao leaderboardRoundScoreMongoDaoImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		RoundScore lrs = new RoundScore(COMPETITION_ID, ROUND_NUMBER);
		/* JG PlayerLeaderboardRound */
		PlayerLeaderboardRound plr = new PlayerLeaderboardRound(PLAYER_ID, "J. Gaffney", "16",
				COMPETITION_ID, ROUND_NUMBER, 18, 0, 0, 0, 0);
		lrs.setPlayerLeaderboardRound(PLAYER_ID, plr);
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.dao.mongo.ClubMongoDaoImpl#listCourseByClub(java.lang.String)}
	 * .
	 * 
	 * @throws PersistenceException
	 */
	@Test
	public void testCompetitionRoundScoreInsert() throws PersistenceException {
		this.leaderboardRoundScoreMongoDaoImpl.scoreHole(COMPETITION_ID, ROUND_NUMBER, PLAYER_ID,
				5, 4, 0, 0, 2);
	}
}
