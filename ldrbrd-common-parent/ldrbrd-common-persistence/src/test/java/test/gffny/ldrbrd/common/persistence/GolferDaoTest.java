/**
 * 
 */
package test.gffny.ldrbrd.common.persistence;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.enums.Dominance;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.persistence.GolferDao;

/**
 * @author jdgaffney
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration({
// "classpath*:spring/applicationContext-persistence.xml",
// "classpath*:spring/applicationContext-model.xml" })
public class GolferDaoTest {

	private GolferDao golferDao;

	/**
	 * @return the golferDao
	 */
	public GolferDao getGolferDao() {
		return golferDao;
	}

	/**
	 * @param golferDao
	 *            the golferDao to set
	 */
	@Autowired
	public void setGolferDao(GolferDao golferDao) {
		this.golferDao = golferDao;
	}

	GolferProfile testProfile;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testProfile = new GolferProfile();
		testProfile.setFirstName("John");
		testProfile.setLastName("Gaffney");
		testProfile.setHandedness(Dominance.RIGHT);
		testProfile.setEmailAddress("gaffney.ie@gmail.com");
		testProfile.setHandicap(26);
		testProfile.setProfileHandle("gffny");
	}

	@Test
	public void test() {
		try {
			golferDao.persist(testProfile);
		} catch (DataAccessException dae) {
			fail(dae.getMessage());
		} finally {
			assertTrue(true);
		}
	}
}
