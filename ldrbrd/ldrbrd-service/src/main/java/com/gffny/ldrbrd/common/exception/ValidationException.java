/**
 * 
 */
package com.gffny.ldrbrd.common.exception;

/**
 * @author John D. Gaffney | gffny.com
 */
public class ValidationException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3063208047436011987L;

	/**
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}
}
