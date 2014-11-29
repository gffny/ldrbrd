/**
 * 
 */
package load.gffny.ldrbrd.club;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.nosql.GenericNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.nosql.Club;
import com.gffny.ldrbrd.common.model.nosql.Course;
import com.gffny.ldrbrd.common.model.nosql.CourseHole;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class TestGolfClubLoad {

	/**
	 * 
	 */
	@Autowired
	private GenericNoSqlDao<Club> clubDao;

	/**
	 * 
	 */
	@Autowired
	private GenericNoSqlDao<Course> courseDao;

	/**
	 * @throws PersistenceException
	 */
	@Test
	public void loadGolfClub() throws PersistenceException {

		// create club
		Club loadClub = new Club("test club");
		loadClub.setAddress("test address");
		loadClub.setManagerName("test manager");
		loadClub.setDressCodePolicy("test dcp");
		loadClub.setGreenKeeperName("test green keeper");
		loadClub.setProGolferName("test pro");

		// create course
		Course course = new Course();
		course.setPar(72);
		course.setCourseName("test course");
		course.setTeeColour(TeeColour.BLUE);
		course.setSlopeIndex(123.2);
		List<CourseHole> courseHoleList = new ArrayList<CourseHole>();
		for (int i = 1; i <= Constant.EIGHTEEN_HOLE; i++) {
			CourseHole courseHole = new CourseHole();
			courseHole.setHoleNumber(i);
			courseHole.setHoleDistance(300 + (i * 10));
			courseHole.setHoleDescription("Hole number: " + i);
			courseHole.setName("Hole number: " + i);
			courseHoleList.add(courseHole);
		}
		course.setCourseHoleList(courseHoleList);
		loadClub.setCourseList(Arrays.asList(course));

		String id = clubDao.persist(loadClub);
		List<Course> courseList = loadClub.getCourseList();
		loadClub.setCourseList(new ArrayList<Course>());
		// TODO CREATE A METHOD TO CLONE CLUB WITHOUT COURSE LIST / OTHER
		// COLLECTIONS OTHERWISE
		// THERE IS A LOOP OF PERSISTENCE CLUB -> COURSE -> CLUB -> COURSE ....
		for (Course loadCourse : courseList) {
			loadCourse.setClub(loadClub);
			System.out.println(courseDao.persist(loadCourse));
		}

		Club clubReturned = clubDao.findById(Club.class, id);
		System.out.println(clubReturned.getClubName());
	}

	/**
	 * @throws PersistenceException
	 */
	@Test
	public void getGolfClub() throws PersistenceException {

		Club clubReturned = clubDao.findById(Club.class,
				"53ef8c050364fc04b2d8ed1d");
		if (clubReturned != null) {
			System.out.println(clubReturned.getClubName());
		}

		clubReturned = clubDao.findByName(Club.class, "test club");
		if (clubReturned != null) {
			System.out.println(clubReturned.getClubName());
		}

		Course courseReturned = courseDao.findByName(Course.class,
				"test course");
		if (courseReturned != null) {
			System.out.println(courseReturned.getName());
		}
	}
}
