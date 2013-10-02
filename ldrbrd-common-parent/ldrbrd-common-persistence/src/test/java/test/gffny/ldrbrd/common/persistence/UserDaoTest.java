/**
 * 
 */
package test.gffny.ldrbrd.common.persistence;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.ldrbrd.common.model.impl.UserProfile;
import com.gffny.ldrbrd.common.persistence.GenericDao;

/**
 * @author jdgaffney
 * 
 */
public class UserDaoTest {

	@Autowired
	private GenericDao<UserProfile> userDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
