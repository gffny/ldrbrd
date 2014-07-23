/**
 * 
 */
package com.gffny.ldrbrd.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author John Gaffney (john@gffny.com) Oct 1, 2012
 * 
 */
public class TimeFunction {

	private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private static Calendar cal = Calendar.getInstance(TimeZone
			.getTimeZone("GMT"));

	/**
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseString(String date) throws ParseException {
		df.setCalendar(cal);
		df.setLenient(false);
		return df.parse(date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		df.setCalendar(cal);
		df.setLenient(false);
		return df.format(date);
	}

	/**
	 * 
	 * @return
	 */
	public static Date getCurrent() {
		return cal.getTime();
	}

	/**
	 * @param startDate
	 * @param string
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTeeTimeDate(Date roundDate, String teeTime)
			throws ParseException {
		df.setCalendar(cal);
		df.setLenient(false);
		teeTime += " ";
		teeTime += formatDate(roundDate);
		return (new SimpleDateFormat("HH:mm dd/MM/yyyy")).parse(teeTime);
	}
}
