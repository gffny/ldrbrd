package com.gffny.ldrbrd.web.model.dto;

import com.gffny.ldrbrd.common.model.impl.UserProfile;

public class InitResponseDto extends BaseDto {

	private static String STATUS_VALID = "VALID";

	private String loadtime;
	private UserProfile user;
	private boolean passwordChangeRequired;
	private String status;

	public static InitResponseDto create(UserProfile user) {
		InitResponseDto dto = new InitResponseDto();
		dto.setStatus(STATUS_VALID);
		dto.setUser(user);
		dto.getUser().setPassword(null);
		return dto;
	}

	public String getLoadtime() {
		return loadtime;
	}

	public void setLoadtime(String loadtime) {
		this.loadtime = loadtime;
	}

	/**
	 * @return the user
	 */
	public UserProfile getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(UserProfile user) {
		this.user = user;
	}

	/**
	 * 
	 * @return true if the user's password is expired
	 */
	public boolean isPasswordChangeRequired() {
		return passwordChangeRequired;
	}

	/**
	 * 
	 * @param passwordChangeRequired
	 *            true if the user's password is expired
	 */
	public void setPasswordChangeRequired(boolean passwordChangeRequired) {
		this.passwordChangeRequired = passwordChangeRequired;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
