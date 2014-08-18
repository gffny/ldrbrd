/**
 * 
 */
package com.gffny.ldrbrd.common.utils;

import com.gffny.ldrbrd.common.model.impl.Golfer;

/**
 * @author John D. Gaffney | gffny.com
 */
public class BootStrapUtils extends Object {

	/**
	 * Golfer used to bootstrap the application where components haven't been wired into the system
	 * quite at this time
	 * 
	 * @return Golfer(John Gaffney)
	 */
	public static Golfer golfer() {
		Golfer golfer = new Golfer();
		golfer.setId(1);
		golfer.setHandicap(23);
		return golfer;
	}

}
