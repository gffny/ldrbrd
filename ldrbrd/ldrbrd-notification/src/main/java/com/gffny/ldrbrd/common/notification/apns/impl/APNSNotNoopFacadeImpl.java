/**
 * 
 */
package com.gffny.ldrbrd.common.notification.apns.impl;

import org.springframework.stereotype.Component;

import com.gffny.ldrbrd.common.notification.apns.IAPNSFacade;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
@Component
public class APNSNotNoopFacadeImpl implements IAPNSFacade {

	/** */
	private ApnsService service;

	/**
	 * 
	 */
	public APNSNotNoopFacadeImpl() {
		service = APNS
				.newService()
				.withCert(
						"/Users/jdgaffney/dev/gffny/ldrbrdiphone/apns-dev-cert.p12",
						"pass").withSandboxDestination().build();
		System.out.println("hello");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.notification.apns.IAPNSFacade#sendNotification
	 *      (java.lang.String, java.lang.String)
	 */
	@Override
	public void sendNotification(String recipient, String message) {
		String payload = APNS.newPayload().alertBody(message).build();
		String token = "fedfbcfb....";
		service.push(token, payload);
	}

}
