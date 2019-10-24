/**
 * 
 */
package com.app.web.controller.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenHandler {

	private static Map<String, String> tokenStorage = new HashMap<>();

	/**
	 * 存储 token
	 * @param token token 自身
	 * @param value token 值
	 */
	public void storeToken(String token, String value){
	    tokenStorage.put(token, value);
	}
}