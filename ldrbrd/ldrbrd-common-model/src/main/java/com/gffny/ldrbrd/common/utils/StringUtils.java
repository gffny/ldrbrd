package com.gffny.ldrbrd.common.utils;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.WordUtils;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	private static final Pattern URL_PATTERN = Pattern
			.compile("((((https?|ftp):(//)+)|(www.))+[\\w\\.\\?\\=\\&\\-\\/~:;]*)");
	private static final Pattern STRIP_TAGS_PATTERN = Pattern
			.compile("\\<.*?>");

	public static final Comparator<String> STRING_TRIM_IGNORE_COMPARATOR = new Comparator<String>() {

		public int compare(String lhs, String rhs) {
			return StringUtils.trimToEmpty(lhs).compareToIgnoreCase(
					StringUtils.trimToEmpty(rhs));
		}
	};

	public static String nullAsEmpty(String s) {
		return (s == null) ? EMPTY : s;
	}

	public static int parseInteger(String s) {
		return parseInteger(s, 0);
	}

	public static int parseInteger(String s, int def) {
		try {
			return Integer.parseInt(s);
		} catch (Throwable ex) {
			return def;
		}
	}

	public static long parseLong(String s) {
		return parseLong(s, 0);
	}

	public static long parseLong(String s, long def) {
		try {
			return Long.parseLong(s);
		} catch (Throwable ex) {
			return def;
		}
	}

	public static double parseDouble(String s) {
		return parseDouble(s, 0);
	}

	public static double parseDouble(String s, double def) {
		try {
			return Double.parseDouble(s);
		} catch (Throwable ex) {
			return def;
		}
	}

	public static boolean parseBoolean(String s) {
		return parseBoolean(s, false);
	}

	public static boolean parseBoolean(String s, boolean def) {
		if (isEmpty(s))
			return def;

		s = s.toLowerCase();

		if (s.equals("y"))
			return true;

		if (s.equals("yes"))
			return true;

		if (s.equals("t"))
			return true;

		if (s.equals("true"))
			return true;

		if (s.equals("on"))
			return true;

		if (s.equals("1"))
			return true;

		return false;
	}

	public static boolean isEquivalent(String a, String b) {
		if ((a == null) && (b == null))
			return true;

		if ((a == null) || (b == null)) {
			if ((a == null) && "".equals(b))
				return true;

			return "".equals(a);
		}

		return a.equals(b);
	}

	public static boolean isNotEquivalent(String a, String b) {
		return !isEquivalent(a, b);
	}

	public static boolean isEquivalentCaseInsensitive(String a, String b) {
		return isEquivalent(lowerCase(a), lowerCase(b));
	}

	public static boolean isNotEquivalentCaseInsensitive(String a, String b) {
		return isNotEquivalent(lowerCase(a), lowerCase(b));
	}

	public static String stripNonNumeric(String a) {
		if (a == null)
			return a;

		return a.replaceAll("[^\\d]", "");
	}

	public static String joinPath(String a, String b) {
		return joinPath(a, b, "/");
	}

	public static String joinPath(String a, String b, String pathSeparator) {
		a = nullAsEmpty(a);
		b = nullAsEmpty(b);

		int n = 0;
		if (a != null && a.endsWith(pathSeparator))
			n++;
		if (b != null && b.startsWith(pathSeparator))
			n++;

		StringBuffer sb = new StringBuffer();
		sb.append(a);
		if (n == 0)
			sb.append(pathSeparator);
		if (n == 2)
			sb.append(b.substring(1));
		else
			sb.append(b);
		return sb.toString();
	}

	public static String join(Map<?, ?> map, String kvSeparator,
			String iSeparator, String keyIfNull, String valueIfNull) {
		if (map == null) {
			return null;
		}

		List<String> items = new ArrayList<String>(map.size());

		for (Entry<?, ?> entry : map.entrySet()) {
			String key = StringUtils.valueOf(entry.getKey(), keyIfNull);
			String value = StringUtils.valueOf(entry.getValue(), valueIfNull);
			items.add(key + kvSeparator + value);
		}

		return StringUtils.join(items, iSeparator);
	}

	/**
	 * Searches through a String of text and replace URL patterns with anchor
	 * tags.
	 * 
	 * http://nealvs.wordpress.com/2010/02/18/wrapreplace-url-text-with-anchor-
	 * tag-with-java-regex/
	 * 
	 * @param message
	 * @return
	 */
	public static String replaceUrls(String message) {

		if (message != null) {
			StringBuffer str = new StringBuffer(message.length());

			Matcher matcher = URL_PATTERN.matcher(message);

			// Replace urls with anchor tags to the url
			while (matcher.find()) {
				String url = matcher.group(0);
				String linkedUrl = url;

				if (!url.substring(0, 4).equalsIgnoreCase("http")
						&& !url.substring(0, 3).equalsIgnoreCase("ftp")
						&& !url.substring(0, 4).equalsIgnoreCase("file")) {
					linkedUrl = "http://" + url;
				}

				matcher.appendReplacement(
						str,
						Matcher.quoteReplacement("<a href=\"" + linkedUrl
								+ "\" target=\"_blank\" rel=\"nofollow\">"
								+ url + "</a>"));
			}
			matcher.appendTail(str);
			message = str.toString();
		}
		return message;
	}

	public static String makeSecureUrl(String url) {
		if (url.startsWith("https"))
			return url;

		return url.replaceFirst("^http", "https");
	}

	public static String encodeDataNullable(String json) {
		try {
			return Base64.encodeBase64String(
					json.getBytes(Charset.forName("UTF-8"))).replaceAll(
					"[\\s]*", "");
		} catch (Throwable e) {
			return Base64.encodeBase64String(JsonUtils.EMPTY_OBJECT.getBytes());
		}
	}

	public static String encodeChartData(String json) {
		try {
			return Base64.encodeBase64String(
					json.getBytes(Charset.forName("UTF-8"))).replaceAll(
					"[\\s]", "");
		} catch (Throwable e) {
			return Base64.encodeBase64String(JsonUtils.EMPTY_ARRAY.getBytes());
		}
	}

	public static String encodeChartData(Map<?, ?> data) {
		try {
			String json = JsonUtils.toJson(data, false);
			// return json;
			return Base64.encodeBase64String(
					json.getBytes(Charset.forName("UTF-8"))).replaceAll(
					"[\\s]", "");
		} catch (Throwable e) {
			return Base64.encodeBase64String(JsonUtils.EMPTY_OBJECT.getBytes());
		}
	}

	public static String encodeChartJson(String json) {
		return encodeChartData(json);
	}

	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}

	public static String escapeJavaScript(String text) {
		return StringEscapeUtils.escapeJavaScript(text);
	}

	// http://technologicaloddity.com/2010/03/16/strip-html-tags-in-java/
	public static String stripTags(String str) {
		if (str == null) {
			return null;
		}

		return STRIP_TAGS_PATTERN.matcher(str).replaceAll("");
	}

	public static String stripNewLines(String str) {
		return replaceAllNewLines(str, EMPTY);
	}

	public static String replaceAllNewLines(String str, String with) {
		if (str == null) {
			return null;
		}

		return str.replaceAll("\r\n|\r|\n", with);
	}

	public static boolean hasText(String str) {
		str = stripTags(str);
		return isNotBlank(str);
	}

	public static boolean hasNoText(String str) {
		return !hasText(str);
	}

	static public String firstNonBlank(String... strings) {
		if (strings.length > 0)
			return null;

		for (String string : strings) {
			if (StringUtils.isNotBlank(string)) {
				return string;
			}
		}

		return null;
	}

	public static String valueOf(Object object) {
		return valueOf(object, null);
	}

	public static String valueOf(Object object, String valueIfNull) {
		if (object != null) {
			if (object instanceof Collection<?>) {
				return "[" + StringUtils.join((Collection<?>) object, ',')
						+ "]";
			} else if (object instanceof Object[]) {
				return "[" + StringUtils.join((Object[]) object, ',') + "]";
			} else if (object instanceof Map<?, ?>) {
				return "["
						+ StringUtils.join((Map<?, ?>) object, "=>", ",",
								"null", "null") + "]";
			} else {
				return String.valueOf(object);
			}
		} else {
			return valueIfNull;
		}
	}

	public static String toCommaSeparatedString(Collection<String> collection) {
		StringBuilder result = new StringBuilder();

		if (collection != null) {
			for (String value : collection) {
				if (value != null)
					result.append(value).append(",");
			}

			if (result.length() > 1)
				return result.substring(0, result.length() - 1);
		}

		return result.toString();
	}

	public static String ensureHasSuffix(String source, String suffix) {
		if (source.endsWith(suffix))
			return source;

		return new StringBuilder(source).append(suffix).toString();
	}

	public static String titleCase(String s) {
		return WordUtils.capitalizeFully(s);
	}

	/**
	 * Prepares an input string containing a hyphenated value for display by
	 * replacing all hyphens with whitespace and capitalizing the first letter
	 * of the first word.
	 * 
	 * ex: 'tomato-basil-soup' becomes Tomato basil soup.
	 * 
	 * @param hypenatedString
	 * @return
	 */
	public static String prettyPrintHyphenatedValue(String hypenatedString) {
		return titleCase(hypenatedString.trim().replace('-', ' '));
	}

	public static boolean isSecuredUrl(String url) {
		if (url == null)
			return false;

		return url.startsWith("https");
	}

	public static char[] toCharArray(String string) {
		return (string != null) ? string.toCharArray() : null;
	}

	public static String format(final String tmpl, Object... vars) {
		return String.format(tmpl, vars);
	}

	public static String addQueryStringParam(final String tmpl, Object... vars) {
		return String.format(tmpl, vars);
	}

	public static Date convertToDate(String dateStr, String format) {
		try {
			DateFormat formatter;
			Date date;
			formatter = new SimpleDateFormat(format);
			date = (Date) formatter.parse(dateStr);
			return date;
		} catch (ParseException ex) {
			return null;
		}
	}

	public static String convertToIdentifier(String str) {
		return str.replaceAll("[^a-zA-Z0-9_-]", "_");
	}

}
