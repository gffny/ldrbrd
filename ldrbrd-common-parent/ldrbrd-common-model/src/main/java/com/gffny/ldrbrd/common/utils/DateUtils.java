package com.gffny.ldrbrd.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Years;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	public static final ThreadLocal<SimpleDateFormat> TIME_FORMAT_TL = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HHmm");
		}
	};

	public static final ThreadLocal<SimpleDateFormat> OUTPUT_MONTH_DAY_TIME_FORMAT_TL = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("MMMM d 'at' h:mm a");
		}
	};

	public static final ThreadLocal<SimpleDateFormat> FULL_DATE_TIME_FORMAT_TL = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("MMM dd, yyyy HH:mm:ss z");
		}
	};

	public static final ThreadLocal<SimpleDateFormat> FULL_DATE_TIME_FORMAT_SSO = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");
		}
	};

	public static final FastDateFormat CHART_DATE_FORMAT = FastDateFormat.getInstance("MM/dd/yyyy");

	public static final FastDateFormat MYSQL_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
	
	public static final FastDateFormat LOG_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

	public static final int DAYS_PER_WEEK = 7;

	private static String WEEK = "week";
	private static String MONTH = "month";
	private static String YEAR = "year";

	public static final Comparator<Date> SAME_DAY_COMPARATOR = new Comparator<Date>() {
		
		public int compare(Date lhs, Date rhs) {
			if (DateUtils.isSameDay(lhs, rhs)) {
				return 0;
			}

			return lhs.compareTo(rhs);
		}
	};

	public static Calendar toCalendar(Date date) {
		if (date == null) {
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c;
	}

	/**
	 * <p>
	 * Checks if two date objects are in the same year ignoring time.
	 * </p>
	 * 
	 * <p>
	 * 28 Mar 2002 13:45 and 12 Mar 2002 06:01 would return true. 28 Mar 2002
	 * 13:45 and 12 Mar 2005 02:45 would return false.
	 * </p>
	 * 
	 * @param date1
	 *            the first date, not altered, not null
	 * @param date2
	 *            the second date, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either date is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameYear(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameYear(cal1, cal2);
	}

	/**
	 * <p>
	 * Checks if two calendar objects are in the same year ignoring time.
	 * </p>
	 * 
	 * <p>
	 * 28 Mar 2002 13:45 and 12 Mar 2002 06:01 would return true. 28 Mar 2002
	 * 13:45 and 12 Mar 2005 02:45 would return false.
	 * </p>
	 * 
	 * @param cal1
	 *            the first calendar, not altered, not null
	 * @param cal2
	 *            the second calendar, not altered, not null
	 * @return true if they represent the same day
	 * @throws IllegalArgumentException
	 *             if either calendar is <code>null</code>
	 * @since 2.1
	 */
	public static boolean isSameYear(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The calendar must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1
				.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
	}

	public static boolean safeIsSameDay(Date date1, Date date2) {
		try {
			return DateUtils.isSameDay(date1, date2);
		} catch (Throwable ex) {
			return false;
		}
	}

	public static boolean safeIsSameDay(Calendar cal1, Calendar cal2) {
		try {
			return DateUtils.isSameDay(cal1, cal2);
		} catch (Throwable ex) {
			return false;
		}
	}

	/**
	 * Useful for determining if a date is between the start and end date (or
	 * effective and expiration date).
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isTodayInRange(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return false;
		}

		return isDateInRange(Calendar.getInstance().getTime(), startDate,
				DateUtils.addDays(endDate, 1));
	}

	public static boolean isDateNotInRange(Date date, Date startDate,
			Date endDate) {
		return !isDateInRange(date, startDate, endDate);
	}

	public static boolean isDateInRange(Date date, Date startDate, Date endDate) {
		if (date == null || startDate == null || endDate == null) {
			return false;
		}

		endDate = DateUtils.getEndOfDay(endDate);

		if (startDate.after(date)) {
			return false;
		}

		if (endDate.before(date)) {
			return false;
		}

		return true;
	}

	public static boolean isDateInRangeOrNull(Date date, Date startDate,
			Date endDate) {
		if (date == null) {
			return false;
		}

		if (startDate != null) {
			if (date.before(startDate)) {
				return false;
			}
		}

		if (endDate != null) {
			if (date.after(endDate)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isFutureDate(Date date) {
		if (date == null) {
			return false;
		}

		Date today = DateUtils.truncate(Calendar.getInstance(), Calendar.DATE)
				.getTime();
		if (date.after(today)) {
			return true;
		}

		return false;
	}

	public static boolean isPastDate(Date date) {
		if (date == null) {
			return false;
		}

		if (isToday(date)) {
			return false;
		}

		Date today = DateUtils.truncate(Calendar.getInstance(), Calendar.DATE)
				.getTime();
		if (date.before(today)) {
			return true;
		}

		return false;
	}

	public static boolean isToday(Date date) {
		if (date == null) {
			return false;
		}

		return isSameDay(date, new Date());
	}

	public static boolean isNotToday(Date date) {
		return !isToday(date);
	}

	public static boolean isAfter(Date startDate, Date endDate) {
		if (startDate == null) {
			return false;
		}

		if (endDate == null) {
			return false;
		}

		return endDate.after(startDate);
	}

	public static boolean isBefore(Date startDate, Date endDate) {
		if (startDate == null) {
			return false;
		}

		if (endDate == null) {
			return false;
		}

		return endDate.before(startDate);
	}

	public static boolean isAfterToday(Date date) {
		return DateUtils.isAfter(new Date(), date)
				&& DateUtils.isNotToday(date);
	}

	public static boolean isYesterday(Date date) {
		if (date == null) {
			return false;
		}

		return isSameDay(date, addDays(-1));
	}

	public static boolean isThisWeek(Date date) {
		if (date == null) {
			return false;
		}

		DateTime dt = new DateTime(date);
		DateTime current = new DateTime();

		return dt.getYear() == current.getYear()
				&& dt.getWeekOfWeekyear() == current.getWeekOfWeekyear();
	}

	public static boolean isThisMonth(Date date) {
		if (date == null) {
			return false;
		}

		DateTime dt = new DateTime(date);
		DateTime current = new DateTime();

		return dt.getYear() == current.getYear()
				&& dt.getMonthOfYear() == current.getMonthOfYear();
	}

	public static boolean isThisYear(Date date) {
		if (date == null) {
			return false;
		}

		return isSameYear(date, new Date());
	}

	public static Date addDays(int days) {
		return addDays(new Date(), days);
	}

	public static Date addWeeks(int weeks) {
		return addWeeks(new Date(), weeks);
	}

	public static Date addMonths(int months) {
		return addMonths(new Date(), months);
	}

	public static Date addHours(int hours) {
		return addHours(new Date(), hours);
	}

	public static Date addMinutes(int minutes) {
		return addMinutes(new Date(), minutes);
	}

	/*
	 * public static Date maxNotNull(Date... dates) { Date max = null;
	 * 
	 * if (CollectionUtils.isNotEmpty(dates)) { for (Date date : dates) { if
	 * (date == null) continue;
	 * 
	 * if (max == null || date.after(max)) { max = date; } } }
	 * 
	 * return clone(max); }
	 * 
	 * public static Date minNotNull(Date... dates) { Date min = null;
	 * 
	 * if (CollectionUtils.isNotEmpty(dates)) { for (Date date : dates) { if
	 * (date == null) { continue; }
	 * 
	 * if (min == null || date.before(min)) { min = date; } } }
	 * 
	 * return clone(min); }
	 */

	public static Date clone(Date date) {
		if (date == null)
			return null;

		return (Date) date.clone();
	}

	public static Date getToday() {
		return getBeginningOfDay(new Date());
	}

	public static Date getYesterday() {
		return getBeginningOfDay(addDays(-1));
	}

	public static Date getTomorrow() {
		return getBeginningOfDay(addDays(1));
	}

	public static Date getBeginningOfDay(Date date) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date getEndOfDay(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return getEndOfDay(cal).getTime();
	}

	public static Calendar getEndOfDay(Calendar input) {
		if (input == null) {
			return null;
		}

		Calendar cal = (Calendar) input.clone();
		cal.setTime(cal.getTime());

		cal.add(Calendar.DATE, 1);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, -1);

		return cal;
	}

	public static Date subtract1Millisecond(Date date) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.MILLISECOND, -1);

		return calendar.getTime();
	}

	/**
	 * @param o
	 *            Must be of type XMLGregorianCalendar, Calendar, or Date
	 */
	public static final String formatMessageDate(Object o) {
		Calendar c = null;
		if (o instanceof XMLGregorianCalendar) {
			c = ((XMLGregorianCalendar) o).toGregorianCalendar();
		} else if (o instanceof Date) {
			c = Calendar.getInstance();
			c.setTime((Date) o);
		} else {
			c = (Calendar) o;
		}

		Calendar today = Calendar.getInstance();
		if (DateUtils.isSameDay(c, today)) {
			return DateFormatUtils.format(c, "h:mm a").toLowerCase();
		} else if (DateUtils.isSameYear(c, today)) {
			return DateFormatUtils.format(c, "MMM d");
		}

		return DateFormatUtils.format(c, "MM/dd/yy");
	}

	public static String formatActivityTime(Date activityDate) {
		return format(activityDate, TIME_FORMAT_TL.get());
	}

	public static String formatActivityDateAndTime(Date activityDate) {
		return format(activityDate, OUTPUT_MONTH_DAY_TIME_FORMAT_TL.get());
	}

	public static String formatChartDate(Date date) {
		return CHART_DATE_FORMAT.format(date);
	}

	public static String formatChartDate(Calendar calendar) {
		return CHART_DATE_FORMAT.format(calendar);
	}

	private static String format(Date activityDate, DateFormat defaultFormat) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(activityDate);

			Calendar today = Calendar.getInstance();
			today.setTime(new Date());

			if (isToday(activityDate)) {
				long diff = today.getTimeInMillis()
						- calendar.getTimeInMillis();
				long minDiff = diff / (1000 * 60);
				if (minDiff < 60) {
					if (minDiff <= 1)
						return new StringBuilder().append("Moments Ago")
								.toString();
					else
						return new StringBuilder().append(minDiff)
								.append(" Minutes Ago").toString();
				} else {
					long hourDiff = minDiff / 60;
					if (hourDiff <= 1)
						return new StringBuilder().append("1 Hour Ago")
								.toString();
					else
						return new StringBuilder().append(hourDiff)
								.append(" Hours Ago").toString();
				}
			}

			return defaultFormat.format(activityDate);
		} catch (Throwable ex) {
			return "";
		}
	}

	public static String format(Date date, String pattern) {
		try {
			return new SimpleDateFormat(pattern).format(date);
		} catch (Throwable ex) {
			return "";
		}
	}

	public static int getDaysRemaining(Date date) {
		if (date == null) {
			return 0;
		}

		Calendar today = DateUtils.truncate(Calendar.getInstance(),
				Calendar.DATE);
		if (date.before(today.getTime())) {
			return 0;
		}

		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end = DateUtils.truncate(end, Calendar.DATE);

		// get positive value so that the difference is calculated as positive
		// number.
		return (int) (Math.abs(today.getTimeInMillis() - end.getTimeInMillis()) / MILLIS_PER_DAY);
	}

	public static int getDaysBeforeStart(Date date) {
		return getDaysBetween(new Date(), date);
	}

	public static int getDaysPassed(Date date) {
		return getDaysBetween(date, new Date());
	}

	public static int getDaysBetween(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}

		date1 = DateUtils.truncate(date1, Calendar.DATE);
		date2 = DateUtils.truncate(date2, Calendar.DATE);

		if (date1.after(date2)) {
			return 0;
		}

		// get positive value so that the difference is calculated as positive
		// number.
		return (int) (Math.abs(date2.getTime() - date1.getTime()) / MILLIS_PER_DAY);
	}

	public static Date randomDate(Date startDate, Date endDate) {

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		int year = randomBetween(start.get(Calendar.YEAR),
				end.get(Calendar.YEAR));
		int dayOfYear = randomBetween(start.get(Calendar.DAY_OF_YEAR),
				end.get(Calendar.DAY_OF_YEAR));
		System.out.println("Year:" + year);
		System.out.println("Day of Year:" + dayOfYear);

		Calendar random = Calendar.getInstance();
		random.set(Calendar.YEAR, year);
		random.set(Calendar.DAY_OF_YEAR, dayOfYear);

		return random.getTime();
	}

	protected static int randomBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public static double getTimeUnitCount(String timeUnit, int numberOfDays) {
		if (timeUnit.equals(WEEK))
			return getWeeksIn(numberOfDays);
		else if (timeUnit.equals(MONTH))
			return getMonthsIn(numberOfDays);
		if (timeUnit.equals(YEAR))
			return getYearsIn(numberOfDays);
		else
			return numberOfDays;
	}

	public static int getTimeUnitCountRounded(String timeUnit, int numberOfDays) {
		double units = getTimeUnitCount(timeUnit, numberOfDays);
		return new Double(units).intValue();
	}

	public static double getMonthsIn(int numberOfDays) {
		return (double) numberOfDays / 30D;
	}

	public static double getWeeksIn(int numberOfDays) {
		return (double) numberOfDays / 7D;
	}

	public static double getYearsIn(int numberOfDays) {
		return (double) numberOfDays / 365D;
	}

	public static int getAge(Date birthDate) {
		DateTime today = new DateTime(getToday()).dayOfYear().roundFloorCopy();
		DateTime dob = new DateTime(birthDate).dayOfYear().roundFloorCopy();

		return Years.yearsBetween(dob, today).getYears();
	}

	/**
	 * Day of Week starts at 1 for Monday and ends with 7 for Sunday.
	 */
	public int getDayOfWeek(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.getDayOfWeek();
	}

	public static int compare(Date lhs, Date rhs, boolean nullIsBOT) {
		if (lhs == rhs) { // both null
			return 0;
		}

		if (lhs == null) {
			return nullIsBOT ? -1 : 1;
		}

		if (rhs == null) {
			return nullIsBOT ? 1 : -1;
		}

		return lhs.compareTo(rhs);
	}

	public static Date parseDateOrNull(String str, String parsePattern) {
		return parseDateOrNull(str, new String[] { parsePattern });
	}

	public static Date parseDateOrNull(String str, String[] parsePatterns) {
		try {
			return parseDate(str, parsePatterns);
		} catch (Throwable e) {
			return null;
		}
	}

	public static String formatSSODate(Date date) {
		return format(date, FULL_DATE_TIME_FORMAT_SSO.get());
	}

	public static boolean isNotSameDay(Date date1, Date date2) {
		return !DateUtils.isSameDay(date1, date2);
	}

	public static boolean isNotSameDay(Calendar cal1, Calendar cal2) {
		return !DateUtils.isSameDay(cal1, cal2);
	}

	public static boolean isNotSameYear(Date date1, Date date2) {
		return !DateUtils.isNotSameYear(date1, date2);
	}

	public static boolean isNotSameYear(Calendar cal1, Calendar cal2) {
		return !DateUtils.isNotSameYear(cal1, cal2);
	}

	public static boolean isInBetween(Date date1, Date date2, Date input) {
		return DateUtils.isOnOrAfter(date1, input)
				&& DateUtils.isOnOrBefore(date2, input);
	}

	public static boolean isNotInBetween(Date date1, Date date2, Date input) {
		return !isInBetween(date1, date2, input);
	}

	public static boolean isOnOrBefore(Date startDate, Date endDate) {
		return isBefore(startDate, endDate) || isSameDay(startDate, endDate);
	}

	public static boolean isOnOrAfter(Date startDate, Date endDate) {
		return isAfter(startDate, endDate) || isSameDay(startDate, endDate);
	}

	public static Date parseDate(int month, int dayOfMonth) {
		try {
			Calendar cal = Calendar.getInstance();
			DateTime current = new DateTime();
			cal.set(current.getYear(), month, dayOfMonth);
			return cal.getTime();
		} catch (Throwable ex) {
			return null;
		}
	}

	public static Date nextDateThanToday(int month, int dayOfMonth) {
		try {
			Calendar cal = Calendar.getInstance();
			DateTime current = new DateTime();
			cal.set(current.getYear(), month, dayOfMonth);
			if (isBefore(cal.getTime(), current.toDate())) {
				return cal.getTime();
			} else {
				cal = Calendar.getInstance();
				cal.set(current.getYear() + 1, month, dayOfMonth);
				return cal.getTime();
			}

		} catch (Throwable ex) {
			return null;
		}
	}

	public static int getDaysInTimeUnit(String timeUnit) {
		if (timeUnit.equals(WEEK))
			return 7;
		else
			return 1;
	}

	public static boolean contains(Interval interval, Date date) {
		if (interval == null || date == null) {
			return false;
		}

		return interval.contains(date.getTime());
	}

	public static boolean contains(Interval interval, DateTime dateTime) {
		if (interval == null || dateTime == null) {
			return false;
		}

		return interval.contains(dateTime.getMillis());
	}

	/**
	 * Here the assumption is that the week starts from Monday to Sunday
	 * 
	 * @return
	 */
	public static Date getBeginningOfWeek() {
		Date date = getToday();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}

	public static Date getBeginningOfQuarter() {
		Date date = getToday();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - (month % 3));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getBeginningOfMonth() {
		Date date = getToday();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getBeginningOfYear() {
		Date date = getToday();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		return calendar.getTime();
	}

	public static Date getEndOfYear() {
		Date date = getEndOfDay(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		return calendar.getTime();
	}

	public static Date getEndOfMonth() {
		Date date = getEndOfDay(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		return calendar.getTime();
	}

	public static Date getEndOfQuarter() {
		Date date = getEndOfDay(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month + (2 - (month % 3)));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getEndOfWeek() {
		Date date = getEndOfDay(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_YEAR,
				calendar.get(Calendar.WEEK_OF_YEAR) + 1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return calendar.getTime();
	}

	public static int getDayofDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	public static int getMonthofDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static int getYearofDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static boolean isAfterOrEqualTo(Date date, Date dateToCompare) {

		if (date.after(dateToCompare) || date.equals(dateToCompare)) {
			return true;
		}

		return false;
	}

	public static boolean isBeforeOrEqualTo(Date date, Date dateToCompare) {

		if (date.before(dateToCompare) || date.equals(dateToCompare)) {
			return true;
		}

		return false;
	}

	public static int getYearIntervalBetweenDates(Date startDate, Date endDate) {
		int years = -1;
		if (startDate != null && endDate != null) {
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(startDate);

			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(endDate);

			years = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
		}
		return years;
	}

	public static int getYear(Date dateToUse) {
		int year = -1;
		if (dateToUse != null) {
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(dateToUse);
			year = calendar1.get(Calendar.YEAR);
		}
		return year;
	}

}
