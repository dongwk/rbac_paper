package com.app.common.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json 格式数据转换各种类型工具类
 * @author Administrator
 *
 */
public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	{
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 转换成json字符串
	 * @param t
	 * @return
	 */
	public static <T> String toJson(T t) {
		try {
			return mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换成json字符串，空的字符串不转换
	 * @param t
	 * @return
	 */
	public static <T> String toJsonNonNull(T t) {
		try {
			mapper.setSerializationInclusion(Include.NON_NULL);
			return mapper.writeValueAsString(t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换成实体Bean
	 * @param json
	 * @param vt
	 * @return
	 */
	public static <T> T toBean(String json, Class<T> vt) {
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, vt);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 转换成Map
	 * @param json
	 * @param vt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json) {
		if (!StringUtils.isEmpty(json)) {
			try {
				return mapper.readValue(json, Map.class);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	/**
	 * 转换成List
	 * @param json
	 * @param vt
	 * @return
	 */
	public static <T> List<T> toList(String json, Class<T> vt) {
		List<T> lst = null;
		if (!StringUtils.isEmpty(json)) {
			try {
				JavaType javaType = mapper.getTypeFactory().constructParametrizedType(List.class, List.class, vt);
				lst = mapper.readValue(json, javaType);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return lst;
	}
}
