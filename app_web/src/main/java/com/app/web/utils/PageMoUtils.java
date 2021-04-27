package com.app.web.utils;

import com.app.web.constant.WebConstants;
import com.app.web.po.base.PagePo;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

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
     * 将前台接收的 pagePo 规范转换成 mybatisplus 所需要的对象，用于查询使用
     * @param pagePo
     * @return
     */
    public static Page toMPPage(PagePo pagePo){
        if (pagePo == null || pagePo.getPage() == null || pagePo.getSize() == null ) return WebConstants.defaultPage();

        return new Page(pagePo.getPage() <= 1 ? 0 : (pagePo.getPage()-1)* pagePo.getSize(), pagePo.getSize() < 1 ? WebConstants.DEFAULT_PAGE_SIZE : pagePo.getSize());
    }

    public static Page toMPPage(PagePo pagePo, String orderFiled){
        Page page = toMPPage(pagePo);
        if (StringUtils.isNotBlank(orderFiled)) {
            Arrays.stream(orderFiled.split(",")).forEach(e -> {
                String[] kv = e.split(" ");
                    page.addOrder(kv.length == 1 ? OrderItem.asc(StringUtils.trim(kv[0])) :
                            StringUtils.trim(kv[1]).equalsIgnoreCase("asc")
                                    ? OrderItem.asc(StringUtils.trim(kv[0]))
                                    : OrderItem.desc(StringUtils.trim(kv[0])));

            });
        }
        return page;
    }
}
