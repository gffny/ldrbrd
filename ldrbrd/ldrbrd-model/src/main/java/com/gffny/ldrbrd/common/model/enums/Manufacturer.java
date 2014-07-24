/**
 * 
 */
package com.gffny.ldrbrd.common.model.enums;

/**
 * @author John D. Gaffney | gffny.com
 * 
 */
public enum Manufacturer {
	GENERIC, // only for clubs that are assigned to the user upon profile set up
	NIKE, MIZUNO, PING, CLEVELAND; // TODO add more club manufacturers

	/**
	 * 
	 * @return
	 */
	public static Manufacturer getDefault() {
		return GENERIC;
	}

}
