/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.core.common.ThrowBiz;
import com.app.model.model.Function;
import com.app.service.service.FunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.FunctionMo;
import com.app.web.mo.base.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/function")
public class FunctionController extends BaseController{

    @Autowired
    private FunctionService functionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Function function = new Function();
        IPage<Function> page = functionService.listPageCount(getPage(), function);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(functionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody FunctionMo functionMo){

        ThrowAssert.isNull(functionMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isBlank(functionMo.getName(), "function.add.name-empty");
        ThrowAssert.isTrue(functionMo.getName().trim().length() > Constants.INT_32, "function.add.name-max");
        ThrowAssert.isBlank(functionMo.getCode(), "function.add.code-empty");
        ThrowAssert.isTrue(functionMo.getCode().trim().length() > Constants.INT_32, "function.add.code-max");
        ThrowAssert.isBlank(functionMo.getUrl(), "function.add.url-empty");
        ThrowAssert.isTrue(functionMo.getUrl().trim().length() > Constants.INT_128, "function.add.url-max");
        functionMo.setName(functionMo.getName().trim());
        functionMo.setCode(functionMo.getCode().trim());
        functionMo.setUrl(functionMo.getUrl().trim());

        Function function = MoToDoUtils.toAddDO(functionMo, Function.class);
        function.setId(null);
        functionService.save(function);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody FunctionMo functionMo){

        ThrowAssert.isNull(functionMo == null, HttpStatus.BAD_REQUEST);
        ThrowAssert.isBlank(functionMo.getName(), "function.add.name-empty");
        ThrowAssert.isTrue(functionMo.getName().trim().length() > Constants.INT_32, "function.add.name-max");
        ThrowAssert.isBlank(functionMo.getCode(), "function.add.code-empty");
        ThrowAssert.isTrue(functionMo.getCode().trim().length() > Constants.INT_32, "function.add.code-max");
        ThrowAssert.isBlank(functionMo.getUrl(), "function.add.url-empty");
        ThrowAssert.isTrue(functionMo.getUrl().trim().length() > Constants.INT_128, "function.add.url-max");
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
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(functionService.removeById(id));
    }
}