/**
 * 
 */
package com.gffny.ldrbrd.rest.resp;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gffny.ldrbrd.rest.utils.StatusResponseSerialiser;

/**
 * @author jdgaffney
 *
 */
@XmlRootElement
@JsonSerialize(using=StatusResponseSerialiser.class)
public class StatusResponse {

	/** The status code. */
	private StatusCode statusCode;

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
	public StatusResponse(final StatusCode code, final String statusMessage) {
		this.statusCode = code;
		this.statusMessage = statusMessage;
	}

	/**
	 * The Enum StatusCode.
	 */
	public enum StatusCode {
		/** The SUCCESS. */
		SUCCESS,
		/** The FAILURE. */
		FAILURE, NO_DATA, UPDATE_FAILED, NOT_AUTHENTICATED
	}

	/**
	 * Gets the status code.
	 * 
	 * @return the status code
	 */
	public StatusCode getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 * 
	 * @param statusCode
	 *            the new status code
	 */
	public StatusResponse setStatusCode(final StatusCode statusCode) {
		this.statusCode = statusCode;
		return this;
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
	public StatusResponse setStatusMessage(final String statusMessage) {
		this.statusMessage = statusMessage;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StatusResponse setNoData() {
		this.setStatusCode(StatusCode.NO_DATA);
		this.setStatusMessage("No Data");
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StatusResponse setNotAutenticated() {
		this.setStatusCode(StatusCode.NOT_AUTHENTICATED);
		this.setStatusMessage("Not Authenticated");
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StatusResponse setSuccessMessage() {
		this.setStatusCode(StatusCode.SUCCESS);
		this.setStatusMessage("success");
		return this;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public StatusResponse setFailureMessage(final Exception e) {
		this.setStatusCode(StatusCode.FAILURE);
		if (e.getMessage() != null) {
			this.setStatusMessage(e.getMessage());
		} else {
			this.setStatusMessage("failure");
		}
		return this;
	}

}
