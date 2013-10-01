/**
 * 
 */
package com.gffny.ldrbrd.common.exception;

/**
 * @author jdgaffney
 *
 */
public class DataAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2993791995776648590L;

	/**
	 * 
	 */
	public DataAccessException() {
		super();
	}

	/**
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
