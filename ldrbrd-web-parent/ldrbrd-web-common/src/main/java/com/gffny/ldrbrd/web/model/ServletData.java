/**
 * 
 */
package com.gffny.ldrbrd.web.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


import com.gffny.ldrbrd.utils.ApplicationConfiguration;
import com.gffny.ldrbrd.utils.DateUtils;
import com.gffny.ldrbrd.utils.JsonUtils;
import com.gffny.ldrbrd.utils.Locale;
import com.gffny.ldrbrd.utils.MapUtils;
import com.gffny.ldrbrd.utils.StringUtils;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public class ServletData {

	/**
	 * 
	 */
	protected final static String HEADER_AJAX_REQUEST = "X-Requested-With";

	/**
	 * 
	 */
	protected final static String HEADER_AJAX_REQUEST_VALUE = "XMLHttpRequest";

	/**
	 * 
	 */
	protected final static String HEADER_SESSION_INVALID = "MWP-SESSION-INVALID";

	/**
	 * 
	 */
	protected final static String OBLIX_SSO_COOKIE_NAME = "ObSSOCookie";

	/**
	 * 
	 */
	protected final static String[] OBLIX_COOKIE_NAMES = new String[] {
			OBLIX_SSO_COOKIE_NAME, "ObTEMC" };

	/**
	 * 
	 */
	protected final static String LOCALE_COOKIE_TMPL = "locale-%s";

	/**
	 * 
	 */
	protected final Logger logger = Logger.getLogger(ServletData.class);

	/**
	 * 
	 */
	private HttpServletRequest _request = null;

	/**
	 * 
	 */
	private HttpServletResponse _response = null;

	/**
	 * 
	 */
	private Map<String, Object> _params = new HashMap<String, Object>();

	/**
	 * 
	 */
	private URLCodec encoder = new URLCodec();

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public ServletData(HttpServletRequest request, HttpServletResponse response) {
		_request = request;
		_response = response;
	}

	/**
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return _response;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public String[] getStringParameters(String param) {
		return getStringParameters(param, null);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public String[] getStringParameters(String param, String def) {
		String[] value = getParameterValues(param);

		if ((value == null) && (def != null)) {
			value = new String[] { def };
		}

		return value;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public String getStringParameter(String param) {
		return getStringParameter(param, null);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public String getStringParameter(String param, String def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return value;
	}

	/**
	 * 
	 * @return
	 */
	public String getQueryString() {
		return getRequest().getQueryString();
	}

	/**
	 * 
	 * @return
	 */
	public String getRequestURI() {
		return getRequest().getRequestURI();
	}

	/**
	 * 
	 * @return
	 */
	public String getServletPath() {
		return getRequest().getServletPath();
	}

	/**
	 * 
	 * @return
	 */
	public String getPathInfo() {
		return getRequest().getPathInfo();
	}

	/**
	 * 
	 * @return
	 */
	public String getContextFreeRequestURI() {
		return StringUtils.removeStart(getRequestURI(), getContextPath());
	}

	/**
	 * 
	 * @return
	 */
	public String getFullRequestURI() {
		StringBuilder sb = new StringBuilder(getRequestURI());
		if (StringUtils.isNotEmpty(getQueryString()))
			sb.append("?").append(getQueryString());

		return sb.toString();
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public int getIntegerParameter(String param) {
		return getIntegerParameter(param, 0);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public int getIntegerParameter(String param, int def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return StringUtils.parseInteger(value, def);
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public long getLongParameter(String param) {
		return getLongParameter(param, 0);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public long getLongParameter(String param, long def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return StringUtils.parseLong(value, def);
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public Long getNullableLongParameter(String param) {
		return getNullableLongParameter(param, null);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public Long getNullableLongParameter(String param, Long def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		try {
			return Long.parseLong(value);
		} catch (Throwable ex) {
			return def;
		}
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public double getDoubleParameter(String param) {
		return getDoubleParameter(param, 0);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public double getDoubleParameter(String param, double def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return StringUtils.parseDouble(value, def);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean getBooleanParameter(String name) {
		return getBooleanParameter(name, false);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public boolean getBooleanParameter(String param, boolean def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return StringUtils.parseBoolean(value, def);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Date getDateParameter(String name) {
		Date returnDate = null;

		try {
			String value = getStringParameter(name);
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			returnDate = df.parse(value);
		} catch (ParseException ex) {
			logger.info("ReturnDate ex: " + returnDate);
		}

		return returnDate;
	}

	/**
	 * 
	 * @param name
	 * @param parsePattern
	 * @return
	 */
	public Date getDateParameter(String name, String parsePattern) {
		String value = getStringParameter(name);

		return DateUtils.parseDateOrNull(value, parsePattern);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Date getTimestampParameter(String name) {
		String value = getStringParameter(name);

		if (StringUtils.isBlank(value))
			return null;

		long timestamp = StringUtils.parseLong(value, -1);

		if (timestamp < 0)
			return null;

		Date returnDate = new Date(timestamp);

		return returnDate;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public String getParameter(String param) {
		String value = getRequest().getParameter(param);

		if (value != null)
			return value;

		String[] mValue = (String[]) _params.get(param);
		if ((mValue != null) && (mValue.length > 0))
			value = mValue[0];

		return value;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public String getCapitalizedParamter(String param) {
		String p = getParameter(param);
		if (p != null)
			p = p.toUpperCase();

		return p;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public String[] getParameterValues(String param) {
		String[] value = getRequest().getParameterValues(param);
		if (value != null)
			return value;

		value = (String[]) _params.get(param);

		return value;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Enumeration<String> getParameterNames() {
		Enumeration<String> values = getRequest().getParameterNames();
		if (values != null)
			return values;

		return Collections.enumeration(_params.keySet());
	}

	/**
	 * 
	 * @param prefix
	 * @return
	 */
	public String getParameterNameStartingWith(String prefix) {
		Enumeration<String> paramNames = getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if (paramName.startsWith(prefix)) {
				return paramName;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public List<String> getListParameterValues(String param) {
		List<String> result = new ArrayList<String>();

		String[] values = getParameterValues(param);
		if (values == null)
			return result;

		for (int i = 0; i < values.length; i++)
			result.add(values[i]);

		return result;
	}

	/**
	 * @param key
	 * @return true when the parameter is not null and parameter's value isn't
	 *         empty false otherwise
	 */
	public boolean hasParameter(String key) {
		if (StringUtils.isNotBlank(getParameter(key))) {
			return true;
		}

		if (MapUtils.containsKey(_params, key)) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addParameter(String key, String value) {
		String[] mValue = (String[]) _params.get(key);
		if (mValue == null)
			mValue = new String[0];

		String[] newValue = new String[mValue.length + 1];
		System.arraycopy(mValue, 0, newValue, 0, mValue.length);
		newValue[mValue.length] = value;

		_params.put(key, newValue);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setParameter(String key, String value) {
		setParameter(key, new String[] { value });
	}

	/**
	 * 
	 * @param key
	 * @param values
	 */
	public void setParameter(String key, String[] values) {
		_params.put(key, values);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasAttribute(String key) {
		return getAttribute(key) != null;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public Object getAttribute(String param) {
		return getRequest().getAttribute(param);
	}

	/**
	 * 
	 * @param param
	 */
	public void removeAttribute(String param) {
		getRequest().removeAttribute(param);
	}

	/**
	 * 
	 * @param param
	 * @param e
	 */
	public void setAttribute(String param, Object e) {
		getRequest().setAttribute(param, e);
	}

	/**
	 * 
	 * @param param
	 * @param i
	 */
	public void setAttribute(String param, int i) {
		Integer value = new Integer(i);
		setAttribute(param, value);
	}

	/**
	 * 
	 * @param param
	 * @param l
	 */
	public void setAttribute(String param, long l) {
		Long value = new Long(l);
		setAttribute(param, value);
	}

	/**
	 * 
	 * @param param
	 * @param d
	 */
	public void setAttribute(String param, double d) {
		Double value = new Double(d);
		setAttribute(param, value);
	}

	/**
	 * 
	 * @param param
	 * @param b
	 */
	public void setAttribute(String param, boolean b) {
		Boolean value = new Boolean(b);
		setAttribute(param, value);
	}

	/**
	 * 
	 * @param attributes
	 */
	public void setAttributes(HashMap<String, Object> attributes) {
		if (attributes == null)
			return;

		Iterator<String> keySet = attributes.keySet().iterator();
		while (keySet.hasNext()) {
			try {
				String key = keySet.next();
				Object value = attributes.get(key);
				// kludge - mimics the functionality of setAttribute(String,
				// boolean)
				value = (value instanceof Boolean) ? value.toString() : value;
				setAttribute(key, value);
			} catch (Throwable ex) {
				logger.error("setAttributes(HashMap) - ex: " + ex);
			}
		}
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public int getIntegerAttribute(String param) {
		return getIntegerAttribute(param, 0);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public int getIntegerAttribute(String param, int def) {
		try {
			return ((Integer) getAttribute(param)).intValue();
		} catch (Throwable ex) {
			return def;
		}
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public long getLongAttribute(String param) {
		return getLongAttribute(param, 0);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public long getLongAttribute(String param, long def) {
		try {
			return ((Long) getAttribute(param)).longValue();
		} catch (Throwable ex) {
			return def;
		}
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean getBooleanAttribute(String name) {
		return getBooleanAttribute(name, false);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public boolean getBooleanAttribute(String param, boolean def) {
		try {
			return ((Boolean) getAttribute(param)).booleanValue();
		} catch (Throwable ex) {
			return def;
		}
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public String getStringAttribute(String param) {
		return getStringAttribute(param, null);
	}

	/**
	 * 
	 * @param param
	 * @param def
	 * @return
	 */
	public String getStringAttribute(String param, String def) {
		String value = (String) getAttribute(param);

		if (value == null)
			return def;

		return value;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public Date getDateAttribute(String param) {
		return getDateAttribute(param, null);
	}

	/**
	 * 
	 * @param key
	 * @param def
	 * @return
	 */
	public Date getDateAttribute(String key, Date def) {
		if (!hasAttribute(key))
			return def;

		return (Date) getAttribute(key);
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public String getHeader(String param) {
		return getRequest().getHeader(param);
	}

	/**
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return _request;
	}

	/**
	 * 
	 * @param request
	 */
	public void setRequest(HttpServletRequest request) {
		_request = request;
	}

	/**
	 * 
	 * @return
	 */
	public String getContextPath() {
		return getRequest().getContextPath();
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public String getRelativeUrl(String uri) {
		return StringUtils.joinPath(getContextPath(), uri);
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public boolean isRelativeUrl(String url) {
		if (StringUtils.isEmpty(url))
			return false;

		return (url.startsWith(getContextPath()));
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public String getAbsoluteUrl(String uri) {
		return StringUtils.joinPath(getRequestPrefix(true), uri);
	}

	/**
	 * 
	 * @return
	 */
	public String getRequestPrefix() {
		return getRequestPrefix(false);
	}

	/**
	 * 
	 * @param includeContext
	 * @return
	 */
	public String getRequestPrefix(boolean includeContext) {
		String protocol = getRequest().getScheme();
		String serverName = getRequest().getServerName();
		int port = getRequest().getServerPort();

		if (port < 0) {
			port = 80;
		}

		StringBuilder prefix = new StringBuilder();

		prefix.append(protocol).append("://").append(serverName);

		if (port != 80 && port != 443) {
			prefix.append(":").append(port);
		}

		if (includeContext) {
			prefix.append(getRequest().getContextPath());
		}

		return prefix.toString();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRequestSecure() {
		return StringUtils.endsWith(getRequest().getScheme(), "s");
	}

	/**
	 * 
	 * @return
	 */
	public String getContentType() {
		return getRequest().getContentType();
	}

	/**
	 * 
	 * @return
	 */
	public String getMethod() {
		return getRequest().getMethod();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPost() {
		if (StringUtils.equalsIgnoreCase("POST", getMethod()))
			return true;

		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isMultipartRequest() {
		String contentType = getContentType();
		if (contentType != null
				&& contentType.startsWith("multipart/form-data"))
			return true;

		return false;
	}

	/**
	 * 
	 * @param s
	 */
	public void setContentType(String s) {
		getResponse().setContentType(s);
	}

	/**
	 * 
	 * @param i
	 */
	public void setContentLength(int i) {
		getResponse().setContentLength(i);
	}

	/**
	 * 
	 */
	public void setHtmlContentType() {
		setContentType("text/html");
	}

	/**
	 * 
	 */
	public void setExcelContentType() {
		setContentType("application/vnd.ms-excel");
	}

	/**
	 * 
	 */
	public void setPdfContentType() {
		setContentType("application/pdf");
	}

	/**
	 * 
	 */
	public void setRtfContentType() {
		setContentType("application/rtf");
	}

	/**
	 * 
	 */
	public void setXmlContentType() {
		setContentType("application/xml");
	}

	/**
	 * 
	 */
	public void setJsonContentType() {
		setContentType("application/json");
	}

	/**
	 * 
	 */
	public void setBrowserNoCacheHeader() {
		addHeader("Cache-Control", "no-store");
		addHeader("Pragma", "no-cache"); // for legacy browsers
	}

	/**
	 * 
	 * @return
	 */
	public List<Cookie> getCookies() {
		Cookie[] cookies = getRequest().getCookies();

		if (cookies == null)
			return new ArrayList<Cookie>();

		return Arrays.asList(cookies);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Cookie getCookie(String name) {
		Iterator<Cookie> i = getCookies().iterator();

		while (i.hasNext()) {
			Cookie c;
			c = i.next();

			if (c.getName().equals(name))
				return c;
		}

		return null;
	}

	/**
	 * 
	 * @param name
	 * @param def
	 * @return
	 */
	public String getCookieValue(String name, String def) {
		Iterator<Cookie> i = getCookies().iterator();

		while (i.hasNext()) {
			Cookie c;
			c = i.next();

			if (c.getName().equals(name))
				return c.getValue();
		}

		return def;
	}

	/**
	 * 
	 * @param name
	 * @param def
	 * @return
	 */
	public int getIntegerCookieValue(String name, int def) {
		return StringUtils.parseInteger(getCookieValue(name, null), def);
	}

	/**
	 * 
	 * @param name
	 * @param def
	 * @return
	 */
	public boolean getBooleanCookieValue(String name, boolean def) {
		return StringUtils.parseBoolean(getCookieValue(name, null), def);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasCookie(String name) {
		return getCookie(name) != null;
	}

	/**
	 * 
	 * @param cookieName
	 */
	public void removeCookie(String cookieName) {
		Cookie c = getCookie(cookieName);
		if (c != null) {
			c.setMaxAge(0);
			c.setPath("/");
			addCookie(c);
		}
	}

	/**
	 * 
	 * @param c
	 */
	public void removeCookie(Cookie c) {
		c.setMaxAge(0);
		c.setPath("/");
		addCookie(c);
	}

	/**
	 * 
	 * @param c
	 */
	public void addCookie(Cookie c) {
		getResponse().addCookie(c);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void addCookie(String name, String value) {
		int oneYear = 365 * 24 * 60 * 60;
		addCookie(name, value, oneYear);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void addCookie(String name, long value) {
		int oneYear = 365 * 24 * 60 * 60;
		addCookie(name, Long.toString(value), oneYear);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void addCookie(String name, boolean value) {
		int oneYear = 365 * 24 * 60 * 60;
		addCookie(name, Boolean.toString(value), oneYear);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void addSessionCookie(String name, String value) {
		addCookie(name, value, -1);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @param age
	 */
	public void addCookie(String name, String value, int age) {
		Cookie c = new Cookie(name, value);
		c.setMaxAge(age);
		c.setPath("/");
		addCookie(c);
	}

	/**
	 * 
	 * @return
	 */
	public String getReferer() {
		return getHeader("Referer");
	}

	/**
	 * 
	 */
	public void refreshReferer() {
		redirectRequest(getReferer());
	}

	/**
	 * 
	 * @param url
	 */
	public void redirectRequest(String url) {
		redirectRequest(url, false);
	}

	/**
	 * 
	 * @param url
	 * @param addContext
	 */
	public void redirectRequest(String url, boolean addContext) {
		try {
			if (addContext) {
				url = StringUtils.joinPath(getContextPath(), url);
			}

			getResponse().sendRedirect(url);
			getResponse().flushBuffer();
		} catch (IOException ex) {
			logger.error("redirectRequest() - ex: " + ex);
		}
	}

	/**
	 * 
	 * @param url
	 */
	public void forwardRequest(String url) {
		try {
			RequestDispatcher rd = getRequest().getRequestDispatcher(url);
			rd.forward(getRequest(), getResponse());
		} catch (Throwable ex) {
			logger.error("forwardRequest() - ex: " + ex);
		}
	}

	/**
	 * 
	 * @param header
	 * @return
	 */
	public boolean containsHeader(String header) {
		return getResponse().containsHeader(header);
	}

	/**
	 * 
	 * @param header
	 * @param value
	 */
	public void addHeader(String header, String value) {
		getResponse().addHeader(header, value);
	}

	/**
	 * 
	 * @param header
	 * @param value
	 */
	public void setHeader(String header, String value) {
		getResponse().setHeader(header, value);
	}

	/**
	 * 
	 * @param data
	 */
	public void writeData(String data) {
		try {
			PrintWriter out = getResponse().getWriter();
			out.println(data);

		} catch (Throwable ex) {
			logger.error("Unable to write data to the response.");
		}
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, String[]> getParameterMap() {
		HashMap<String, String[]> properties = new HashMap<String, String[]>();
		Enumeration<String> names = null;
		names = getParameterNames();

		while (names.hasMoreElements()) {
			String name = names.nextElement();
			properties.put(name, getParameterValues(name));
		}

		return properties;
	}

	/**
	 * 
	 */
	public void setResponseInvalidRequestHeader() {
		addHeader(HEADER_SESSION_INVALID, "true");
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAjaxRequest() {
		if (getHeader(HEADER_AJAX_REQUEST) != null) {
			if (getHeader(HEADER_AJAX_REQUEST)
					.equals(HEADER_AJAX_REQUEST_VALUE)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param locale
	 */
	public void storeLocaleCookie(Locale locale) {
		// addSessionCookie(String.format(LOCALE_COOKIE_TMPL,
		// RequestContext.get()
		// .getOrgUnitId()), locale.getLocale_symbol());
	}

	/**
	 * 
	 */
	public void removeLocaleCookie() {
		// removeCookie(String.format(LOCALE_COOKIE_TMPL, RequestContext.get()
		// .getOrgUnitId()));
	}

	/**
	 * 
	 * @param cookieName
	 * @param c
	 * @return
	 */
	public <T> T getContextFromCookie(String cookieName, Class<T> c) {
		try {
			Cookie cookie = getCookie(cookieName);
			String cookieValue = encoder.decode(cookie.getValue());
			String json = new String(Base64.decodeBase64(cookieValue));
			logger.info("context json:" + json);
			return JsonUtils.fromJson(json, c);
		} catch (Throwable ex) {
			try {
				return c.newInstance();
			} catch (Throwable e) {
				return null;
			}
		}
	}

	/**
	 * 
	 * @param cookieName
	 * @param c
	 * @return
	 */
	public <T> T getContextFromCookieNoDefault(String cookieName, Class<T> c) {
		try {
			Cookie cookie = getCookie(cookieName);
			String cookieValue = encoder.decode(cookie.getValue());
			String json = new String(Base64.decodeBase64(cookieValue));
			logger.info("context json:" + json);
			return JsonUtils.fromJson(json, c);
		} catch (Throwable ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param name
	 * @param context
	 */
	public <T> void storeContextCookie(String name, T context) {
		try {
			String value = encoder.encode(Base64.encodeBase64String(JsonUtils
					.toJson(context, false).getBytes()));
			logger.info("writing value:" + value);
			addSessionCookie(name, value);
		} catch (Throwable ex) {
			logger.error("Unable to write user cookie: " + ex);
		}
	}

	/**
	 * 
	 * @param name
	 * @param context
	 * @param ttl
	 */
	public <T> void storeLimitedTTLContextCookie(String name, T context, int ttl) {
		try {
			String value = encoder.encode(Base64.encodeBase64String(JsonUtils
					.toJson(context, false).getBytes()));
			logger.info("writing value:" + value);
			addCookie(name, value, ttl);
		} catch (Throwable ex) {
			logger.error("Unable to write user cookie: " + ex);
		}
	}

	/**
	 * 
	 * @param isLoggedIn
	 * @return
	 */
	public boolean hasValidOblixCookies(boolean isLoggedIn) {
		logger.info(" checking for valid OblixCookies");
		for (String cookieName : OBLIX_COOKIE_NAMES) {
			Cookie cookie = getCookie(cookieName);
			if (cookie != null) {
				logger.info(" has cookie: " + cookieName);
				if (isLoggedIn) {
					// ObSSO Cookie still exists after logout, so if we want to
					// check to
					// see if a user may still be logged in the cookie value
					// cannot be 'loggedout'
					logger.info(" cookie value [" + cookie.getValue() + "]");
					if (cookieName.equals(OBLIX_SSO_COOKIE_NAME)
							&& !cookie.getValue().equals("loggedout")) {
						return true;
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 */
	public void removeOamCookies() {
		for (String cookieName : OBLIX_COOKIE_NAMES) {
			Cookie cookie = getCookie(cookieName);
			if (cookie != null)
				removeCookie(cookie);
		}
	}

	/**
	 * 
	 * @return
	 */
	private String getTestGolferName() {
		String cookieName = "local.test.golfer";
		String golferName = getParameter("test.golfer.name");
		if (StringUtils.isNotEmpty(golferName)) {
			addSessionCookie(cookieName, golferName);
			return golferName;
		}
		Cookie cookie = getCookie(cookieName);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	private String getTestGolferId() {

		return ApplicationConfiguration.getTestGolferId();
		/*
		 * String cookieName = "local.test.golfer.id"; String golferId =
		 * getParameter("test.golfer.id");
		 * 
		 * if (StringUtils.isNotEmpty(golferId)) { addSessionCookie(cookieName,
		 * golferId); return golferId; }
		 * 
		 * Cookie cookie = getCookie(cookieName); if (cookie != null) { return
		 * cookie.getValue(); }
		 */
	}

	/**
	 * 
	 * @return
	 */
	public String getTestCompetitionRoundId() {
		return new String("123");
		// return getParameter("test.competition.round.id");
	}

	/**
	 * 
	 */
	public void removeLocalCookies() {
		Cookie cookie = getCookie("local-testid");
		if (cookie != null)
			removeCookie(cookie);

		cookie = getCookie("local-testuser");
		if (cookie != null)
			removeCookie(cookie);
	}

	/**
	 * 
	 */
	private ApplicationContext _context;

	/**
	 * 
	 * @return
	 */
	public ApplicationContext getWebApplicationContext() {
		if (_context == null)
			_context = (ApplicationContext) getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		return _context;
	}

	/**
	 * 
	 * @param context
	 */
	public void setWebApplicationContext(ApplicationContext context) {
		_context = context;
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public String getLink(String uri) {
		return StringUtils.joinPath(getContextPath(), uri);
	}

	/**
	 * 
	 */
	public void setReturningContext() {
		setAttribute("returning-context", true);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isReturningContext() {
		return hasAttribute("returning-context");
	}

	/**
	 * 
	 * @return
	 */
	public String getGolferId() {
		if (ApplicationConfiguration.useMockEnvironment()) {
			return getTestGolferId();
		}
		String key = ApplicationConfiguration.getGolferIdHeaderName();
		return getHeader(key);
	}

	/**
	 * 
	 * @return
	 */
	public String getGolferName() {
		if (ApplicationConfiguration.useMockEnvironment()) {
			return getTestGolferName();
		}
		String key = ApplicationConfiguration.getGolferNameHeaderName();
		return getHeader(key);
	}

	/**
	 * 
	 * @return
	 */
	public String getCompetitionRoundId() {
		if (ApplicationConfiguration.useMockEnvironment()) {
			return getTestCompetitionRoundId();
		}
		String key = ApplicationConfiguration.getCompetitionRoundIdHeaderName();
		return getHeader(key);
	}

	/**
	 * 
	 * @param userLocaleFromDb
	 * @return
	 */
	public Locale getLocale(Locale userLocaleFromDb) {
		if (userLocaleFromDb != null) {
			return userLocaleFromDb;
		}
		return Locale.en_US;
	}
}