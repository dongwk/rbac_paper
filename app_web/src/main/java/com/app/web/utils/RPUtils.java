package com.app.web.utils;

import com.app.web.common.RP;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author dongwk
 * @date 2019/5/11
 */
public class RPUtils {

    public static <T> RP<T> parsePR(IPage<T> page){
        return new RP<T>(page.getCurrent(), page.getSize(), page.getPages(), page.getTotal(), page.getRecords());
    }

    public static <T> RP<T> newPR(Integer page, Integer size, Integer totalPage, Integer totalSize, List<T> data){
        return new RP<>(page, size, totalPage, totalSize, data);
    }
}
