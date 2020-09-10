package com.app.web.controller.base;

import com.app.common.util.ValidateUtil;
import com.app.common.util.reflection.GenericUtils;
import com.app.model.base.BaseModel;
import com.app.service.base.BaseSimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基本操作的服务类
 * 提供基于 Rest 的方法
 * 关键字
 * sort 排序 逗号分隔 sort=-updated_at
 * fields 过滤字段 逗号分隔
 * 更新创建 时间字段 不再更新列中
 * https://www.oschina.net/translate/best-practices-for-a-pragmatic-restful-api?lang=chs&p=1
 * page perPage 页数 每页条数
 *
 * @author dongwk
 * @version 1.0
 * @date 2018/11/27 14:42
 */
@Slf4j
public abstract class BaseRestController<S extends BaseSimpleService, T extends BaseModel> extends BaseController {

    @Autowired
    protected S baseService;
    /** 提取泛型类型 */
    private final Class<T> tClass = (Class<T>) GenericUtils.getSuperclassGeneric(getClass());

    /** 默认页数 */
    protected static int DEFAULT_PAGE = 1;
    /** 默认条数 */
    protected static int DEFAULT_PER_PAGE = 20;
    /** 排序字段 */
    private static final String SORT = "sort";
    /** 过滤字段 */
    private static final String FIELDS = "fields";
    /** 页码字段 */
    private static final String PAGE = "page";
    /** 条数字段 */
    private static final String PER_PAGE = "per_page";

    /**
     * 每页条数
     * @author dongwk
     * @date 2018/12/5
     */
    protected int getPerPage(){
        String perPage = request.getParameter(PER_PAGE);
        return ValidateUtil.isInteger(perPage) ?  Integer.parseInt(perPage) : 0;
    }

    /**
     * 页数
     * @author dongwk
     * @date 2018/12/5
     */
    protected int getPage(){
        String page = request.getParameter(PAGE);
        return ValidateUtil.isInteger(page) ?  Integer.parseInt(page) : 0;
    }

    /**
     * 显示字段
     * @author dongwk
     * @date 2018/12/5
     * @return
     */
    protected String getFields(){
        String fields = request.getParameter(FIELDS);
        return fields;
    }

    /**
     * 排序字段
     * @author dongwk
     * @date 2018/12/4
     * @return
     */
    protected String getSort(){
        String sort = request.getParameter(SORT);
        return sort;
    }


}
