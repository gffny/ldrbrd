/**
 * 
 */
package com.gffny.ldrbrd.common.model.web;

/**
 * @author John D. Gaffney | gffny.com
 */
public class StatusResponse {

	/** The status code. */
	private String statusCode;

	/** The status message. */
	private String statusMessage;

	/**
	 * Instantiates a new status response.
	 */
	public StatusResponse() {

	}

	/**
	 * Instantiates a new status response.
	 * 
	 * @param code
	 *            the code
	 * @param statusMessage
	 *            the status message
	 */
	public StatusResponse(final String code, final String statusMessage) {
		this.statusCode = code;
		this.statusMessage = statusMessage;
	}

	/**
	 * Gets the status code.
	 * 
	 * @return the status code
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param code
	 */
	public void setStatusCode(String code) {
		this.statusCode = code;
	}

	/**
	 * Gets the status message.
	 * 
	 * @return the status message
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * Sets the status message.
	 * 
	 * @param statusMessage
	 *            the new status message
	 */
	public void setStatusMessage(final String statusMessage) {
		this.statusMessage = statusMessage;
	}

}