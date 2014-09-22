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

import com.gffny.ldrbrd.common.dao.GenericNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.enums.TeeColour;
import com.gffny.ldrbrd.common.model.impl.mongo.Club;
import com.gffny.ldrbrd.common.model.impl.mongo.Course;
import com.gffny.ldrbrd.common.model.impl.mongo.CourseHole;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class NEGolfClubLoad {

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
	public void loadButterbrook() throws PersistenceException {

		// create club
		Club loadClub = new Club("Butter Brook Golf Club");
		loadClub.setAddress("bbgc address");
		loadClub.setManagerName("bbgc manager");
		loadClub.setDressCodePolicy("bbgc dcp");
		loadClub.setGreenKeeperName("bbgc green keeper");
		loadClub.setProGolferName("bbgc pro");

		Course black = new Course();
		black.setPar(72);
		black.setCourseName("Butter Brook GC Black");
		black.setSlopeIndex(128.0);
		black.setTeeColour(TeeColour.BLACK);
		List<CourseHole> blackcourseHoleList = new ArrayList<CourseHole>();

		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black1", 5, 517, 8,
				"Butter Brook GC 1", 1, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black2", 4, 316, 14,
				"Butter Brook GC 2", 2, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black3", 3, 136, 16,
				"Butter Brook GC 3", 3, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black4", 4, 403, 6,
				"Butter Brook GC 4", 4, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black5", 3, 136, 18,
				"Butter Brook GC 5", 5, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black6", 4, 436, 4,
				"Butter Brook GC 6", 6, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black7", 5, 554, 10,
				"Butter Brook GC 7", 7, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black8", 3, 171, 12,
				"Butter Brook GC 8", 8, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black9", 5, 618, 2,
				"Butter Brook GC 9", 9, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black10", 4, 383, 13,
				"Butter Brook GC 10", 10, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black11", 3, 249, 9,
				"Butter Brook GC 11", 11, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black12", 5, 521, 7,
				"Butter Brook GC 12", 12, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black13", 4, 321, 15,
				"Butter Brook GC 23", 13, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black14", 3, 199, 17,
				"Butter Brook GC 14", 14, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black15", 4, 447, 3,
				"Butter Brook GC 15", 15, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black16", 5, 524, 11,
				"Butter Brook GC 16", 16, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black17", 4, 416, 1,
				"Butter Brook GC 17", 17, " "));
		blackcourseHoleList.add(CourseHole.createCourseHole("bbgc black18", 4, 355, 5,
				"Butter Brook GC 18", 18, " "));
		black.setCourseHoleList(blackcourseHoleList);

		Course blue = new Course();
		blue.setPar(72);
		blue.setCourseName("Butter Brook GC Blue");
		blue.setSlopeIndex(128.0);
		blue.setTeeColour(TeeColour.BLUE);
		List<CourseHole> bluecourseHoleList = new ArrayList<CourseHole>();

		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 1", 5, 507, 8,
				"Butter Brook GC 1", 1, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 2", 4, 293, 14,
				"Butter Brook GC 2", 2, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 3", 3, 123, 16,
				"Butter Brook GC 3", 3, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 4", 4, 396, 6,
				"Butter Brook GC 4", 4, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 5", 3, 132, 18,
				"Butter Brook GC 5", 5, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 6", 4, 401, 4,
				"Butter Brook GC 6", 6, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 7", 5, 492, 10,
				"Butter Brook GC 7", 7, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 8", 3, 157, 12,
				"Butter Brook GC 8", 8, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 9", 5, 551, 2,
				"Butter Brook GC 9", 9, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 10", 4, 374, 13,
				"Butter Brook GC 10", 10, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 11", 3, 195, 9,
				"Butter Brook GC 11", 11, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 12", 5, 501, 7,
				"Butter Brook GC 12", 12, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 13", 4, 309, 15,
				"Butter Brook GC 23", 13, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 14", 3, 175, 17,
				"Butter Brook GC 14", 14, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 15", 4, 377, 3,
				"Butter Brook GC 15", 15, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 16", 5, 475, 11,
				"Butter Brook GC 16", 16, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 17", 4, 394, 1,
				"Butter Brook GC 17", 17, " "));
		bluecourseHoleList.add(CourseHole.createCourseHole("bbgc blue 18", 4, 322, 5,
				"Butter Brook GC 18", 18, " "));
		blue.setCourseHoleList(bluecourseHoleList);

		Course white = new Course();
		white.setPar(72);
		white.setCourseName("Butter Brook GC White");
		white.setSlopeIndex(128.0);
		white.setTeeColour(TeeColour.WHITE);
		List<CourseHole> whitecourseHoleList = new ArrayList<CourseHole>();

		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 1", 5, 477, 8,
				"Butter Brook GC 1", 1, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 2", 4, 286, 14,
				"Butter Brook GC 2", 2, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 3", 3, 102, 16,
				"Butter Brook GC 3", 3, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 4", 4, 373, 6,
				"Butter Brook GC 4", 4, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 5", 3, 118, 18,
				"Butter Brook GC 5", 5, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 6", 4, 377, 4,
				"Butter Brook GC 6", 6, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 7", 5, 471, 10,
				"Butter Brook GC 7", 7, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 8", 3, 144, 12,
				"Butter Brook GC 8", 8, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 9", 5, 549, 2,
				"Butter Brook GC 9", 9, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 10", 4, 308, 13,
				"Butter Brook GC 10", 10, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 11", 3, 173, 9,
				"Butter Brook GC 11", 11, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 12", 5, 434, 7,
				"Butter Brook GC 12", 12, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 13", 4, 266, 15,
				"Butter Brook GC 23", 13, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 14", 3, 138, 17,
				"Butter Brook GC 14", 14, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 15", 4, 351, 3,
				"Butter Brook GC 15", 15, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 16", 5, 420, 11,
				"Butter Brook GC 16", 16, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 17", 4, 330, 1,
				"Butter Brook GC 17", 17, " "));
		whitecourseHoleList.add(CourseHole.createCourseHole("bbgc white 18", 4, 300, 5,
				"Butter Brook GC 18", 18, " "));
		white.setCourseHoleList(whitecourseHoleList);

		Course red = new Course();
		red.setPar(72);
		red.setCourseName("Butter Brook GC Red");
		red.setSlopeIndex(128.0);
		red.setTeeColour(TeeColour.RED);
		List<CourseHole> redcourseHoleList = new ArrayList<CourseHole>();

		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red1", 5, 408, 8,
				"Butter Brook GC 1", 1, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red2", 4, 265, 14,
				"Butter Brook GC 2", 2, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red3", 3, 94, 16,
				"Butter Brook GC 3", 3, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red4", 4, 282, 6,
				"Butter Brook GC 4", 4, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red5", 3, 111, 18,
				"Butter Brook GC 5", 5, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red6", 4, 283, 4,
				"Butter Brook GC 6", 6, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red7", 5, 401, 10,
				"Butter Brook GC 7", 7, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red8", 3, 111, 12,
				"Butter Brook GC 8", 8, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red9", 5, 498, 2,
				"Butter Brook GC 9", 9, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red10", 4, 271, 13,
				"Butter Brook GC 10", 10, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red11", 3, 167, 9,
				"Butter Brook GC 11", 11, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red12", 5, 382, 7,
				"Butter Brook GC 12", 12, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red13", 4, 231, 15,
				"Butter Brook GC 23", 13, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red14", 3, 131, 17,
				"Butter Brook GC 14", 14, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red15", 4, 300, 3,
				"Butter Brook GC 15", 15, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red16", 5, 388, 11,
				"Butter Brook GC 16", 16, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red17", 4, 307, 1,
				"Butter Brook GC 17", 17, " "));
		redcourseHoleList.add(CourseHole.createCourseHole("bbgc red18", 4, 279, 5,
				"Butter Brook GC 18", 18, " "));
		loadClub.setCourseList(Arrays.asList(black, blue, white, red));

		String id = clubDao.persist(loadClub);
		List<Course> courseList = loadClub.getCourseList();
		loadClub.setCourseList(new ArrayList<Course>());
		// TODO CREATE A METHOD TO CLONE CLUB WITHOUT COURSE LIST / OTHER COLLECTIONS OTHERWISE
		// THERE IS A LOOP OF PERSISTENCE CLUB -> COURSE -> CLUB -> COURSE ....
		for (Course loadCourse : courseList) {
			loadCourse.setClub(loadClub);
			System.out.println(courseDao.persist(loadCourse));
		}

		Club clubReturned = clubDao.findById(Club.class, id);
		System.out.println(clubReturned.getClubName());
	}
}
