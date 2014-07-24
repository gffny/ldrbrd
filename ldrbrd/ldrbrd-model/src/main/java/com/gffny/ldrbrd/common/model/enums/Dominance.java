/**
 * 
 */
package com.gffny.ldrbrd.common.model.enums;

/**
 * @author John D. Gaffney | gffny.com
 * 
 */
public enum Dominance {
	LEFT, RIGHT;

	public String getName() {
		// String temp = Dominance.class.getSimpleName() + "_" +
		// name();
		// return ResourcesUtil.getInstance().getProperty(temp);
		return name();
	}
}
