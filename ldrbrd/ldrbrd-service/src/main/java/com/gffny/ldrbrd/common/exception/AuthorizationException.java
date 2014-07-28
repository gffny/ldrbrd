/**
 * 
 */
package com.gffny.ldrbrd.common.exception;

/**
 * @author John D. Gaffney | gffny.com
 */
public class AuthorizationException extends LdrbrdException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3242212243642503991L;

	/**
	 * 
	 */
	public AuthorizationException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public AuthorizationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AuthorizationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AuthorizationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
