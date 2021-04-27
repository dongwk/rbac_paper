package com.app.common.web.result;

import lombok.Data;

@Data
public class PageModel<T> extends Model<T>{
	private Integer page;
	private Integer size;
	private Integer totalPage;
	private Integer totalSize;

	public PageModel() {}

	public PageModel(String code, String msg, T data, Integer page, Integer size, Integer totalPage, Integer totalSize) {
		super(code, msg, data);
		this.page = page;
		this.size = size;
		this.totalPage = totalPage;
		this.totalSize = totalSize;
	}
}