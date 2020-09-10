package com.app.web.utils;

import com.app.common.web.result.RP;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author dongwk
 * @date 2019/5/11
 */
public class RPUtils {

    public static <T> RP<T> parsePR(Page<T> page){
        return new RP<T>(page.getCurrent(), page.getSize(), page.getPages(), page.getTotal(), page.getRecords());
    }

    public static <T> RP<T> newPR(Integer page, Integer size, Integer totalPage, Integer totalSize, List<T> data){
        return new RP<>(page, size, totalPage, totalSize, data);
    }
}
