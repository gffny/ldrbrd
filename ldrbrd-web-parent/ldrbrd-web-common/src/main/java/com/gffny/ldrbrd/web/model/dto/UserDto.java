/**
 * 
 */
package com.gffny.ldrbrd.web.model.dto;

/**
 * @author John Gaffney (john@gffny.com) May 1, 2013
 * 
 */
public class UserDto extends BaseDto {

	private String handle;
	private String password;

	/**
	 * 
	 * 
	 */
	public String getProfileHandle() {
		return this.handle;
	}

	/**
	 * 
	 * 
	 */
	public void setProfileHandle(String profileHandle) {
		this.handle = profileHandle;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param object
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
