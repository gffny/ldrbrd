package com.gffny.ldrbrd.common.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * @author jdgaffney
 * 
 */
public class JsonUtils {

	private static ObjectMapper m = new ObjectMapper();
	private static JsonFactory jf = new JsonFactory();
	public static final String EMPTY_ARRAY = "[]";
	public static final String EMPTY_OBJECT = "{}";

	/**
	 * 
	 */
	static {
		m.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		m.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}

	/**
	 * 
	 * @param jsonAsString
	 * @param pojoClass
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public static <T> T fromJson(String jsonAsString, Class<T> pojoClass)
			throws JsonMappingException, JsonParseException, IOException {
		return m.readValue(jsonAsString, pojoClass);
	}

	/**
	 * Convenience method for those situations where it not necessary to catch
	 * the exception and the result can be null.
	 * 
	 * @param <T>
	 * @param jsonAsString
	 * @param pojoClass
	 * @return
	 */
	public static <T> T fromJsonNullable(String jsonAsString, Class<T> pojoClass) {
		try {
			return fromJson(jsonAsString, pojoClass);
		} catch (Throwable ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param fr
	 * @param pojoClass
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public static <T> T fromJson(FileReader fr, Class<T> pojoClass)
			throws JsonParseException, IOException {
		return m.readValue(fr, pojoClass);
	}

	/**
	 * 
	 * @param is
	 * @param pojoClass
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public static <T> T fromJson(InputStream is, Class<T> pojoClass)
			throws JsonParseException, IOException {
		return m.readValue(is, pojoClass);
	}

	/*
	 * Bug in the javac compiler forces an explicit cast whereas the Eclipse
	 * compiler does not. This should be fixed in javac 1.7.0 -
	 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6302954.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonAsString, JavaType javaType)
			throws JsonMappingException, JsonParseException, IOException {

		return (T) m.readValue(jsonAsString, javaType);
	}

	/**
	 * 
	 * @param jsonAsString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> fromJsonToMap(String jsonAsString) {
		try {
			return (Map<String, Object>) m.readValue(jsonAsString,
					new TypeReference<HashMap<String, Object>>() {
					});
		} catch (Throwable ex) {
			return null;
		}
	}

	/**
	 * Convenience method for those situations where it not necessary to catch
	 * the exception and the result can be null.
	 * 
	 * Bug in the javac compiler forces an explicit cast whereas the Eclipse
	 * compiler does not. This should be fixed in javac 1.7.0 -
	 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6302954.
	 * 
	 * @param <T>
	 * @param jsonAsString
	 * @param javaType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJsonNullable(String jsonAsString, JavaType javaType) {
		try {
			return (T) fromJson(jsonAsString, javaType);
		} catch (Throwable ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param jsonAsString
	 * @param typeRef
	 * @return - type casted objects. this can be used to parse json string as
	 *         collections.
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonAsString, TypeReference<T> typeRef)
			throws JsonMappingException, JsonParseException, IOException {
		return (T) m.readValue(jsonAsString, typeRef);
	}

	/**
	 * 
	 * @param jsonAsString
	 * @param typeRef
	 * @return
	 */
	public static <T> T fromJsonNullable(String jsonAsString,
			TypeReference<T> typeRef) {
		try {
			return fromJson(jsonAsString, typeRef);
		} catch (Throwable ex) {
			return null;
		}
	}

	/**
	 * Convenience method for those situations where it not necessary to catch
	 * the exception and the result can be null.
	 * 
	 * @param pojo
	 * @return
	 */
	public static String toJsonNullable(Object pojo) {
		return toJsonNullable(pojo, false);
	}

	/**
	 * Convenience method for those situations where it not necessary to catch
	 * the exception and the result can be null.
	 * 
	 * @param pojo
	 * @param prettyPrint
	 * @return
	 */
	public static String toJsonNullable(Object pojo, boolean prettyPrint) {
		return toJsonNullable(pojo, Object.class, prettyPrint);
	}

	/**
	 * 
	 * @param pojo
	 * @param viewClass
	 * @param prettyPrint
	 * @return
	 */
	public static String toJsonNullable(Object pojo, Class<?> viewClass,
			boolean prettyPrint) {
		try {
			return toJson(pojo, viewClass, prettyPrint);
		} catch (Throwable ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param pojo
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static String toJson(Object pojo) throws JsonMappingException,
			JsonGenerationException, IOException {
		return toJson(pojo, false);
	}

	/**
	 * 
	 * @param pojo
	 * @param prettyPrint
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static String toJson(Object pojo, boolean prettyPrint)
			throws JsonMappingException, JsonGenerationException, IOException {
		return toJson(pojo, Object.class, prettyPrint);
	}

	/**
	 * 
	 * @param pojo
	 * @param viewClass
	 * @param prettyPrint
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static String toJson(Object pojo, Class<?> viewClass,
			boolean prettyPrint) throws JsonMappingException,
			JsonGenerationException, IOException {
		StringWriter sw = new StringWriter();
		JsonGenerator jg = jf.createJsonGenerator(sw);
		if (prettyPrint) {
			jg.useDefaultPrettyPrinter();
		}
		m.viewWriter(viewClass).writeValue(jg, pojo);
		return sw.toString();
	}

	/**
	 * 
	 * @param pojo
	 * @param fw
	 * @param prettyPrint
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws IOException
	 */
	public static void toJson(Object pojo, Writer fw, boolean prettyPrint)
			throws JsonMappingException, JsonGenerationException, IOException {
		JsonGenerator jg = jf.createJsonGenerator(fw);
		if (prettyPrint) {
			jg.useDefaultPrettyPrinter();
		}
		m.writeValue(jg, pojo);
	}

	/**
	 * 
	 * @param pojo
	 * @param os
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static void toJson(Object pojo, OutputStream os)
			throws JsonGenerationException, JsonMappingException, IOException {
		JsonGenerator jg = createJsonGenerator(os);
		m.writeValue(jg, pojo);
	}

	/**
	 * 
	 * @param w
	 * @return
	 */
	public static JsonGenerator createJsonGenerator(Writer w) {
		try {
			return jf.createJsonGenerator(w);
		} catch (IOException ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param os
	 * @return
	 */
	public static JsonGenerator createJsonGenerator(OutputStream os) {
		try {
			return jf.createJsonGenerator(os, JsonEncoding.UTF8);
		} catch (IOException ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param in
	 * @return
	 */
	public static JsonParser createJsonParser(InputStream in) {
		try {
			return jf.createJsonParser(in);
		} catch (IOException ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param in
	 * @return
	 */
	public static JsonParser createJsonParser(String in) {
		try {
			return jf.createJsonParser(in);
		} catch (IOException ex) {
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static ObjectMapper getObjectMapper() {
		return m;
	}
}