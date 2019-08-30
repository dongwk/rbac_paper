package com.app.common.web.result;

import lombok.Data;

import java.util.List;

/** 
 * 统一 web 分页返回值
 * @author dongwk
 * @date 2018/12/6
 */
@Data
public class RP<T> {
    private Integer page;
    private Integer size;
    private Integer totalPage;
    private Integer totalSize;
    private List<T> data;

    public RP() {
    }

    public RP(Integer page, Integer size, Integer totalPage, Integer totalSize, List<T> data) {
        this.page = page;
        this.size = size;
        this.totalPage = totalPage;
        this.totalSize = totalSize;
        this.data = data;
    }
}
