/**
 * 
 */
package test.gffny.ldrbrd.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.gffny.ldrbrd.web.config.WebAppConfigurationAware;

/**
 * @author John Gaffney | gffny.com
 * 
 */
public class ProfileRestAPITest extends WebAppConfigurationAware {

	@Test
	public void testProfileDisgest() throws Exception {
		mockMvc.perform(get("/profile/digest")).andExpect(status().isOk());
	}
}
