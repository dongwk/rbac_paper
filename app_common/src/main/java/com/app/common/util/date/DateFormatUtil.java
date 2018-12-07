package com.app.common.util.date;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间转换
 * @author Administrator
 *
 */
public class DateFormatUtil {

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param parttern 日期格式
	 * @return 日期字符串
	 */
	public static String format(Date date, DateStyle dateStyle) {
		String dateString = null;
		if (date != null) {
			dateString = format(date, dateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param parttern 日期格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String parttern) {
		String dateString = null;
		if (date != null) {
			dateString = DateFormatUtils.format(date, parttern);
		}
		return dateString;
	}
	
	/**
	 * 日期转换为字符串 YYYY-MM-DD
	 * @param date
	 * @return
	 */
	public static String formatYYYYMMDD(Date date){
		return format(date, DateStyle.YYYY_MM_DD);
	}

	
	/**
	 * 日期转换为字符串 YYYY-MM-DD HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatYYYYMMDDHHMMSS(Date date){
		return format(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
	}
}