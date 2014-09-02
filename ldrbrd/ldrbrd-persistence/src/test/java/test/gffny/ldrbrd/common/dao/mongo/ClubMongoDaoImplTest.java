/**
 * 
 */
package test.gffny.ldrbrd.common.dao.mongo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.mongo.ClubMongoDaoImpl;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.mongo.Club;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class ClubMongoDaoImplTest {

	@Autowired
	private ClubMongoDaoImpl clubMongoDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.dao.mongo.ClubMongoDaoImpl#listCourseByClub(java.lang.String)}
	 * .
	 * 
	 * @throws PersistenceException
	 */
	@Test
	public void testListCourseByClub() throws PersistenceException {
		Club club = clubMongoDao.findById(Club.class, "53ef8c050364fc04b2d8ed1d");
		Assert.assertNotNull(club);
	}

}
