/**
 * 
 */
package com.gffny.ldrbrd.common.notification.apns;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
public interface IAPNSFacade {

	/**
	 * 
	 * @param recipient
	 * @param message
	 */
	public void sendNotification(String recipient, String message);
}
