/**
 * 
 */
package test.gffny.ldrbrd.common.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.impl.UserProfile;
import com.gffny.ldrbrd.common.persistence.GenericDao;

/**
 * @author jdgaffney
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-test.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class GenericDaoTest {

	@Autowired
	public GenericDao<UserProfile> userJpaDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() throws DataAccessException {

	}
}
