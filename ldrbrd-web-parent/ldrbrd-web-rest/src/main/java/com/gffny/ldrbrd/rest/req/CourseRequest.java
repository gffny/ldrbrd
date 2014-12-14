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
public class CourseRequest {
	
	private String id;
	private String golferId;
	private int favouriteLimit;
	private String location;
	private String zip;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public String getGolferId() {
		return golferId;
	}

	/**
	 * @return the favouriteLimit
	 */
	public int getFavouriteLimit() {
		return favouriteLimit;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @param golferId
	 */
	public void setGolferId(String golferId) {
		this.golferId = golferId;
	}

	/**
	 * @param favouriteLimit the favouriteLimit to set
	 */
	public void setFavouriteLimit(int favouriteLimit) {
		this.favouriteLimit = favouriteLimit;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
