/**
 * 
 */
package test.gffny.ldrbrd.dataload;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.Club;
import com.gffny.ldrbrd.common.model.impl.Course;
import com.gffny.ldrbrd.common.service.impl.CourseClubService;

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
	private CourseClubService courseClubService;

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
	public void testCreateCourseaData() {
		Club club = courseClubService.createClub("test club");
		Course course = courseClubService.createCourse("test course", club,
				TeeColour.WHITE, 114.5d, 72, "test course image reference");
		course.toString();
	}
}
