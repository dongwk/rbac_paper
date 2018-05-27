/**
 * @author Administrator
 *
 */
package com.app.common.web.common;


import com.app.common.web.common.enums.R_CODE;

public class R<T> {

	private boolean success;
	private String code;
	private String msg;
	private T data;

	public R() {
		super();
	}

	public R(boolean success, String code) {
		this.success = success;
		this.code = code;
	}
	
	public R(boolean success, String code, String msg) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
	}

	public R(boolean success, String code, String msg, T data) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static R<String> SUCCESS(){
		return new R<String>(true, R_CODE.SUCCESS.CODE, R_CODE.SUCCESS.MSG);
	}

	public static <T> R<T> SUCCESS(T t){
		return new R<T>(true, R_CODE.SUCCESS.CODE, R_CODE.SUCCESS.MSG, t);
	}

	public static R<String> SUCCESS(String msg){
		return new R<String>(true, R_CODE.SUCCESS.CODE, msg);
	}

	public static R<String> SUCCESS(String code, String msg){
		return new R<String>(true, code, msg);
	}

	public static R<String> ERROR(){
		return new R<String>(false, R_CODE.ERROR.CODE, R_CODE.ERROR.MSG);
	}

	public static <T> R<T> ERROR(T t){
		return new R<T>(false, R_CODE.ERROR.CODE, R_CODE.ERROR.MSG, t);
	}

	public static R<String> ERROR(String msg){
		return new R<String>(false, R_CODE.ERROR.CODE, msg);
	}

	public static R<String> ERROR(String code, String msg){
		return new R<String>(false, code, msg);
	}


	public static R<String> E500(){
		return new R<String>(false,R_CODE.SYS_ERROR.CODE, R_CODE.SYS_ERROR.MSG);
	}

	public static R<String> E404(){
		return new R<String>(false,R_CODE.NOT_FOUND.CODE, R_CODE.NOT_FOUND.MSG);
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
		return "R [success=" + success + ", code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}