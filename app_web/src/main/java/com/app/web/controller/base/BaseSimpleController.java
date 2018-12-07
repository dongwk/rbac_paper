package com.app.web.controller.base;

import com.app.common.util.ValidateUtil;
import com.app.common.web.result.R;
import com.app.model.base.BaseModel;
import com.app.service.base.BaseSimpleService;
import com.app.common.util.date.DateUtil;
import com.app.common.util.reflection.GenericUtils;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dongwk
 * @version 1.0
 * @date 2018/11/27 14:42
 */
@Slf4j
public class BaseSimpleController<S extends BaseSimpleService, T extends BaseModel> extends BaseController {

    /** 
     * 关键字
     * sort 排序 逗号分隔 sort=-updated_at
     * fields 过滤字段 逗号分隔
     * 更新创建 时间字段 不再更新列中
     * https://www.oschina.net/translate/best-practices-for-a-pragmatic-restful-api?lang=chs&p=1
     * page perPage 页数 每页条数
     */
    // 基本操作的服务类
    @Autowired
    protected S baseSimpleService;
    // 提取泛型类型
    private final Class<T> tClass = (Class<T>) GenericUtils.extractSecondModelClass(getClass());

    // 默认页数
    private static int DEFAULT_PAGE = 1;
    // 默认条数
    private static int DEFAULT_PER_PAGE = 20;
    // 排序字段
    private static final String SORT = "sort";
    // 过滤字段
    private static final String FIELDS = "fields";
    // 页码字段
    private static final String PAGE = "page";
    // 条数字段
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
    protected String[] getFields(){
        String fields = request.getParameter(FIELDS);
        String[] ary = StringUtils.isNoneEmpty(fields) ? StringUtils.split(fields, ",") : null;
        if (ary != null) {

        }
        return ary;
    }

    /**
     * 排序字段
     * @author dongwk
     * @date 2018/12/4
     * @return [{filed1, asc or desc}, {filed2, asc or desc}]
     */
    protected String[][] getSort(){
        String sort = request.getParameter(SORT);
        String[] fileds = StringUtils.isNoneEmpty(sort) ? StringUtils.split(sort, ","):null;
        if (fileds == null) return null;
        String[][] sortfileds = new String[fileds.length][2];

        for (int i = 0; i < fileds.length; i++) {
            String sortFiled = fileds[i];
            if (sortFiled.startsWith("-")){
                sortfileds[i][0] = sortFiled.substring(1);
                sortfileds[i][1] = "desc";
            } else {
                sortfileds[i][0] = sortFiled;
                sortfileds[i][1] = "asc";
            }
        }

        return sortfileds;
    }

    @GetMapping
    public R<?> get(){
        int perPage = getPerPage();
        int page1 = getPage();

        perPage = (perPage == 0 ? DEFAULT_PER_PAGE : perPage);
        page1 = (page1 == 0 ? DEFAULT_PAGE : page1);

        Page<T> page = baseSimpleService.selectPage(new Page(page1, perPage));
        return R.SUCCESS(page.getRecords());
    }

    @PostMapping
    public R<?> post(@RequestBody T obj){
        return R.SUCCESS(baseSimpleService.insert(obj));
    }

    @GetMapping(value = "/{id}")
    public R<?> get(@PathVariable String id){
        return R.SUCCESS(baseSimpleService.get(id));
    }

    @PutMapping(value = "/{id}")
    public R<?> put(@PathVariable String id, @RequestBody T obj){
        obj.setId(id != null ? Integer.parseInt(id):null);
        obj.setUpdateTime(DateUtil.date());
        return R.SUCCESS(baseSimpleService.updateAllColumnById(obj));
    }

    @PatchMapping(value = "/{id}")
    public R<?> patch(@PathVariable String id, @RequestBody T obj){
        obj.setId(id != null ? Integer.parseInt(id):null);
        obj.setUpdateTime(DateUtil.date());
        return R.SUCCESS(baseSimpleService.updateById(obj));
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
        return R.SUCCESS(baseSimpleService.updateById(obj));
    }
}
