package com.app.web.controller.base;

import com.app.common.util.ValidateUtil;
import com.app.common.web.result.R;
import com.app.common.web.result.RP;
import com.app.model.base.BaseModel;
import com.app.model.model.User;
import com.app.service.base.BaseSimpleService;
import com.app.common.util.date.DateUtil;
import com.app.common.util.reflection.GenericUtils;
import com.app.web.util.RPUtils;
import com.app.web.util.RestUtils;
import com.app.web.vo.PageVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class BaseRestController<S extends BaseSimpleService, T extends BaseModel> extends BaseController {

    @Autowired
    protected S baseService;
    /** 提取泛型类型 */
    private final Class<T> tClass = (Class<T>) GenericUtils.extractSecondModelClass(getClass());

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
     *  Get
     *  get /user
     */
    @GetMapping
    public R<?> get(){
        Wrapper<User> wrapper = null;
        if (StringUtils.isNotBlank(getFields())) {
            wrapper = new EntityWrapper<>();
            wrapper.setSqlSelect(RestUtils.filedsToCamel(getFields()));
        }
        Page page = buildPage();

        List<T> list = null;
        if (page != null){
            Page<T> page1 = baseService.selectPage(page, wrapper);
            return R.SUCCESS(RPUtils.parsePR(page1));
        } else {
            list = baseService.selectList(wrapper);
            return R.SUCCESS(list);
        }
    }

    /**
     * Post
     * post /user
     */
    @PostMapping
    public R<?> post(@RequestBody T obj){
        return R.SUCCESS(baseService.insert(obj));
    }

    @GetMapping(value = "/{id}")
    public R<?> get(@PathVariable String id){
        Wrapper<User> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(getFields())) wrapper.setSqlSelect(RestUtils.filedsToCamel(getFields()));
        return R.SUCCESS(baseService.get(id));
    }

    @PutMapping(value = "/{id}")
    public R<?> put(@PathVariable String id, @RequestBody T obj){
        obj.setId(id != null ? Integer.parseInt(id):null);
        obj.setUpdateTime(DateUtil.date());
        return R.SUCCESS(baseService.updateAllColumnById(obj));
    }

    @PatchMapping(value = "/{id}")
    public R<?> patch(@PathVariable String id, @RequestBody T obj){
        obj.setId(id != null ? Integer.parseInt(id):null);
        obj.setUpdateTime(DateUtil.date());
        return R.SUCCESS(baseService.updateById(obj));
    }

    @DeleteMapping(value = "/{id}")
    public R<?> delete(@PathVariable String id){
        if (tClass == null) throw new RuntimeException("该类未声明实体类型 T");

        T obj = null;
        try {
            obj = tClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(tClass.getName()+" 无默认的构造函数");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("该类未声明实体类型 T");
        }
        obj.setId(id != null ? Integer.parseInt(id):null);
        obj.setUpdateTime(DateUtil.date());
        obj.setStatus(Boolean.FALSE);
        return R.SUCCESS(baseService.updateById(obj));
    }


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

    /**
     *
     */
    private Wrapper buildWrapper(){
        Wrapper<User> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(getFields())) wrapper.setSqlSelect(RestUtils.filedsToCamel(getFields()));
        if (StringUtils.isNotBlank(getSort())) wrapper.orderBy(RestUtils.sortToSql(getSort()));
        return wrapper;
    }
    /**
     *
     */
    private Page buildPage(){
        Page page = null;
        if (getPage() > 0 && getPerPage() > 0) page = new Page(getPage(), getPerPage()) ;
        return page;
    }

}
