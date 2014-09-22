/**
 * 
 */
package com.gffny.ldrbrd.common.model.enums;

/**
 * @author John D. Gaffney | gffny.com
 */
public enum TeeColour {

	RED("tee.colour.red"), BLACK("tee.colour.black"), BLUE("tee.colour.blue"), GREEN(
			"tee.colour.green"), WHITE("tee.colour.white");

	/**
	 * 
	 */
	private String label;

	/**
	 * @param label
	 */
	TeeColour(final String label) {
		this.label = label;
	}

	/**
	 * @return
	 */
	public String getLabel() {
		return this.label;
	}
}
