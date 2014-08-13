/**
 * 
 */
package com.gffny.ldrbrd.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author John D. Gaffney | gffny.com
 */
public class QueryUtils {

	/**
	 * @param param
	 * @return
	 */
	public static Map<String, Object> paramMap(String... param) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// check param validity (should be a list of pairs)
		if (param != null && param.length % 2 == 0) {
			// iterate through the pairs
			for (int i = 0; i < param.length; i += 2) {
				// add the pair
				paramMap.put(param[i], param[i + 1]);
			}
		}
		return paramMap;
	}

}
