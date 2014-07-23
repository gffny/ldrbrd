/**
 * 
 */
package test.gffny.ldrbrd.common.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.model.enums.Dominance;
import com.gffny.ldrbrd.common.model.impl.Golfer;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class GolferDaoTest {

	@Autowired
	private GenericDao<Golfer> golferDao;

	Golfer testProfile;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testProfile = new Golfer();
		testProfile.setFirstName("John");
		testProfile.setLastName("Gaffney");
		testProfile.setHandedness(Dominance.RIGHT);
		testProfile.setEmailAddress("gaffney.ie@gmail.com2");
		testProfile.setHandicap(26);
		testProfile.setProfileHandle("gffny2");
	}

	@Test
	public void test() {
		try {
			golferDao.persist(testProfile);
		} catch (Exception dae) {
			dae.printStackTrace();
			fail(dae.getMessage());
		} finally {
			assertTrue(true);
		}
	}

}
