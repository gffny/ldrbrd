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

import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.service.impl.CompetitionService;
import com.gffny.ldrbrd.common.service.impl.CourseClubService;

/**
 * @author jdgaffney
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class CompetitionDataLoad {

	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private CourseClubService courseClubService;

	private Competition competition;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 
	 */
	@Test
	public void testCreateCompetitionData() {

		Course course = courseClubService.getCourseByNameAndTeeColour(
				"test course", TeeColour.WHITE);

		DateTime roundDate = new DateTime();
		competition = competitionService.createCompetition("test competition");
		competitionService.createCompetitionRound(competition, roundDate, 1,
				course);
	}

}
