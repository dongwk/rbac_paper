package com.app.web.utils;

import com.app.web.constant.WebConstants;
import com.app.web.mo.PageMo;
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
     * 将前台接收的 pageMo 规范转换成 mybatisplus 所需要的对象，用于查询使用
     * @param pageMo
     * @return
     */
    public static Page toMPPage(PageMo pageMo){
        if (pageMo == null || pageMo.getPage() == null || pageMo.getSize() == null ) return WebConstants.defaultPage();

        return new Page(pageMo.getPage() <= 1 ? 0 : (pageMo.getPage()-1)*pageMo.getSize(), pageMo.getSize() < 1 ? WebConstants.DEFAULT_PAGE_SIZE : pageMo.getSize());
    }

    public static Page toMPPage(PageMo pageMo, String orderFiled){
        Page page = toMPPage(pageMo);
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
