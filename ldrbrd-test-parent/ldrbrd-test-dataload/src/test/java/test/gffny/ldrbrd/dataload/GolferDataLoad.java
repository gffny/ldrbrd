/**
 * 
 */
package test.gffny.ldrbrd.dataload;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.model.enums.Dominance;
import com.gffny.ldrbrd.common.model.impl.GolferClubDetail;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.service.IUserProfileService;

/**
 * @author jdgaffney
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class GolferDataLoad {

	@Autowired
	private IUserProfileService personService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetGolfBag() {
		// 5c9ac179-d83a-433a-86df-16c49e955277
		GolferProfile golfer = personService.getGolferByHandle("gffny");
		List<GolferClubDetail> golfBag = golfer.getGolfBag();
		if (golfBag != null) {
			for (GolferClubDetail gcd : golfBag) {
				System.out.println(gcd.toString());
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testCreateGolferData() {
		GolferProfile golfer = new GolferProfile();
		golfer.setProfileHandle("gffny");
		golfer.setFirstName("John");
		golfer.setLastName("Gaffney");
		golfer.setEmailAddress("john@gffny.com");
		golfer.setHandicap(13);
		golfer.setHandedness(Dominance.RIGHT);
		golfer.setPassword("fidelity");
		golfer.setProfileImageRef("http://distilleryimage7.s3.amazonaws.com/26adcca2488511e3aec5128fd569807c_8.jpg");
		personService.addGolferProfile(golfer);
	}
}
