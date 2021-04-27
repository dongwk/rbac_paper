package com.app.common.web.result;

import com.app.common.util.date.DateUtils;
import lombok.Data;

@Data
public class Model<T>{
	private String code;
	private String msg;
	private Long timestamp;
	private T data;

	public Model() {}
	public Model(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.timestamp = DateUtils.timestamp();
	}
}

