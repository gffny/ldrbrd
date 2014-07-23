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
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionEntry;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.CompetitionRoundScore;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.Scorecard;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class CompetitionDaoTest {

	/** */
	@Autowired
	private GenericDao<Competition> competitionDao;

	/** */
	@Autowired
	private GenericDao<CompetitionRound> competitionRoundDao;

	/** */
	@Autowired
	private GenericDao<CompetitionEntry> competitionEntryDao;

	/** */
	@Autowired
	private GenericDao<CompetitionRoundScore> competitionRoundScoreDao;

	Competition testCompetition;

	CompetitionRound testCompetitionRound;

	CompetitionEntry testCompetitionEntry;

	CompetitionRoundScore testCompetitionRoundScore;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testCompetition = new Competition();
		testCompetition.setId(0);
		testCompetition.setCompetitionName("Test Competition");
		testCompetition.setCompetitorLimit(20);
		testCompetition.setStartDate(new Date());

		testCompetitionRound = new CompetitionRound();
		testCompetitionRound.setCompetition(testCompetition);
		testCompetitionRound.setStartDate(new Date());
		testCompetitionRound.setRoundNumber(1);
		testCompetitionRound.setTeeTime(new Date());
		testCompetitionRound.setCourseDocumentId("abcd-1234-efgh-5678");
		testCompetitionRound.setScoringFormat(1);

		testCompetitionEntry = new CompetitionEntry();
		testCompetitionEntry.setCompetition(testCompetition);
		Golfer testGolfer = new Golfer();
		testGolfer.setId(0);
		testCompetitionEntry.setGolfer(testGolfer);
		testCompetitionEntry.setEntryDate(new Date());

		testCompetitionRoundScore = new CompetitionRoundScore();
		testCompetitionRoundScore.setCompetitionEntry(testCompetitionEntry);
		Scorecard scorecard = new Scorecard();
		scorecard.setId(0);
		testCompetitionRoundScore.setScorecard(scorecard);
	}

	/**
	 * 
	 */
	@Test
	public void testPersistCompetition() {
		try {
			competitionDao.persist(testCompetition);
		} catch (Exception dae) {
			dae.printStackTrace();
			fail(dae.getMessage());
		} finally {
			assertTrue(true);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testPersistCompetitionRound() {
		try {
			competitionRoundDao.persist(testCompetitionRound);
		} catch (Exception dae) {
			dae.printStackTrace();
			fail(dae.getMessage());
		} finally {
			assertTrue(true);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testPersistCompetitionEntry() {
		try {
			competitionEntryDao.persist(testCompetitionEntry);
		} catch (Exception dae) {
			dae.printStackTrace();
			fail(dae.getMessage());
		} finally {
			assertTrue(true);
		}
	}

	@Test
	public void testPersistCompetitionRoundScore() {
		try {
			competitionRoundScoreDao.persist(testCompetitionRoundScore);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			assertTrue(true);
		}
	}

	@Test
	@Transactional
	public void testGetCompetitionRoundScore() {
		try {
			CompetitionEntry competitionEntry = competitionEntryDao.findById(
					CompetitionEntry.class, 0);
			for (Scorecard scorecard : competitionEntry.getEntryScorecardList()) {
				System.out.println(scorecard.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			assertTrue(true);
		}
	}
}
