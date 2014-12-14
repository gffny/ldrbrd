/**
 * 
 */
package test.gffny.ldrbrd.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.gffny.ldrbrd.web.config.WebAppConfigurationAware;

/**
 * @author jdgaffney
 * 
 */
public class CourseRestAPITest extends WebAppConfigurationAware {

	@Test
	public void test() throws Exception {
		mockMvc.perform(get("/check/c_online")).andExpect(status().isOk());
	}
}
