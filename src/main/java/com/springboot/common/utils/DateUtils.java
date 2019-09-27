package com.springboot.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;

/**
 * 
 * @ClassName: DateUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author link
 * @date 2018年11月27日 下午2:10:20
 *
 */
public class DateUtils {

	/**
	 * 
	 * 当前日期，格式 yyyyMMdd
	 * 
	 * @param @return 设定文件
	 * @return String 返回类型
	 *
	 */
	public static String getDateNoBar() {
		return format(new Date(), "yyyyMMdd");
	}

	/**
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前时间的标准形式字符串
	 */
	public static String getDateTime() {
		return formatDateTime(new Date());
	}

	/**
	 * 当前日期，格式 yyyy-MM-dd
	 * 
	 * @return 当前日期的标准形式字符串
	 */
	public static String getDate() {
		return formatDate(new Date());
	}

	/**
	 * 
	 * Long类型时间转为Date
	 * 
	 * @param @param date
	 * @param @return 设定文件
	 * @return Date 返回类型
	 * @throws
	 */
	public static Date date(long date) {
		return new Date(date);
	}

	/**
	 * {@link Calendar}类型时间转为{@link Date}
	 * 
	 * @param calendar
	 *            {@link Calendar}
	 * @return 时间对象
	 */
	public static Date date(Calendar calendar) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		return date;
	}

	/**
	 * 转换为Calendar对象
	 * 
	 * @param date
	 *            日期对象
	 * @return Calendar对象
	 */
	public static Calendar calendar(Date date) {
		return calendar(date.getTime());
	}

	/**
	 * 转换为Calendar对象
	 * 
	 * @param millis
	 *            时间戳
	 * @return Calendar对象
	 */
	public static Calendar calendar(long millis) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal;
	}

	/**
	 * 当前时间的时间戳
	 * 
	 * @param isNano
	 *            是否为高精度时间
	 * @return 时间
	 */
	public static long current(boolean isNano) {
		return isNano ? System.nanoTime() : System.currentTimeMillis();
	}

	/**
	 * 当前时间的时间戳（秒）
	 * 
	 * @return 当前时间秒数
	 * @since 4.0.0
	 */
	public static long currentSeconds() {
		return System.currentTimeMillis() / 1000;
	}

	// Date end

	/**
	 * 获得指定日期年份和季节<br>
	 * 格式：[20131]表示2013年第一季度
	 * 
	 * @param date
	 *            日期
	 * @return Quarter ，类似于 20132
	 */
	public static String yearAndQuarter(Date date) {
		return yearAndQuarter(calendar(date));
	}

	/**
	 * 获得指定日期区间内的年份和季节<br>
	 * 
	 * @param startDate
	 *            起始日期（包含）
	 * @param endDate
	 *            结束日期（包含）
	 * @return 季度列表 ，元素类似于 20132
	 */
	public static LinkedHashSet<String> yearAndQuarter(Date startDate,
			Date endDate) {
		if (startDate == null || endDate == null) {
			return new LinkedHashSet<String>(0);
		}
		return yearAndQuarter(startDate.getTime(), endDate.getTime());
	}

	/**
	 * 获得指定日期区间内的年份和季节<br>
	 * 
	 * @param startDate
	 *            起始日期（包含）
	 * @param endDate
	 *            结束日期（包含）
	 * @return 季度列表 ，元素类似于 20132
	 * @since 4.1.15
	 */
	public static LinkedHashSet<String> yearAndQuarter(long startDate,
			long endDate) {
		LinkedHashSet<String> quarters = new LinkedHashSet<>();
		final Calendar cal = calendar(startDate);
		while (startDate <= endDate) {
			// 如果开始时间超出结束时间，让结束时间为开始时间，处理完后结束循环
			quarters.add(yearAndQuarter(cal));

			cal.add(Calendar.MONTH, 3);
			startDate = cal.getTimeInMillis();
		}

		return quarters;
	}

	// ------------------------------------ Format start--------------
	/**
	 * 
	 * 根据特定格式格式化日期
	 * 
	 * @param @param pattern 日期格式 {@link DatePattern}
	 * @param @param date 被格式化的日期
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 根据特定格式格式化日期
	 * 
	 * @param date
	 *            被格式化的日期
	 * @param format
	 *            日期格式，常用格式见： {@link DatePattern}
	 * @return 格式化后的字符串
	 */
	public static Date format(String date, String pattern) {
		if (null == date || StringUtils.isBlank(pattern)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 根据特定格式格式化日期
	 * 
	 * @param date
	 *            被格式化的日期
	 * @param format
	 *            {@link SimpleDateFormat}
	 * @return 格式化后的字符串
	 */
	public static String format(Date date, DateFormat format) {
		if (null == format || null == date) {
			return null;
		}
		return format.format(date);
	}

	/**
	 * 格式化日期时间<br>
	 * 格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            被格式化的日期
	 * @return 格式化后的日期
	 */
	public static String formatDateTime(Date date) {
		if (null == date) {
			return null;
		}
		return format(date, DatePattern.NORM_DATETIME_PATTERN);
	}

	/**
	 * 格式化日期部分（不包括时间）<br>
	 * 格式 yyyy-MM-dd
	 * 
	 * @param date
	 *            被格式化的日期
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date) {
		if (null == date) {
			return null;
		}
		return format(date, DatePattern.NORM_DATE_PATTERN);
	}

	/**
	 * 格式化时间<br>
	 * 格式 HH:mm:ss
	 * 
	 * @param date
	 *            被格式化的日期
	 * @return 格式化后的时间
	 */
	public static String formatTime(Date date) {
		if (null == date) {
			return null;
		}
		return format(date, DatePattern.NORM_TIME_PATTERN);
	}

	/**
	 * 格式化日期<br>
	 * 格式 yyyy-MM-dd HH:mm:ss
	 *
	 * @param @param date 被格式化的日期
	 * @return Date 格式化后的日期
	 */
	public static Date formatDateTime(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return format(date, DatePattern.NORM_DATETIME_PATTERN);
	}

	/**
	 * 格式化日期部分（不包括时间）<br>
	 * 格式 yyyy-MM-dd
	 * 
	 * @param date
	 *            被格式化的日期
	 * @return 格式化后日期
	 */
	public static Date formatDate(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return format(date, DatePattern.NORM_DATE_PATTERN);
	}

	/**
	 * 格式化为中文日期格式，如果isUppercase为false，则返回类似：2018年10月24日，否则返回二〇一八年十月二十四日
	 * 
	 * @param date
	 *            被格式化的日期
	 * @param isUppercase
	 *            是否采用大写形式
	 * @return 中文日期字符串
	 * @since 4.1.19
	 */
	public static String formatChineseDate(Date date, boolean isUppercase) {
		if (null == date) {
			return null;
		}

		return null;
	}

	// ------------------------------------ Format end
	// ----------------------------------------------

	// -------------------------------pase start----------------

	/**
	 * 将特定格式的日期转换为Date对象
	 * 
	 * @param dateStr
	 *            特定格式的日期
	 * @param format
	 *            格式，例如yyyy-MM-dd
	 * @return 日期对象
	 */
	public static Date parse(String dateStr, String format) {
		return format(dateStr, format);
	}

	/**
	 * 将日期字符串转换为 Date对象，格式：<br>
	 * <ol>
	 * <li>yyyy-MM-dd HH:mm:ss</li>
	 * <li>yyyy/MM/dd HH:mm:ss</li>
	 * <li>yyyy.MM.dd HH:mm:ss</li>
	 * <li>yyyy年MM月dd日 HH时mm分ss秒</li>
	 * <li>yyyy-MM-dd</li>
	 * <li>yyyy/MM/dd</li>
	 * <li>yyyy.MM.dd</li>
	 * <li>HH:mm:ss</li>
	 * <li>HH时mm分ss秒</li>
	 * <li>yyyy-MM-dd HH:mm</li>
	 * <li>yyyy-MM-dd HH:mm:ss.SSS</li>
	 * <li>yyyyMMddHHmmss</li>
	 * <li>yyyyMMddHHmmssSSS</li>
	 * <li>yyyyMMdd</li>
	 * <li>EEE, dd MMM yyyy HH:mm:ss z</li>
	 * <li>EEE MMM dd HH:mm:ss zzz yyyy</li>
	 * </ol>
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 日期
	 */
	public static Date parse(String dateStr) {
		if (null == dateStr) {
			return null;
		}
		// 去掉两边空格并去掉中文日期中的“日”，以规范长度
		dateStr = dateStr.trim().replace("日", "");
		int length = dateStr.length();
		if (StringUtils.isNumeric(dateStr)) {
			// 纯数字形式
			if (length == DatePattern.PURE_DATETIME_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATETIME_PATTERN);
			} else if (length == DatePattern.PURE_DATETIME_MS_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATETIME_MS_PATTERN);
			} else if (length == DatePattern.PURE_DATE_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_DATE_PATTERN);
			} else if (length == DatePattern.PURE_TIME_PATTERN.length()) {
				return parse(dateStr, DatePattern.PURE_TIME_PATTERN);
			}
		}

		if (length == DatePattern.NORM_DATETIME_PATTERN.length()
				|| length == DatePattern.NORM_DATETIME_PATTERN.length() + 1) {
			if (dateStr.contains("T")) {
				// UTC时间格式：类似2018-09-13T05:34:31
				return parse(dateStr, DatePattern.UTC_PATTERN);
			}
			return parse(dateStr, DatePattern.NORM_DATETIME_PATTERN);
		} else if (length == DatePattern.NORM_DATE_PATTERN.length()) {
			return parse(dateStr, DatePattern.NORM_DATE_PATTERN);
		} else if (length == DatePattern.NORM_TIME_PATTERN.length()
				|| length == DatePattern.NORM_TIME_PATTERN.length() + 1) {
			return parse(dateStr, DatePattern.NORM_TIME_PATTERN);
		} else if (length == DatePattern.NORM_DATETIME_MINUTE_PATTERN.length()
				|| length == DatePattern.NORM_DATETIME_MINUTE_PATTERN.length() + 1) {
			return parse(dateStr, DatePattern.NORM_DATETIME_MINUTE_PATTERN);
		} else if (length >= DatePattern.NORM_DATETIME_MS_PATTERN.length() - 2) {
			return parse(dateStr, DatePattern.NORM_DATETIME_MS_PATTERN);
		}
		return null;
	}

	// -------------------------------pase end----------------
	/**
	 * 是否为相同时间
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 是否为相同时间
	 * @since 4.1.13
	 */
	public static boolean isSameTime(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 比较两个日期是否为同一天
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 是否为同一天
	 * @since 4.1.13
	 */
	public static boolean isSameDay(final Date date1, final Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return isSameDay(calendar(date1), calendar(date2));
	}

	/**
	 * 比较两个日期是否为同一天
	 * 
	 * @param cal1
	 *            日期1
	 * @param cal2
	 *            日期2
	 * @return 是否为同一天
	 * @since 4.1.13
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
				&& //
				cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && //
				cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA);
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return 是否闰年
	 */
	public static boolean isLeapYear(int year) {
		return new GregorianCalendar().isLeapYear(year);
	}

	/**
	 * 秒数转为时间格式(HH:mm:ss)<br>
	 * 参考：https://github.com/iceroot
	 * 
	 * @param seconds
	 *            需要转换的秒数
	 * @return 转换后的字符串
	 * @since 3.1.2
	 */
	public static String secondToTime(int seconds) {
		if (seconds < 0) {
			throw new IllegalArgumentException(
					"Seconds must be a positive number!");
		}

		int hour = seconds / 3600;
		int other = seconds % 3600;
		int minute = other / 60;
		int second = other % 60;
		final StringBuilder sb = new StringBuilder();
		if (hour < 10) {
			sb.append("0");
		}
		sb.append(hour);
		sb.append(":");
		if (minute < 10) {
			sb.append("0");
		}
		sb.append(minute);
		sb.append(":");
		if (second < 10) {
			sb.append("0");
		}
		sb.append(second);
		return sb.toString();
	}

	/**
	 * 月初
	 *
	 * @param @return 月初
	 * @return String 返回类型
	 *
	 */
	public static String startMonth() {
		String dateString = "";
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			dateString = formatDate(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;
	}

	// ------------------------------------------------------------------------
	// Private method start

	/**
	 * 获得指定日期年份和季节<br>
	 * 格式：[20131]表示2013年第一季度
	 * 
	 * @param cal
	 *            日期
	 */
	private static String yearAndQuarter(Calendar cal) {
		return new StringBuilder().append(cal.get(Calendar.YEAR))
				.append(cal.get(Calendar.MONTH) / 3 + 1).toString();
	}

	// ------------------------------------------------------------------------
	// Private method end

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}
}
