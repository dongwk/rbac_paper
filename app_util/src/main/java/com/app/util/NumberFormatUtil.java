package com.app.util;

import java.text.DecimalFormat;

public class NumberFormatUtil {

	private static DecimalFormat df2 = new DecimalFormat();
	
	public static String format(double val, String pattern){
		df2.applyPattern(pattern);
		return df2.format(val);
	}

	public static String format(String val, String pattern){
		df2.applyPattern(pattern);
		return format(Double.parseDouble(val), pattern);
	}
	
	public static String formatTwodec(double val){
		df2.applyPattern("0.00");
		return df2.format(val);
	}
	
	public static String formatTwodec(String val){
		df2.applyPattern("0.00");
		return formatTwodec(Double.parseDouble(val));
	}
	
	public static String formatInt(double val){
		df2.applyPattern("#");
		return df2.format(val);
	}
	
	public static String formatInt(String val){
		df2.applyPattern("#");
		return formatInt(Double.parseDouble(val));
	}
}
