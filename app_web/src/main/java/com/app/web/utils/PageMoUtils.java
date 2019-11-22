package com.app.web.utils;

import com.app.web.constant.Constants;
import com.app.web.mo.PageMo;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * 字段是否可以查询
 *
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 15:30
 */
public class PageMoUtils {

    /**
     * 将前台接收的 pageMo 规范转换成 mybatisplus 所需要的对象，用于查询使用
     * @param pageMo
     * @return
     */
    public static Page toMPPage(PageMo pageMo){
        if (pageMo == null || pageMo.getPage() == null || pageMo.getSize() == null)
            return Constants.DEFAULT_MYBATISPLUS_PAGE;

        return new Page(pageMo.getPage() <= 1 ? 0:pageMo.getPage()-1*pageMo.getSize(), pageMo.getSize());
    }
}
