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

import com.gffny.ldrbrd.common.dao.GenericNoSqlDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.mongo.HoleScore;

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
		HoleScore blah = new HoleScore();
		blah.setCompetitionId("123");
		blah.setCompetitionRoundId("123");
		blah.setCourseId("123");
		blah.setDateTime(new Date());
		blah.setHoleCompetitionScore(1);
		blah.setHoleNumber(1);
		holeScoreMongoDaoImpl.persist(blah);

	}

}
