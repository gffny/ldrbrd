/**
 * 
 */
package com.gffny.ldrbrd.common.security.enums;

/**
 * @author John D. Gaffney | gffny.com
 * 
 */
public enum AuthenticationResult {

	SUCCESS("auth.res.success"), USER_LOCKED_OUT("auth.res.lockedout"), FAILED(
			"auth.res.failed"), ERROR("auth.res.error");

	/**
	 * 
	 */
	private String labelKey;

	/**
	 * 
	 * @param labelKey
	 */
	private AuthenticationResult(final String labelKey) {
		this.labelKey = labelKey;
	}

	/**
	 * 
	 * @return
	 */
	public String getLabelKey() {
		return labelKey;
	}
}
