/**
 * 
 */
package test.gffny.ldrbrd.common.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.UserProfile;
import com.gffny.ldrbrd.common.persistence.GenericDao;
import com.gffny.ldrbrd.common.persistence.impl.PersonDao;
import com.gffny.ldrbrd.common.persistence.impl.UserJpaDao;

/**
 * @author jdgaffney
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring/applicationContext-test.xml", "classpath*:spring/applicationContext-model.xml"})
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
	public void test() {
		UserProfile up = new UserProfile(false);
		up.setId(new Integer(2));
		try {
			userJpaDao.save(up);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
