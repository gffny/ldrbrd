/**
 * 
 */
package test.gffny.ldrbrd.dataload;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.enums.ClubCategory;
import com.gffny.ldrbrd.common.model.enums.ClubType;
import com.gffny.ldrbrd.common.model.enums.Manufacturer;
import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.service.IUserProfileService;
import com.gffny.ldrbrd.common.service.impl.GolfClubService;

/**
 * @author jdgaffney
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class GolfClubDataLoad {

	@Autowired
	private GenericDao<GolfClub> golfClubDao;

	@Autowired
	private GolfClubService golfClubService;

	@Autowired
	private IUserProfileService profileService;

	@Test
	public void testDefaultGolfClubList() {
		List<GolfClub> golfClubList = golfClubService.getDefaultGolfClubList();
		golfClubList.size();
	}

	@Test
	public void testLoadDefaultGolfClubList() {
		GolferProfile gffny = profileService.getGolferByEmail("john@gffny.com");
		profileService.createDefaultGolfBagForGolfer(gffny);
	}

	/**
	 * 
	 */
	@Test
	public void testCreateGolfClubData() {

		GolfClub[] allClubsArray = new GolfClub[ClubType
				.getDefaultClubQuantity()];

		int i = 0, j = 0;

		// create driver
		GolfClub driver = testClub(ClubCategory.DRIVER, 11, ClubType.DRIVER);
		allClubsArray[j++] = driver;

		// create wood array
		GolfClub[] woodArray = new GolfClub[ClubType.WOOD_ARRAY.length];
		for (ClubType clubType : ClubType.WOOD_ARRAY) {
			int loftValue = 10 + (i + 2);
			allClubsArray[j++] = woodArray[i++] = testClub(ClubCategory.WOOD,
					loftValue, clubType);
			// woodArray[i];
		}

		// create hybrid array
		GolfClub[] hybridArray = new GolfClub[ClubType.HYBRID_ARRAY.length];
		i = 0;
		for (ClubType clubType : ClubType.HYBRID_ARRAY) {
			int loftValue = 17 + i;
			allClubsArray[j++] = hybridArray[i++] = testClub(
					ClubCategory.HYBRID, loftValue, clubType);
		}

		// create iron array
		GolfClub[] ironArray = new GolfClub[ClubType.IRON_ARRAY.length];
		i = 0;
		for (ClubType clubType : ClubType.IRON_ARRAY) {
			int loftValue = 90 - (i * 4);
			allClubsArray[j++] = ironArray[i++] = testClub(ClubCategory.IRON,
					loftValue, clubType);
		}

		// create wedge array
		GolfClub[] wedgeArray = new GolfClub[ClubType.WEDGE_ARRAY.length];
		i = 0;
		for (ClubType clubType : ClubType.WEDGE_ARRAY) {
			int loftValue = 60 - (i * 2);
			allClubsArray[j++] = wedgeArray[i++] = testClub(ClubCategory.WEDGE,
					loftValue, clubType);
		}

		// create putter
		GolfClub putter = testClub(ClubCategory.PUTTER, 0, ClubType.PUTTER);
		allClubsArray[j++] = putter;

		// TODO PERSIST THE NEWLY CREATED CLUBS!
		for (GolfClub gc : allClubsArray) {
			try {
				golfClubDao.persist(gc);
			} catch (DataAccessException daEx) {
				System.out.println(daEx.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param cat
	 * @param lft
	 * @param type
	 * @return
	 */
	private GolfClub testClub(ClubCategory cat, double lft, ClubType type) {

		GolfClub newClub = new GolfClub();
		newClub.setClubCategory(cat);
		newClub.setManufacturer(Manufacturer.getDefault());
		newClub.setClubType(type);
		newClub.setClubLoft(lft);
		newClub.setDefault(true);
		return newClub;
	}
}
