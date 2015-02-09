/**
 * 
 */
package com.gffny.ldrbrd.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author John D. Gaffney | gffny.com
 *
 */
@Configuration
public class AppConfig {

	@Bean
	public MongoConfig mongoConfig() {
		return new MongoConfig();
	}
}
