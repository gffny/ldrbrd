package com.gffny.ldrbrd.web.model.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.gffny.ldrbrd.web.model.JSONable;

/**
 * Simple base class for DTOs
 * 
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public class BaseDto implements JSONable {

	@JsonSerialize(include = Inclusion.NON_NULL)
	private String debugInfo;

	/**
	 * @return the debugInfo
	 */
	public String getDebugInfo() {
		return debugInfo;
	}

	/**
	 * @param debugInfo
	 *            the debugInfo to set
	 */
	public void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}

	public void populateDebugInfo() {
	}

	@JsonIgnore
	public String getAsJSON() throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}

}
