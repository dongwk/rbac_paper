package com.app.common.web.result;

import lombok.Data;

import java.util.List;

/** 
 * 统一 web 分页返回值
 * @author dongwk
 * @date 2018/12/6
 */
@Data
public class RPage<T> {
    private Integer page;
    private Integer size;
    private Integer totalPage;
    private Integer totalSize;
    private List<T> data;

}
