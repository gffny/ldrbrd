/**
 * 
 */
package test.gffny.ldrbrd.common.notification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gffny.ldrbrd.common.notification.apns.IAPNSFacade;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-notification.xml" })
public class IAPNSFacadeTest {

	/** */
	@Autowired
	private IAPNSFacade aPNSNotNoopFacadeImpl;

	/**
	 * Test method for
	 * {@link com.gffny.ldrbrd.common.notification.apns.impl.APNSNotNoopFacadeImpl#sendNotification(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testSendNotification() {
		aPNSNotNoopFacadeImpl.sendNotification("", "test");
	}

}
