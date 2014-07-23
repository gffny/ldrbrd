/**
 * 
 */
package test.gffny.ldrbrd.dataload;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.enums.Dominance;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.model.mapping.FavouriteCourse;
import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.service.IUserProfileService;

/**
 * @author jdgaffney
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class GolferDataLoad {

	@Autowired
	private IUserProfileService personService;

	@Autowired
	private ICourseClubService courseService;

	@Autowired
	private ICompetitionService competitionService;

	@Autowired
	private GenericDao<FavouriteCourse> favouriteCourseDao;

	@Autowired
	private IScorecardService scorecardService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws ServiceException
	 */
	@Test
	public void testCreateGolferData() throws ServiceException {
		GolferProfile golfer = new GolferProfile();
		golfer.setProfileHandle("gffny");
		golfer.setFirstName("John");
		golfer.setLastName("Gaffney");
		golfer.setEmailAddress("john@gffny.com");
		golfer.setHandicap(13);
		golfer.setHandedness(Dominance.RIGHT);
		// encode the password using the StandardPassword encoder
		golfer.setPassword(new StandardPasswordEncoder().encode("fidelity"));
		golfer.setProfileImageRef("http://distilleryimage7.s3.amazonaws.com/26adcca2488511e3aec5128fd569807c_8.jpg");
		personService.addGolferProfile(golfer);
	}

	/**
	 * @throws ServiceException
	 */
	@Test
	public void testGetGolferByName() throws ServiceException {
		GolferProfile golfer = personService.getGolferByEmail("john@gffny.com");
		System.out.println(golfer.getId());
	}

	/**
	 * @throws ServiceException
	 * @throws PersistenceException
	 */
	@Test
	public void testLoadGolferFavouriteCourseList() throws ServiceException, PersistenceException {
		// GolferProfile golfer = personService.getGolferByHandle("gffny");
		// Course course = courseService.getCourseByNameAndTeeColour(
		// "test course", TeeColour.WHITE);
		// FavouriteCourse favCourse = new FavouriteCourse(golfer, course);
		// favouriteCourseDao.persist(favCourse);
	}

	/**
	 * @throws ServiceException
	 */
	@Test
	public void testLoadGolferCompetitionRegistrationList() throws ServiceException {

		GolferProfile golfer = personService.getGolferByHandle("gffny");
		Competition competition = competitionService.getCompetitionByName("test testCompetition");
		int handicap = golfer.getHandicap();
		competitionService.registerGolferForCompetitionWithHandicap(golfer, competition, handicap);
	}

	/**
	 * @throws ServiceException
	 */
	@Test
	public void testLoadCompetitionRoundScorecard() throws ServiceException {
		// GolferProfile golfer = personService.getGolferByHandle("gffny");
		// Competition testCompetition = competitionService
		// .getCompetitionByName("test testCompetition");
		// CompetitionRegistration registration = competitionService
		// .getCompetitionRegistrationForGolfer(golfer, testCompetition);
		// CompetitionRound round = competitionService.getCompetitionRound(
		// testCompetition.getId(), 1);
		// Scorecard scorecard = scorecardService.startCompetitionScorecard(
		// golfer.getId(), golfer.getId(), testCompetition.getId(), 1, null,
		// registration.getCompetitionHandicap());
		// List<CourseHole> courseHoleList = round.getCourse().getCourseHoleList();
		// Map<Integer, Integer> holeScoreMap = new HashMap<Integer, Integer>();
		// for (CourseHole courseHole : courseHoleList) {
		// holeScoreMap.put(courseHole.getHoleNumber(), 4);
		// }
		// scorecardService.scoreHoleScoreMap(scorecard.getId(), holeScoreMap);
	}
}
