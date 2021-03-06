/**
 * 
 */
package test.gffny.ldrbrd.dataload;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Club;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.model.impl.CourseHole;
import com.gffny.ldrbrd.common.service.ICourseClubService;

/**
 * @author jdgaffney
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class CourseDataLoad {

	@Autowired
	private ICourseClubService courseClubService;

	Club club;
	Course course;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws ServiceException
	 * 
	 */
	@Test
	public void testCreate18HoleCourseData() throws ServiceException {
		club = courseClubService.createClub("test club");
		course = courseClubService.createCourse("test course", club,
				TeeColour.WHITE, 114.5d, 72, "test course image reference");
		course.toString();
		Random r = new Random();
		for (int i = 1; i <= 18; i++) {
			CourseHole courseHole = CourseHole.createCourseHole(course,
					"Hole No. " + i, (r.nextInt(550 - 150) + 150), "Hole No. "
							+ i + " description", i, "");
			courseClubService.saveOrUpdateHole(courseHole);
		}
	}

	@Test
	public void testCreate9HoleCourseData() throws ServiceException {
		club = courseClubService.createClub("test club 2");
		course = courseClubService.createCourse("test three hole course", club,
				TeeColour.WHITE, 114.5d, 36, "test course image reference");
		System.out.println(course.toString());
		Random r = new Random();
		for (int i = 1; i <= 9; i++) {
			CourseHole courseHole = CourseHole.createCourseHole(course,
					"Hole No. " + i, (r.nextInt(550 - 150) + 150), "Hole No. "
							+ i + " description", i, "");
			courseClubService.saveOrUpdateHole(courseHole);
		}
	}

	@Test
	public void testGetMyFuckingCourseData() throws ServiceException {
		course = courseClubService.getCourseByNameAndTeeColour(
				"test three hole course", TeeColour.WHITE);
		System.out.println("\n\n\n\n\n\n" + course.getId());
	}
}
