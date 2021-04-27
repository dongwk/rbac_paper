package com.app.common.web.result;

import lombok.Data;

@Data
public class CountModel<T> extends Model<T>{
	private Integer count;

	public CountModel() {}

	public CountModel(String code, String msg, T data, Integer count) {
		super(code, msg, data);
		this.count = count;
	}

	public CountModel(String code, String msg, T data, Long count) {
		super(code, msg, data);
		this.count = count != null ? count.intValue() : null;
	}
}