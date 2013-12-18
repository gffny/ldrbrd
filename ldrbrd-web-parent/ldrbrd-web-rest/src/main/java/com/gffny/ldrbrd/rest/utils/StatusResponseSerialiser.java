/**
 * 
 */
package com.gffny.ldrbrd.rest.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.gffny.ldrbrd.rest.resp.StatusResponse;

/**
 * @author jdgaffney
 *
 */
public class StatusResponseSerialiser extends JsonSerializer<StatusResponse> {

	@Override
	public void serialize(final StatusResponse value, final JsonGenerator jgen, 
			final SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeFieldName("statusCode");
		jgen.writeString(value.getStatusCode().toString());
		jgen.writeFieldName("statusMessage");
		jgen.writeString(value.getStatusMessage());
		jgen.writeFieldName("timestamp");
		jgen.writeNumber(System.currentTimeMillis());
		jgen.writeEndObject();
	}

}
