package com.app.web.controller.base;

import com.app.common.web.common.R;
import com.app.service.base.MapperSupportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author dongwk
 * @date 2018/10/8 16:11
 **/
public class ServiceController<T extends MapperSupportService> extends BaseController {
    private T mapperService;

    @GetMapping(value = "/{id}")
    public R<?> get(@PathVariable(value = "id") long id){
//        Object obj = mapperService.selectById(id);
        return R.SUCCESS();
    }

    @GetMapping(value = "/list")
    public R<?> list(){
//        List<?> obj = mapperService.selectList(null);
        return R.SUCCESS();
    }
}
