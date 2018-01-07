package com.app.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 验证码生成器
 * @author dongwk
 * @date 2017年2月7日
 */
public class CaptchaUtil {

	/**
	 * 生成6位随机数
	 * @return
	 */
	public static String sixCaptcha(){
		return generator(6);
	}

	/**
	 * 生成num位随机数
	 * @return
	 */
	public static String generator(int num){
		return RandomStringUtils.randomNumeric(num);
	}
}
