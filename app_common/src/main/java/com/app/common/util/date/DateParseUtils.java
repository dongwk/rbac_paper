package com.app.common.util.date;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateParseUtils {

	/**
	 * 字符串 to 日期
	 * @param date 日期
	 * @param parttern 日期格式
	 * @return 日期字符串
	 */
	public static Date parse(String date, DateStyle dateStyle) {
		return parse(date, dateStyle.getValue());
	}

	/**
	 * 字符串 to 日期
	 * @param date 日期
	 * @param parttern 日期格式
	 * @return 日期字符串
	 */
	public static Date parse(String date, String parttern) {
		try {
			return DateUtils.parseDate(date, parttern);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * YYYY-MM-DD 字符串 to 日期
	 * @param date
	 * @return
	 */
	public static Date parseYYYYMMDD(String date){
		return parse(date, DateStyle.YYYY_MM_DD);
	}

	/**
	 * YYYY-MM-DD HH:mm:ss 字符串 to 日期
	 * @param date
	 * @return
	 */
	public static Date parseYYYYMMDDHHMMSS(String date){
		return parse(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
	}
}