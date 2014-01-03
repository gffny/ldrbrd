/**
 * 
 */
package test.gffny.ldrbrd.common.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.security.enums.AuthenticationResult;
import com.gffny.ldrbrd.common.security.token.AuthenticationToken;
import com.gffny.ldrbrd.common.security.token.impl.EmailPasswordToken;
import com.gffny.ldrbrd.common.service.IUserProfileService;

/**
 * @author jdgaffney
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-service.xml" })
public class AuthenticationTest {

	@Autowired
	IUserProfileService personService;

	GolferProfile testGolfer = new GolferProfile();
	String password = "password";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testGolfer.setId("6a6a3cbf-728e-48b7-8b33-c440c6b57888");
	}

	/**
	 * 
	 */
	@Test
	public void testAuthenticateGolferByEmailPassword() {
		AuthenticationToken authToken = new EmailPasswordToken(
				testGolfer.getId(), password);
		AuthenticationResult authResult = personService
				.authenticateUser(authToken);
		assertEquals(AuthenticationResult.SUCCESS, authResult);
	}
}
