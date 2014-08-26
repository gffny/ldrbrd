/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author John D. Gaffney | gffny.com
 */
@RequestMapping(value = "/rest")
public class BaseRestController {

	/**
	 * this field will be used to log error, info, debug, etc for concrete implementations of this
	 * class. It is intended for use by transactional classes, such as service and repository
	 * classes.
	 */
	protected Logger LOG;

	/**
	 * @param logger
	 */
	public BaseRestController(Class<? extends BaseRestController> clas) {
		// initialize the LOG field with the concrete implementation of this
		// abstract class
		this.LOG = LoggerFactory.getLogger(clas);
	}
}
