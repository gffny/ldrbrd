/**
 * 
 */
package test.gffny.ldrbrd.common.utils;

import org.junit.Test;

import com.gffny.ldrbrd.common.utils.ScoringUtils;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public class ScoringUtilsTest {

	@Test
	public void test() {
		// ScoringUtils.calcHandicap(16, 16, holeNumber)
		int HANDICAP = 16;
		int hc = ScoringUtils.calcHoleHandicap(16, HANDICAP);
		System.out.println(hc);
		hc = ScoringUtils.calcHoleHandicap(17, HANDICAP);
		System.out.println(hc);
		hc = ScoringUtils.calcHoleHandicap(15, HANDICAP);
		System.out.println(hc);
		HANDICAP = 26;
		hc = ScoringUtils.calcHoleHandicap(15, HANDICAP);
		System.out.println(hc);
		HANDICAP = 30;
		hc = ScoringUtils.calcHoleHandicap(15, HANDICAP);
		System.out.println(hc);
		HANDICAP = 33;
		hc = ScoringUtils.calcHoleHandicap(15, HANDICAP);
		System.out.println(hc);
	}

}
