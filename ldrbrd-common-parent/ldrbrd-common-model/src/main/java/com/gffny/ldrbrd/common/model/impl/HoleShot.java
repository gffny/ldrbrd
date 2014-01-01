/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;


/**
 * @author jdgaffney
 *
 */
public class HoleShot {
	
	private String gps;
	private String type;
	private String golfClub;
	private String resultDirection;
	private String resultLength;

	/**
	 * @return the gps
	 */
	public String getGps() {
		return gps;
	}
	/**
	 * @param gps the gps to set
	 */
	public void setGps(String gps) {
		this.gps = gps;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the golfClub
	 */
	public String getGolfClub() {
		return golfClub;
	}
	/**
	 * @param golfClub the golfClub to set
	 */
	public void setGolfClub(String golfClub) {
		this.golfClub = golfClub;
	}
	/**
	 * @return the resultDirection
	 */
	public String getResultDirection() {
		return resultDirection;
	}
	/**
	 * @param resultDirection the resultDirection to set
	 */
	public void setResultDirection(String resultDirection) {
		this.resultDirection = resultDirection;
	}
	/**
	 * @return the resultLength
	 */
	public String getResultLength() {
		return resultLength;
	}
	/**
	 * @param resultLength the resultLength to set
	 */
	public void setResultLength(String resultLength) {
		this.resultLength = resultLength;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HoleShot ["
				+ (gps != null ? "gps=" + gps + ", " : "")
				+ (type != null ? "type=" + type + ", " : "")
				+ (golfClub != null ? "golfClub=" + golfClub + ", " : "")
				+ (resultDirection != null ? "resultDirection="
						+ resultDirection + ", " : "")
				+ (resultLength != null ? "resultLength=" + resultLength : "")
				+ "]";
	}
}
