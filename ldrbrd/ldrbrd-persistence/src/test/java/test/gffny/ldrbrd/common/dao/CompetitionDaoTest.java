/**
 * 
 */
package test.gffny.ldrbrd.common.dao;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;

/**
 * @author John D. Gaffney | gffny.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml" })
public class CompetitionDaoTest {

	/** */
	@Autowired
	private GenericDao<CompetitionRound> competitionRoundDao;

	@Test
	@Transactional
	public void testGetCompetitionRoundScore() throws PersistenceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("scorecardId", 1);
		params.put("roundNumber", 1);
		List<CompetitionRound> competitionRound = competitionRoundDao
				.findByNamedQuery(
						CompetitionRound.FIND_BY_SCORECARD_ID_AND_RND_NMBR,
						params);
		assertNotNull(competitionRound);
	}
}
