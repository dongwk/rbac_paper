/**
 * @author Administrator
 *
 */
package com.app.manage.web.common;


import com.app.common.constant.Constants;
import com.app.common.util.date.DateUtils;
import com.app.manage.web.utils.RPUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Collections;

@Accessors(chain = true)
@Data
@ToString
public class R<T>{

	private String code;
	private String msg;
	private Long timestamp;
	private T data;
	public R(){
	}

	public R(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.timestamp = DateUtils.timestamp();
	}


	public final static R EMPTY_LIST(){
		return new R(Constants.DEF_SUCCCESS_CODE, Constants.DEF_SUCCCESS_MSG, Collections.EMPTY_LIST);
	}
	public final static R SUCCESS(){
		return new R(Constants.DEF_SUCCCESS_CODE, Constants.DEF_SUCCCESS_MSG, null);
	}

	public final static <T> R<T> SUCCESS(IPage t){
		return new R(Constants.DEF_SUCCCESS_CODE, Constants.DEF_SUCCCESS_MSG, RPUtils.parsePR(t));
	}

	public final static <T> R<T> SUCCESS(T t){
		return new R(Constants.DEF_SUCCCESS_CODE, Constants.DEF_SUCCCESS_MSG, t);
	}

	public final static R SUCCESS(String msg){
		return new R(Constants.DEF_SUCCCESS_CODE, msg, null);
	}

	public final static R FAIL(){
		return new R(Constants.DEF_FAIL_CODE, Constants.DEF_FAIL_MSG, null);
	}

	public final static <T> R<T> FAIL(T t){
		return new R(Constants.DEF_FAIL_CODE, Constants.DEF_FAIL_MSG, t);
	}

	public final static <T> R<T> FAIL(String code, T t){
		return new R(code, Constants.DEF_FAIL_MSG, t);
	}

	public final static R FAIL(String msg){
		return new R(Constants.DEF_FAIL_CODE, msg, null);
	}

	public final static R FAIL(String code, String msg){
		return new R(code, msg, null);
	}
}