/**
 * 
 */
package test.gffny.ldrbrd.common.dao.mongo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.dao.nosql.GenericNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.nosql.HoleScore;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class HoleScoreMongoDaoImplTest {

	@Autowired
	private GenericNoSqlDao<HoleScore> holeScoreMongoDaoImpl;

	/**
	 * @throws PersistenceException
	 * 
	 */
	@Test
	public void test() throws PersistenceException {
		HoleScore blah = HoleScore.instance("123", "123", "1", "123", "1",
				new Date(), 1, 4, 2, 0, -1, 4, 0, -1, 2);
		holeScoreMongoDaoImpl.persist(blah);

	}

}
