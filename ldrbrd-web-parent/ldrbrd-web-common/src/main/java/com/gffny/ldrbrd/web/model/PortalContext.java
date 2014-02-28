package com.gffny.ldrbrd.web.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.gffny.ldrbrd.common.utils.StringUtils;

public class PortalContext {

	private String uid;
	private String username;

	// Stands for manage organization id (used for context switching)
	private String moid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMoid() {
		return moid;
	}

	public void setMoid(String moid) {
		this.moid = moid;
	}

	@JsonIgnore
	public boolean isValid(String username) {
		if (StringUtils.isNotEquivalent(getUsername(), username)) {
			return false;
		}

		return true;
	}
}
