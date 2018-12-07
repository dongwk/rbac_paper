package com.app.common.util.cn;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.app.common.util.date.DateParseUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

public class Idcard {
	public static final int IDENTITYCODE_OLD = 15; // 老身份证15位
	public static final int IDENTITYCODE_NEW = 18; // 新身份证18位
	public static Map<String, String> cityCodes = new HashMap<String, String>();
	/** 台湾身份首字母对应数字 */
	public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
	/** 香港身份首字母对应数字 */
	public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();
	/** 省、直辖市代码表 */
	public static final String cityCode[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34",
			"35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64",
			"65", "71", "81", "82", "91" };

	static {
		cityCodes.put("11", "北京");
		cityCodes.put("12", "天津");
		cityCodes.put("13", "河北");
		cityCodes.put("14", "山西");
		cityCodes.put("15", "内蒙古");
		cityCodes.put("21", "辽宁");
		cityCodes.put("22", "吉林");
		cityCodes.put("23", "黑龙江");
		cityCodes.put("31", "上海");
		cityCodes.put("32", "江苏");
		cityCodes.put("33", "浙江");
		cityCodes.put("34", "安徽");
		cityCodes.put("35", "福建");
		cityCodes.put("36", "江西");
		cityCodes.put("37", "山东");
		cityCodes.put("41", "河南");
		cityCodes.put("42", "湖北");
		cityCodes.put("43", "湖南");
		cityCodes.put("44", "广东");
		cityCodes.put("45", "广西");
		cityCodes.put("46", "海南");
		cityCodes.put("50", "重庆");
		cityCodes.put("51", "四川");
		cityCodes.put("52", "贵州");
		cityCodes.put("53", "云南");
		cityCodes.put("54", "西藏");
		cityCodes.put("61", "陕西");
		cityCodes.put("62", "甘肃");
		cityCodes.put("63", "青海");
		cityCodes.put("64", "宁夏");
		cityCodes.put("65", "新疆");
		cityCodes.put("71", "台湾");
		cityCodes.put("81", "香港");
		cityCodes.put("82", "澳门");
		cityCodes.put("91", "国外");
		twFirstCode.put("A", 10);
		twFirstCode.put("B", 11);
		twFirstCode.put("C", 12);
		twFirstCode.put("D", 13);
		twFirstCode.put("E", 14);
		twFirstCode.put("F", 15);
		twFirstCode.put("G", 16);
		twFirstCode.put("H", 17);
		twFirstCode.put("J", 18);
		twFirstCode.put("K", 19);
		twFirstCode.put("L", 20);
		twFirstCode.put("M", 21);
		twFirstCode.put("N", 22);
		twFirstCode.put("P", 23);
		twFirstCode.put("Q", 24);
		twFirstCode.put("R", 25);
		twFirstCode.put("S", 26);
		twFirstCode.put("T", 27);
		twFirstCode.put("U", 28);
		twFirstCode.put("V", 29);
		twFirstCode.put("X", 30);
		twFirstCode.put("Y", 31);
		twFirstCode.put("W", 32);
		twFirstCode.put("Z", 33);
		twFirstCode.put("I", 34);
		twFirstCode.put("O", 35);
		hkFirstCode.put("A", 1);
		hkFirstCode.put("B", 2);
		hkFirstCode.put("C", 3);
		hkFirstCode.put("R", 18);
		hkFirstCode.put("U", 21);
		hkFirstCode.put("Z", 26);
		hkFirstCode.put("X", 24);
		hkFirstCode.put("W", 23);
		hkFirstCode.put("O", 15);
		hkFirstCode.put("N", 14);
	}

	public static int[] Wi = new int[17];

	public static Integer scale = 2;// 保留小数位后两位

	/**
	 * 判断身份证号码是否正确。
	 * 
	 * @param code
	 *            身份证号码。
	 * @return 如果身份证号码正确，则返回true，否则返回false。
	 */
	public static boolean isIdentityCode(String code) {

		if (StringUtils.isBlank(code)) {
			return false;
		}

		code = code.trim();

		// 长度只有15和18两种情况
		if ((code.length() != IDENTITYCODE_OLD) && (code.length() != IDENTITYCODE_NEW)) {
			return false;
		}

		// 身份证号码必须为数字(18位的新身份证最后一位可以是x)
		Pattern pt = Pattern.compile("\\d{15,17}([\\dxX]{1})?");
		Matcher mt = pt.matcher(code);
		if (!mt.find()) {
			return false;
		}

		// 最后一位校验码验证
		if (code.length() == IDENTITYCODE_NEW) {
			String lastNum = getCheckFlag(code.substring(0, IDENTITYCODE_NEW - 1));
			// check last digit
			if (!("" + code.charAt(IDENTITYCODE_NEW - 1)).toUpperCase().equals(lastNum)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取新身份证的最后一位:检验位
	 * 
	 * @param code
	 *            18位身份证的前17位
	 * @return 新身份证的最后一位
	 */
	private static String getCheckFlag(String code) {

		int[] varArray = new int[code.length()];
		String lastNum = "";
		int numSum = 0;
		// 初始化位权值
		setWiBuffer();
		for (int i = 0; i < code.length(); i++) {
			varArray[i] = NumberUtils.toInt("" + code.charAt(i));
			varArray[i] = varArray[i] * Wi[i];
			numSum = numSum + varArray[i];
		}
		int checkDigit = 12 - numSum % 11;
		switch (checkDigit) {
		case 10:
			lastNum = "X";
			break;
		case 11:
			lastNum = "0";
			break;
		case 12:
			lastNum = "1";
			break;
		default:
			lastNum = String.valueOf(checkDigit);
		}
		return lastNum;
	}

	/**
	 * 初始化位权值
	 */
	private static void setWiBuffer() {
		for (int i = 0; i < Wi.length; i++) {
			int k = (int) Math.pow(2, (Wi.length - i));
			Wi[i] = k % 11;
		}
	}

	/**
	 * 将15位身份证号码升级为18位身份证号码
	 * 
	 * @param code
	 *            15位身份证号码
	 * @return 18位身份证号码
	 */
	public static String updateEighteen(String code) {

		if (StringUtils.isEmpty(code)) {
			return "";
		}

		code = code.trim();

		if (code.length() != IDENTITYCODE_OLD || !isIdentityCode(code)) {
			return "";
		}

		code = code.substring(0, 6) + "19" + code.substring(6);
		//
		code = code + getCheckFlag(code);

		return code;
	}

	public static int getDaysByIdCard(String idCard, String type) {
		if (StringUtils.isBlank(idCard)) {
			return 0;
		}
		String birthday = getBirthday(idCard);
		if ("month".equals(type)) {
			return getMonth(birthday);
		}
		if ("day".equals(type)) {
			return getday(birthday);
		}
		if ("year".equals(type)) {
			return getyear(birthday);
		}
		return 0;
	}

	public static int getMonth(String birthday) {
		Calendar idCal = Calendar.getInstance();
		try {
			idCal.setTime(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return idCal.get(Calendar.MONTH) + 1;
	}

	public static int getday(String birthday) {
		Calendar idCal = Calendar.getInstance();
		try {
			idCal.setTime(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return idCal.get(Calendar.DATE);
	}

	public static int getyear(String birthday) {
		Calendar idCal = Calendar.getInstance();
		try {
			idCal.setTime(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return idCal.get(Calendar.YEAR);
	}

	/**
	 * 根据生日获取年龄
	 * 
	 * @param birthday
	 * @return
	 */
	private static String getAgeByBirthday(String birthday) {
		if (StringUtils.isBlank(birthday)) {
			return "未知";
		}
		int age = 0;
		Calendar idCal = Calendar.getInstance();
		try {
			idCal.setTime(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer idMonth = idCal.get(Calendar.MONTH);
		Integer idDay = idCal.get(Calendar.DATE);
		Calendar nowCal = Calendar.getInstance();

		age = nowCal.get(Calendar.YEAR) - idCal.get(Calendar.YEAR) - 1;
		Integer nowMonth = nowCal.get(Calendar.MONTH);
		Integer nowDay = nowCal.get(Calendar.DATE);
		if (nowMonth > idMonth || (idMonth == nowMonth && nowDay >= idDay)) {
			age++;
		}
		return String.valueOf(age);
	}

	/**
	 * 根据生日获取年龄
	 * 
	 * @param birthday
	 * @return
	 */
	private static String getAgeByBirthday(String birthday, Date date) {
		if (StringUtils.isBlank(birthday)) {
			return "未知";
		}
		int age = 0;
		Calendar idCal = Calendar.getInstance();
		try {
			idCal.setTime(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer idMonth = idCal.get(Calendar.MONTH);
		Integer idDay = idCal.get(Calendar.DATE);
		Calendar nowCal = Calendar.getInstance();
		if (date != null)
			nowCal.setTime(date);

		age = nowCal.get(Calendar.YEAR) - idCal.get(Calendar.YEAR) - 1;
		Integer nowMonth = nowCal.get(Calendar.MONTH);
		Integer nowDay = nowCal.get(Calendar.DATE);
		if (nowMonth > idMonth || (idMonth == nowMonth && nowDay >= idDay)) {
			age++;
		}
		return String.valueOf(age);
	}

	/**
	 * 根据身份证号码获取年龄
	 * 
	 * @param idCard
	 * @return
	 */
	public static String getAge(String idCard) {
		if (StringUtils.isBlank(idCard)) {
			return "未知";
		}
		String birthday = getBirthday(idCard);
		return getAgeByBirthday(birthday);
	}

	/**
	 * 根据身份证号码获取年龄
	 * 
	 * @param idCard
	 * @return
	 */
	public static String getAge(String idCard, Date date) {
		if (StringUtils.isBlank(idCard)) {
			return "未知";
		}
		String birthday = getBirthday(idCard);
		return getAgeByBirthday(birthday, date);
	}

	/**
	 * 通过身份证号码获取生日
	 * 
	 * @param idCard
	 * @return
	 */
	public static String getBirthday(String idCard) {
		String birthday = "";
		if (!isIdentityCode(idCard)) {
			return birthday;
		}
		if (StringUtils.isBlank(idCard)) {
			return birthday;
		}
		idCard = idCard.trim();
		if (idCard.length() == 15) {
			idCard = updateEighteen(idCard);
		}
		String byear = idCard.substring(6, 10); // 取年份
		String bmonth = idCard.substring(10, 12); // 取月份
		String bday = idCard.substring(12, 14); // 取日期
		birthday = byear + "-" + bmonth + "-" + bday;
		return birthday;
	}

	/**
	 * 通过身份证号码获取性别
	 * 
	 * @param idCard
	 * @return
	 */
	public static int getGender(String idCard) {
		if (!isIdentityCode(idCard)) {
			return 0;
		}
		idCard = idCard.trim();
		if (idCard.length() == 15) {
			idCard = updateEighteen(idCard);
		}
		int genderI = idCard.charAt(idCard.length() - 2); // 取性别标志码
		if (genderI % 2 == 0) {
			return 1; //女
		} else {
			return 0; //男
		}
	}

	/**
	 * 身份证号码隐私隐藏
	 * 
	 * @param str
	 * @return
	 */
	public static String privacy(String str) {
		if (!isIdentityCode(str)) {
			return str;
		}
		int lastLenth = str.length() - 4;
		String last = str.substring(lastLenth);
		String head = str.substring(0, 3);
		return head + "*****" + last;

	}

	/**
	 * 根据身份证号，自动获取对应的星座
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 星座
	 */
	public static String getConstellationById(String idCard) {
		if (!isIdentityCode(idCard)) {
			return "身份证号码不正确";
		}
		int month = getDaysByIdCard(idCard, "month");
		int day = getDaysByIdCard(idCard, "day");
		String strValue = "";

		if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
			strValue = "水瓶座";
		} else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
			strValue = "双鱼座";
		} else if ((month == 3 && day > 20) || (month == 4 && day <= 19)) {
			strValue = "白羊座";
		} else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
			strValue = "金牛座";
		} else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
			strValue = "双子座";
		} else if ((month == 6 && day > 21) || (month == 7 && day <= 22)) {
			strValue = "巨蟹座";
		} else if ((month == 7 && day > 22) || (month == 8 && day <= 22)) {
			strValue = "狮子座";
		} else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
			strValue = "处女座";
		} else if ((month == 9 && day >= 23) || (month == 10 && day <= 23)) {
			strValue = "天秤座";
		} else if ((month == 10 && day > 23) || (month == 11 && day <= 22)) {
			strValue = "天蝎座";
		} else if ((month == 11 && day > 22) || (month == 12 && day <= 21)) {
			strValue = "射手座";
		} else if ((month == 12 && day > 21) || (month == 1 && day <= 19)) {
			strValue = "魔羯座";
		}
		return strValue;
	}

	/**
	 * 根据身份证号，自动获取对应的生肖
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 生肖
	 */
	public static String getZodiacById(String idCard) { // 根据身份证号，自动返回对应的生肖
		if (!isIdentityCode(idCard)) {
			return "身份证号码不正确";
		}
		String sSX[] = { "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗" };
		int year = getDaysByIdCard(idCard, "year");
		int end = 3;
		int x = (year - end) % 12;

		String retValue = "";
		retValue = sSX[x];

		return retValue;
	}

	/**
	 * 根据身份证号，自动获取对应的天干地支
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 天干地支
	 */
	public static String getChineseEraById(String idCard) { // 根据身份证号，自动返回对应的生肖
		if (!isIdentityCode(idCard)) {
			return "身份证号码不正确";
		}
		String sTG[] = { "癸", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "任" };
		String sDZ[] = { "亥", "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌" };
		int year = getDaysByIdCard(idCard, "year");
		int i = (year - 3) % 10;
		int j = (year - 3) % 12;

		String retValue = "";
		retValue = sTG[i] + sDZ[j];

		return retValue;
	}

	/**
	 * 根据身份编号获取户籍省份
	 * 
	 * @param idCard
	 *            身份编码
	 * @return 省级编码。
	 */
	public static String getProvinceByIdCard(String idCard) {
		if (!isIdentityCode(idCard)) {
			return "身份证号码不正确";
		}
		int len = idCard.length();
		String sProvince = null;
		String sProvinNum = "";
		if (len == IDENTITYCODE_OLD || len == IDENTITYCODE_NEW) {
			sProvinNum = idCard.substring(0, 2);
		}
		sProvince = cityCodes.get(sProvinNum);
		return sProvince;
	}

	/**
	 * 保留小数位后两位
	 * 
	 * @param bd
	 * @return
	 */
	public static String decimalToStr(BigDecimal bd) {
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.toString();
	}

	public static String doubleToStr(Double bd) {
		BigDecimal b = (new BigDecimal(bd)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return b.toString();
	}


	/**
	 * 通过身份证号码获取生日
	 * 
	 * @param idCard
	 * @return
	 */
	public static Date getBirthdayDate(String idCard) {
		String birthday = "";
		if (!isIdentityCode(idCard)) {
			return null;
		}
		if (StringUtils.isBlank(idCard)) {
			return null;
		}
		idCard = idCard.trim();
		if (idCard.length() == 15) {
			idCard = updateEighteen(idCard);
		}
		String byear = idCard.substring(6, 10); // 取年份
		String bmonth = idCard.substring(10, 12); // 取月份
		String bday = idCard.substring(12, 14); // 取日期
		birthday = byear + "-" + bmonth + "-" + bday;
		
		return DateParseUtil.parseYYYYMMDD(birthday);
	}
}
