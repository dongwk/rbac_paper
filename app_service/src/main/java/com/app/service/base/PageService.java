package com.app.service.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 分页使用
 * @author dongwk
 * @date 2017年1月18日
 */
public class PageService{

	public static void offsetPage(int offset, int limit) {
		offsetPage(offset, limit, true);
	}

	public static void offsetPage(int offset, int limit, boolean count) {
		PageHelper.offsetPage(offset, limit);
	}

	public static void startPage(int page, int size) {
		startPage(page, size, true);
	}

	public static void startPage(int page, int size, boolean count) {
		PageHelper.startPage(page, size, count);
	}

	public static void setCount(boolean count) {
		Page<?> page = PageHelper.getLocalPage();
		if (page != null) page.setCount(count);
	}
	
	public static void setStartRow(int num) {
		Page<?> page = PageHelper.getLocalPage();
		if (page != null) page.setStartRow(num);
	}

	public static void setPage(int num) {
		Page<?> page = PageHelper.getLocalPage();
		if (page != null) page.setPageNum(num);
	}

	public static void setSize(int size) {
		Page<?> page = PageHelper.getLocalPage();
		if (page != null) page.setPageSize(size);
	}
	
	public static int getPage() {
		Page<?> page = PageHelper.getLocalPage();
		return page != null ? page.getPageNum():0;
	}

	public static int getSize() {
		Page<?> page = PageHelper.getLocalPage();
		return page != null ? page.getPageSize():0;
	}

	public static int getTotal() {
		Page<?> page = PageHelper.getLocalPage();
		return page != null ? (int) page.getTotal():0;
	}
}
