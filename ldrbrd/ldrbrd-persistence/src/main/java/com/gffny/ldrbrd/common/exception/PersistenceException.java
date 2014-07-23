/**
 * 
 */
package com.gffny.ldrbrd.common.exception;

/**
 * @author John D. Gaffney | gffny.com
 * 
 */
public class PersistenceException extends LdrbrdException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2993791995776648590L;

	/**
	 * 
	 */
	public PersistenceException() {
		super();
	}

	/**
	 * @param message
	 */
	public PersistenceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}
