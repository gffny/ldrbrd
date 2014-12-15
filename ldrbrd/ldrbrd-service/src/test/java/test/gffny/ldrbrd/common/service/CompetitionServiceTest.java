/**
 * 
 */
package test.gffny.ldrbrd.common.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionEntry;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.service.impl.CompetitionService;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-service.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class CompetitionServiceTest {

	/** */
	@Autowired
	private CompetitionService competitionService;

	/** */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<Golfer> personDao;

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#createCompetition(java.lang.String)}
	 * .
	 */
	@Test
	public void testCreateCompetition() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#createCompetitionRound(com.gffny.ldrbrd.common.model.impl.Competition, org.joda.time.DateTime, java.lang.Integer, com.gffny.ldrbrd.common.model.nosql.Course)}
	 * .
	 */
	@Test
	public void testCreateCompetitionRound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#registerGolferForCompetition(com.gffny.ldrbrd.common.model.impl.Golfer, com.gffny.ldrbrd.common.model.impl.Competition)}
	 * .
	 * 
	 * @throws ServiceException
	 * @throws PersistenceException
	 */
	@Test
	public void testRegisterGolferForCompetition() throws ServiceException,
			PersistenceException {
		Competition competition = competitionService.getCompetitionById("1");
		Golfer golfer = personDao.findById(Golfer.class, 1);
		assertNotNull(golfer);
		assertNotNull(competition);
		// CompetitionEntry competitionEntry = competitionService
		// .registerGolferForCompetition(golfer, competition);
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#getCompetitionById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCompetitionById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#getCompetitionByName(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCompetitionByName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#getCompetitionRound(java.lang.String, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetCompetitionRound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#getCompetitionListForGolfer(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCompetitionListForGolfer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#getCompetitionRegistrationForGolfer(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws ServiceException
	 */
	@Test
	public void testGetCompetitionRegistrationForGolfer()
			throws ServiceException {
		CompetitionEntry competitionEntry = competitionService
				.getCompetitionRegistrationForGolfer("1", "1");
		assertNotNull(competitionEntry);
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#getCompetitionRoundByScorecardId(String, String)}
	 * 
	 * @throws ServiceException
	 */
	@Test
	public void testGetCompetitionRoundByScorecardIdAndRoundNumber()
			throws ServiceException {
		CompetitionRound competitionRound = competitionService
				.getCompetitionRoundByScorecardId("1");
		assertNotNull(competitionRound);
		assertNotNull(competitionRound.getCourse());
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#registerCompetitionScorecard(int, com.gffny.ldrbrd.common.model.impl.Scorecard)}
	 * .
	 */
	@Test
	public void testCreateRoundScore() {
		fail("Not yet implemented");
	}

}
