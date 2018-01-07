/**
 * @author Administrator
 *
 */
package com.app.web.common;

public class ResultWeb<T> {

	private boolean success;
	private String code;
	private String msg;
	private T data;

	public ResultWeb() {
		super();
	}

	public ResultWeb(boolean success, String code) {
		this.success = success;
		this.code = code;
	}
	
	public ResultWeb(boolean success, String code, String msg) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
	}

	public ResultWeb(boolean success, String code, String msg, T data) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ResultWeb<String> SUCCESS(){
		return new ResultWeb<String>(true, "1", "success");
	}

	public static <T> ResultWeb<T> SUCCESS(T t){
		return new ResultWeb<T>(true, "1", "success", t);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "ResultWeb [success=" + success + ", code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}