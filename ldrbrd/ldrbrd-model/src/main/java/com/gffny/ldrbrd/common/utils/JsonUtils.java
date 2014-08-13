package com.gffny.ldrbrd.common.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.google.gson.GsonBuilder;

/**
 * @author John D. Gaffney | gffny.com
 */
public class JsonUtils {

	/**
	 * @param pojo
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static String toJson(Object pojo) throws JsonMappingException, JsonGenerationException,
			IOException {
		return toJson(pojo, false);
	}

	/**
	 * @param pojo
	 * @param prettyPrint
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static String toJson(Object pojo, boolean prettyPrint) throws JsonMappingException,
			JsonGenerationException, IOException {
		return toJson(pojo, Object.class, prettyPrint);
	}

	/**
	 * @param pojo
	 * @param viewClass
	 * @param prettyPrint
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static String toJson(Object pojo, Class<?> viewClass, boolean prettyPrint) {
		GsonBuilder gsb = new GsonBuilder().serializeNulls();
		if (prettyPrint) {
			gsb.setPrettyPrinting();
		}
		return gsb.create().toJson(pojo);
		// return (new GsonBuilder().serializeNulls().create()).toJson(pojo);
	}
}