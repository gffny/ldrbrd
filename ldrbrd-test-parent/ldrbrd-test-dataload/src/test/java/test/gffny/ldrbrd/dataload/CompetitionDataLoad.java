/**
 * 
 */
package test.gffny.ldrbrd.dataload;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.ICourseClubService;

/**
 * @author jdgaffney
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class CompetitionDataLoad {

	/** */
	@Autowired
	private ICompetitionService competitionService;

	/** */
	@Autowired
	private ICourseClubService courseClubService;

	/** */
	private Competition competition;

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
	public void testCreateCompetitionData() throws ServiceException {

		Course course = courseClubService.getCourseByNameAndTeeColour("test course",
				TeeColour.WHITE);

		DateTime roundDate = new DateTime();
		competition = competitionService.createCompetition("test testCompetition");
		competitionService.createCompetitionRound(competition, roundDate, 1, course);
	}
}
