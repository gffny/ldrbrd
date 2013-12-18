/**
 * 
 */
package test.gffny.ldrbrd.common.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gffny.ldrbrd.common.model.impl.Competition;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.service.ICompetitionService;

/**
 * @author jdgaffney
 * 
 */
public class CompetitionServiceTest {

	@Autowired
	private ICompetitionService competitionService;

	private Competition competition;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.service.impl.CompetitionService#getCompetitionRound(java.lang.String, java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetCompetitionRound() {
		CompetitionRound competitionRound = competitionService
				.getCompetitionRound("", 1);
		competition = competitionRound.getCompetition();
		competition.toString();
	}
}
