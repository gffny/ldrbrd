/**
 * 
 */
package com.gffny.ldrbrd.rest.req;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * @author jdgaffney
 * 
 */
@JsonAutoDetect
public class ProfileRequest {

	private String handle;

	private String id;

	/**
	 * @return the handle
	 */
	public String getHandle() {
		return handle;
	}

	/**
	 * @param handle
	 *            the handle to set
	 */
	public void setHandle(String handle) {
		this.handle = handle;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
