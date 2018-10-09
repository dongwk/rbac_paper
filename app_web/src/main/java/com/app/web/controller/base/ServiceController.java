package com.app.web.controller.base;

import com.app.service.base.MapperService;
import com.app.web.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author dongwk
 * @date 2018/10/8 16:11
 **/
public class ServiceController<T extends MapperService> extends BaseController {
    private T mapperService;

    @GetMapping(value = "/{id}")
    public R<?> get(@PathVariable(value = "id") long id){
        Object obj = mapperService.selectById(id);
        return R.SUCCESS(obj);
    }

    @GetMapping(value = "/list")
    public R<?> list(){
        List<?> obj = mapperService.selectList(null);
        return R.SUCCESS(obj);
    }
}
