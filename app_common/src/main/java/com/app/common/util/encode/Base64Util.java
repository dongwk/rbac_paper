package com.app.common.util.encode;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

	private final static String CHARSET = "UTF-8";

	/**
	 * BASE64 编码，返回值按照 charset 编码
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String encode(byte[] bytes, String charset) {
		try {
			return new String(Base64.encodeBase64(bytes), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * BASE64 解码，返回值按照 charset 编码
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String decode(byte[] bytes, String charset) {
		try {
			return new String(Base64.decodeBase64(bytes), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * BASE64 编码，str和返回值都按照 charset 编码
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String encode(String str, String charset) {
		try {
			return new String(Base64.encodeBase64(str.getBytes(charset)), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * BASE64 解码，str和返回值都按照 charset 编码
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String decode(String str, String charset) {
		try {
			return new String(Base64.decodeBase64(str.getBytes(charset)), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 默认按照 UTF-8 编码，进行BASE64编码
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		try {
			return new String(Base64.encodeBase64(str.getBytes(CHARSET)), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 默认按照 UTF-8 编码，进行BASE64解码
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		try {
			return new String(Base64.decodeBase64(CHARSET), CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "我";
		for (byte s : str.getBytes("gbk")) System.out.print(s+" ");
		System.out.println();

		for (byte s : str.getBytes("utf-8")) System.out.print(s+" ");
		System.out.println();
		
		System.out.println(new String(str.getBytes("gbk"), "gbk").getBytes("utf-8"));
	}
}