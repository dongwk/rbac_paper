package com.app.common.web.result;

import java.util.List;

/** 
 * 统一 web 分页返回值
 * @author dongwk
 * @date 2018/12/6
 */
public class RP<T> {
    private Integer page;
    private Integer size;
    private Integer totalPage;
    private Integer totalSize;
    private List<T> data;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
