/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.Function;
import com.app.service.service.FunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.FunctionMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/function")
public class FunctionController extends BaseController{

    @Autowired
    private FunctionService functionService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        Function function = new Function();
        PageCount<Function> page = functionService.selectPageCount(PageMoUtils.toMPPage(pageMo), function);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(functionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody FunctionMo functionMo){

        if (functionMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(functionMo.getName())) throw new BizException("function.add.name-empty");
        if (functionMo.getName().trim().length() > Constants.INT_32) throw new BizException("function.add.name-max");
        if (StringUtils.isBlank(functionMo.getCode())) throw new BizException("function.add.code-empty");
        if (functionMo.getCode().trim().length() > Constants.INT_32) throw new BizException("function.add.code-max");
        if (StringUtils.isBlank(functionMo.getUrl())) throw new BizException("function.add.url-empty");
        if (functionMo.getUrl().trim().length() > Constants.INT_128) throw new BizException("function.add.url-max");
        functionMo.setName(functionMo.getName().trim());
        functionMo.setCode(functionMo.getCode().trim());
        functionMo.setUrl(functionMo.getUrl().trim());

        Function function = MoToDoUtils.toAddDO(functionMo, Function.class);
        function.setId(null);
        functionService.insert(function);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody FunctionMo functionMo){

        if (functionMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(functionMo.getName())) throw new BizException("function.add.name-empty");
        if (functionMo.getName().trim().length() > Constants.INT_32) throw new BizException("function.add.name-max");
        if (StringUtils.isBlank(functionMo.getCode())) throw new BizException("function.add.code-empty");
        if (functionMo.getCode().trim().length() > Constants.INT_32) throw new BizException("function.add.code-max");
        if (StringUtils.isBlank(functionMo.getUrl())) throw new BizException("function.add.url-empty");
        if (functionMo.getUrl().trim().length() > Constants.INT_128) throw new BizException("function.add.url-max");
        functionMo.setName(functionMo.getName().trim());
        functionMo.setCode(functionMo.getCode().trim());
        functionMo.setUrl(functionMo.getUrl().trim());

        Function function = MoToDoUtils.toUpdDO(functionMo, Function.class);
        functionService.updateById(function);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(functionService.deleteById(id));
    }
}