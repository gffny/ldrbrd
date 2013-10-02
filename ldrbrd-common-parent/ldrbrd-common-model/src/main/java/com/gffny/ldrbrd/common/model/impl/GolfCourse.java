/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.Arrays;
import java.util.List;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John Gaffney (john@gffny.com) Jul 31, 2012
 * 
 */
public class GolfCourse extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -955925665524705321L;
	private String location;
	private String teeColour;
	private int par;
	private int[] holePar;
	private int[] holeIndex;
	private int[] teeDistance;
	private List<CourseHole> holeList;

	/**
	 * 
	 * @return
	 */
	public String getCourseId() {
		return this.getId();
	}

	/**
	 * 
	 */
	public int getPar() {
		return par;
	}

	/**
	 * 
	 */
	public int[] getHoleParArray() {
		return holePar;
	}

	/**
	 * 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @param holeNumber
	 */
	public CourseHole getHole(int holeNumber) {
		// return new GolfCourse.Hole(holePar[holeNumber - 1],
		// holeIndex[holeNumber - 1], teeDistance[holeNumber - 1]);
		return new CourseHole();
	}

	/**
	 * 
	 */
	public int[] getTeeDistanceArray() {
		return teeDistance;
	}

	/**
	 * 
	 * @param holeNumber
	 */
	public int getHolePar(int holeNumber) {
		return holePar[holeNumber - 1];
	}

	/**
	 * 
	 */
	public int[] getHoleIndexArray() {
		return holeIndex;
	}

	/**
	 * 
	 * @param holeNumber
	 */
	public int getHoleIndex(int holeNumber) {
		return holeIndex[holeNumber - 1];
	}

	/**
	 * 
	 */
	public String getTeeColour() {
		return teeColour;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfCourse#getHoleList()
	 */
	public List<CourseHole> getHoleList() {
		return holeList;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 18;
		return "GolfCourse [holePar="
				+ (holePar != null ? Arrays.toString(Arrays.copyOf(holePar,
						Math.min(holePar.length, maxLen))) : null)
				+ ", holeIndex="
				+ (holeIndex != null ? Arrays.toString(Arrays.copyOf(holeIndex,
						Math.min(holeIndex.length, maxLen))) : null) + "]";
	}

	// private List<CourseHole> populateHoleList(int[] holeParArray,
	// int[] holeIndexArray, int[] teeDistanceArray) {
	// List<CourseHole> holeList = new ArrayList<CourseHole>();
	// // for (int i = 0; i < holeParArray.length; i++) {
	// // holeList.add(new CourseHole("Hole " + (i + 1), holeParArray[i],
	// // holeIndexArray[i], teeDistanceArray[i]));
	// // }
	// return holeList;
	// }

}
