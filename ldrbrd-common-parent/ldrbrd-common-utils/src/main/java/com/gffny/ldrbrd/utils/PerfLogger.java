package com.gffny.ldrbrd.utils;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public abstract class PerfLogger {

	private final static Logger logger = Logger.getLogger(PerfLogger.class);

	public static final ThreadLocal<SimpleDateFormat> LOG_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		}
	};

	public enum LogType {
		WEB, JDBC, QUERY, SOAP, RENDER
	}

	public static void log(Class<?> clazz, LogType logType, long milliseconds) {
		log(clazz.getName(), logType, milliseconds);
	}

	/**
	 * Logs performance data to the application-perf log, which is defined in
	 * the log4j.properties file. Because this data will eventually be parsed by
	 * the LogParserJob and entered into the database, we limit the name to 255
	 * characters. Most items will not be affected, but SQL queries could get
	 * truncated.
	 * 
	 * @param name
	 * @param logType
	 * @param milliseconds
	 */
	public static void log(String name, LogType logType, long milliseconds) {
		logger.info(logOutput(name, logType, milliseconds));
	}

	public static String logOutput(String name, LogType logType,
			long milliseconds) {
		StringBuilder log = new StringBuilder();
		log.append(StringUtils.substring(name, 0, 255));
		log.append(",");
		log.append(logType.name());
		log.append(",");
		log.append(milliseconds);

		return log.toString();
	}
}
