/**
 * 
 */
package com.gffny.ldrbrd.web.config;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author jdgaffney
 * 
 */
@ActiveProfiles("test")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring/applicationContext-persistence.xml",
		"classpath*:spring/applicationContext-model.xml",
		"classpath*:spring/applicationContext-webservices.xml",
		"classpath*:WEB-INF/ldrbrd-rest-servlet.xml",
		"classpath*:WEB-INF/web.xml" })
public abstract class WebAppConfigurationAware {

	@Inject
	protected WebApplicationContext wac;
	protected MockMvc mockMvc;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
}
