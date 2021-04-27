package com.app.common.util.date;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils {
    
    /**
     * @return 当前时间
     */
	public static Date date() {
		return new Date();
	}

	/**
	 * @return 当前时间
	 */
	public static Date now() {
		return new Date();
	}


	/**
	 * @return 当前时间
	 */
	public static long timestamp() {
		return date().getTime();
	}

	/**
	 * @return 当前时间
	 */
	public static long timestampSec() {
		return date().getTime()/1000;
	}

	/**
	 * 获取日期中的某数值。如获取月份
	 * @param date 日期
	 * @param dateType 日期格式
	 * @return 数值
	 */
	private static int getInteger(Date date, int dateType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(dateType);
	}
	
	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期字符串
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期字符串
	 */
	private static String addInteger(String date, int dateType, int amount) {
		String dateString = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = toDate(date, dateStyle);
			myDate = addInteger(myDate, dateType, amount);
			dateString = toStr(myDate, dateStyle);
		}
		return dateString;
	}
	
	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	/**
	 * 获取精确的日期
	 * @param timestamps 时间long集合
	 * @return 日期
	 */
	private static Date getAccurateDate(List<Long> timestamps) {
		Date date = null;
		long timestamp = 0;
		Map<Long, long[]> map = new HashMap<Long, long[]>();
		List<Long> absoluteValues = new ArrayList<Long>();

		if (timestamps != null && timestamps.size() > 0) {
			if (timestamps.size() > 1) {
				for (int i = 0; i < timestamps.size(); i++) {
					for (int j = i + 1; j < timestamps.size(); j++) {
						long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
						absoluteValues.add(absoluteValue);
						long[] timestampTmp = { timestamps.get(i), timestamps.get(j) };
						map.put(absoluteValue, timestampTmp);
					}
				}

				// 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的
				long minAbsoluteValue = -1;
				if (!absoluteValues.isEmpty()) {
					// 如果timestamps的size为2，这是差值只有一个，因此要给默认值
					minAbsoluteValue = absoluteValues.get(0);
				}
				for (int i = 0; i < absoluteValues.size(); i++) {
					for (int j = i + 1; j < absoluteValues.size(); j++) {
						if (absoluteValues.get(i) > absoluteValues.get(j)) {
							minAbsoluteValue = absoluteValues.get(j);
						} else {
							minAbsoluteValue = absoluteValues.get(i);
						}
					}
				}

				if (minAbsoluteValue != -1) {
					long[] timestampsLastTmp = map.get(minAbsoluteValue);
					if (absoluteValues.size() > 1) {
						timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
					} else if (absoluteValues.size() == 1) {
						// 当timestamps的size为2，需要与当前时间作为参照
						long dateOne = timestampsLastTmp[0];
						long dateTwo = timestampsLastTmp[1];
						if ((Math.abs(dateOne - dateTwo)) < 100000000000L) {
							timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
						} else {
							long now = new Date().getTime();
							if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {
								timestamp = dateOne;
							} else {
								timestamp = dateTwo;
							}
						}
					}
				}
			} else {
				timestamp = timestamps.get(0);
			}
		}

		if (timestamp != 0) {
			date = new Date(timestamp);
		}
		return date;
	}

	/**
	 * 获取日期字符串的日期风格。失敗返回null。
	 * @param date 日期字符串
	 * @return 日期风格
	 */
	public static DateStyle getDateStyle(String date) {
		DateStyle dateStyle = null;
		Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
		List<Long> timestamps = new ArrayList<Long>();
		for (DateStyle style : DateStyle.values()) {
			Date dateTmp = toDate(date, style.getValue());
			if (dateTmp != null) {
				timestamps.add(dateTmp.getTime());
				map.put(dateTmp.getTime(), style);
			}
		}
		dateStyle = map.get(getAccurateDate(timestamps).getTime());
		return dateStyle;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static Date toDate(String date) {
		DateStyle dateStyle = null;
		return toDate(date, dateStyle);
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param parttern 日期格式
	 * @return 日期
	 */
	public static Date toDate(String date, String parttern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = org.apache.commons.lang3.time.DateUtils.parseDate(date, parttern);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		return myDate;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param dateStyle 日期风格
	 * @return 日期
	 */
	public static Date toDate(String date, DateStyle dateStyle) {
		Date myDate = null;
		if (dateStyle == null) {
			List<Long> timestamps = new ArrayList<Long>();
			for (DateStyle style : DateStyle.values()) {
				Date dateTmp = toDate(date, style.getValue());
				if (dateTmp != null) {
					timestamps.add(dateTmp.getTime());
				}
			}
			myDate = getAccurateDate(timestamps);
		} else {
			myDate = toDate(date, dateStyle.getValue());
		}
		return myDate;
	}

	/**
	 * 将日期转化为Calendar。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static Calendar toCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static Calendar toCalendar(String date) {
		DateStyle dateStyle = null;
		Date date2 = toDate(date, dateStyle);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		return calendar;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param parttern 日期格式
	 * @return 日期
	 */
	public static Calendar toCalendar(String date, String parttern) {
		if (date != null) {
			Date myDate = toDate(date, parttern);
			toDate(date, parttern);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(myDate);
			return calendar;
		}
		return null;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param dateStyle 日期风格
	 * @return 日期
	 */
	public static Calendar toCalendar(String date, DateStyle dateStyle) {
		Date myDate = null;
		if (dateStyle == null) {
			List<Long> timestamps = new ArrayList<Long>();
			for (DateStyle style : DateStyle.values()) {
				Date dateTmp = toDate(date, style.getValue());
				if (dateTmp != null) {
					timestamps.add(dateTmp.getTime());
				}
			}
			myDate = getAccurateDate(timestamps);
		} else {
			myDate = toDate(date, dateStyle.getValue());
		}
		if (myDate != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(myDate);
			return calendar;
		}
		return null;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param parttern 日期格式
	 * @return 日期字符串
	 */
	public static String toStr(Date date, String parttern) {
		String dateString = null;
		if (date != null) {
			dateString = DateFormatUtils.format(date, parttern);
		}
		return dateString;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param dateStyle 日期风格
	 * @return 日期字符串
	 */
	public static String toStr(Date date, DateStyle dateStyle) {
		String dateString = null;
		if (dateStyle != null) {
			dateString = toStr(date, dateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param parttern 新日期格式
	 * @return 新日期字符串
	 */
	public static String toStr(String date, String parttern) {
		return toStr(date, null, parttern);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param dateStyle 新日期风格
	 * @return 新日期字符串
	 */
	public static String toStr(String date, DateStyle dateStyle) {
		return toStr(date, null, dateStyle);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddParttern 旧日期格式
	 * @param newParttern 新日期格式
	 * @return 新日期字符串
	 */
	public static String toStr(String date, String olddParttern, String newParttern) {
		String dateString = null;
		if (olddParttern == null) {
			DateStyle style = getDateStyle(date);
			if (style != null) {
				Date myDate = toDate(date, style.getValue());
				dateString = toStr(myDate, newParttern);
			}
		} else {
			Date myDate = toDate(date, olddParttern);
			dateString = toStr(myDate, newParttern);
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddDteStyle 旧日期风格
	 * @param newDateStyle 新日期风格
	 * @return 新日期字符串
	 */
	public static String toStr(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
		String dateString = null;
		if (olddDteStyle == null) {
			DateStyle style = getDateStyle(date);
			dateString = toStr(date, style.getValue(), newDateStyle.getValue());
		} else {
			dateString = toStr(date, olddDteStyle.getValue(), newDateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期字符串
	 */
	public static String addYear(String date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}
	
	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期
	 */
	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}
	
	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加月份后的日期字符串
	 */
	public static String addMonth(String date, int yearAmount) {
		return addInteger(date, Calendar.MONTH, yearAmount);
	}
	
	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加月份后的日期
	 */
	public static Date addMonth(Date date, int yearAmount) {
		return addInteger(date, Calendar.MONTH, yearAmount);
	}
	
	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期字符串
	 */
	public static String addDay(String date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期
	 */
	public static Date addDay(Date date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}
	
	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期字符串
	 * @param hourAmount 增加数量。可为负数
	 * @return 增加小时后的日期字符串
	 */
	public static String addHour(String date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期
	 * @param hourAmount 增加数量。可为负数
	 * @return 增加小时后的日期
	 */
	public static Date addHour(Date date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}
	
	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期字符串
	 * @param minuteAmount 增加数量。可为负数
	 * @return 增加分钟后的日期字符串
	 */
	public static String addMinute(String date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}

	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期
	 * @param minuteAmount 增加数量。可为负数
	 * @return 增加分钟后的日期
	 */
	public static Date addMinute(Date date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}
	
	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期字符串
	 * @param secondAmount 增加数量。可为负数
	 * @return 增加秒钟后的日期字符串
	 */
	public static String addSecond(String date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期
	 * @param secondAmount 增加数量。可为负数
	 * @return 增加秒钟后的日期
	 */
	public static Date addSecond(Date date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期字符串
	 * @return 年份
	 */
	public static int getYear(String date) {
		return getYear(toDate(date));
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		return getInteger(date, Calendar.YEAR);
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期字符串
	 * @return 月份
	 */
	public static int getMonth(String date) {
		return getMonth(toDate(date));
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		return getInteger(date, Calendar.MONTH);
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期字符串
	 * @return 天
	 */
	public static int getDay(String date) {
		return getDay(toDate(date));
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		return getInteger(date, Calendar.DATE);
	}
	
	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期字符串
	 * @return 小时
	 */
	public static int getHour(String date) {
		return getHour(toDate(date));
	}

	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		return getInteger(date, Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期字符串
	 * @return 分钟
	 */
	public static int getMinute(String date) {
		return getMinute(toDate(date));
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期
	 * @return 分钟
	 */
	public static int getMinute(Date date) {
		return getInteger(date, Calendar.MINUTE);
	}
	
	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期字符串
	 * @return 秒钟
	 */
	public static int getSecond(String date) {
		return getSecond(toDate(date));
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期
	 * @return 秒钟
	 */
	public static int getSecond(Date date) {
		return getInteger(date, Calendar.SECOND);
	}

	/**
	 * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static String getDate(String date) {
		return toStr(date, DateStyle.YYYY_MM_DD);
	}

	/**
	 * 获取日期。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期
	 * @return 日期
	 */
	public static String getDate(Date date) {
		return toStr(date, DateStyle.YYYY_MM_DD);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期字符串
	 * @return 时间
	 */
	public static String getTime(String date) {
		return toStr(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期
	 * @return 时间
	 */
	public static String getTime(Date date) {
		return toStr(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期字符串
	 * @return 星期
	 */
	public static Week getWeek(String date) {
		Week week = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = toDate(date, dateStyle);
			week = getWeek(myDate);
		}
		return week;
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期
	 * @return 星期
	 */
	public static Week getWeek(Date date) {
		Week week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (weekNumber) {
		case 0:
			week = Week.SUNDAY;
			break;
		case 1:
			week = Week.MONDAY;
			break;
		case 2:
			week = Week.TUESDAY;
			break;
		case 3:
			week = Week.WEDNESDAY;
			break;
		case 4:
			week = Week.THURSDAY;
			break;
		case 5:
			week = Week.FRIDAY;
			break;
		case 6:
			week = Week.SATURDAY;
			break;
		}
		return week;
	}
	
	/**
	 * 获取两个日期相差的天数
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差天数
	 */
	public static int getIntervalDays(String date, String otherDate) {
		return (int)(getInterval(toDate(date), toDate(otherDate))/(24 * 60 * 60 * 1000));
	}
	
	/**
	 * 获取两个日期相差的小时数
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差小时数
	 */
	public static int getIntervalHours(String date, String otherDate) {
		return (int)(getInterval(toDate(date), toDate(otherDate))/(60 * 60 * 1000));
	}
	
	/**
	 * 获取两个日期相差的分钟数
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差分钟数
	 */
	public static int getIntervalMinutes(String date, String otherDate) {
		return (int)(getInterval(toDate(date), toDate(otherDate))/(60 * 1000));
	}
	
	/**
	 * 获取两个日期相差的秒数
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差秒数
	 */
	public static int getIntervalSeconds(String date, String otherDate) {
		return (int)(getInterval(toDate(date), toDate(otherDate))/(1000));
	}
	
	/**
	 * @param date 日期
	 * @param otherDate 另一个日期
	 * @return 相差毫秒数
	 */
	private static long getInterval(Date date, Date otherDate) {
		date = DateUtils.toDate(DateUtils.getDate(date));
		long time = Math.abs(date.getTime() - otherDate.getTime());
		return time;
	}
	
	/**
	 * 获取月开始日期
	 * @param date 日期
	 * @return 开始日期
	 */
	public static Date getBeginOfMonth(Date date) {
        YearMonth yearMonth = YearMonth.of(getYear(date), 1);  
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atTime(0, 0, 0, 0);
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());  
	}
	
	/**
	 * 获取月结束日期
	 * @param date 日期
	 * @return 结束日期
	 */
	public static Date getEndOfMonth(Date date) {
        YearMonth yearMonth = YearMonth.of(getYear(date), 1);  
        LocalDate localDate = yearMonth.atEndOfMonth();
        LocalDateTime startOfDay = localDate.atTime(23, 59, 59, 999);  
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());  
	}
	
	/**
	 * 获取月开始日期
	 * @param date 日期
	 * @return 开始日期
	 */
	public static Date getBeginOfWeek(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取月结束日期
	 * @param date 日期
	 * @return 结束日期
	 */
	public static Date getEndOfWeek(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); 
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	/**
	 * 字符串转换时间
	 * @param date
	 * @param parttern
	 * @return
	 */
	public static Date parseDate(String date, String parttern){
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(date, parttern);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}