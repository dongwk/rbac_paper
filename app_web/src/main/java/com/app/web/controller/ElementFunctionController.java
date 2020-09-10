/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowBiz;
import com.app.model.model.ElementFunction;
import com.app.service.service.ElementFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.ElementFunctionMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
@RequestMapping("/elementFunction")
public class ElementFunctionController extends BaseController{

    @Autowired
    private ElementFunctionService elementFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        ElementFunction elementFunction = new ElementFunction();
        IPage<ElementFunction> page = elementFunctionService.listPageCount(PageMoUtils.toMPPage(pageMo), elementFunction);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(elementFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ElementFunctionMo elementFunctionMo){

        if (elementFunctionMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (elementFunctionMo.getElementId() == null) ThrowBiz.throwExce("elementFunction.add.elementId-empty");
        if (elementFunctionMo.getElementId() < Constants.INT_1 || elementFunctionMo.getElementId() > Integer.MAX_VALUE) ThrowBiz.throwExce("elementFunction.add.elementId-range");
        if (elementFunctionMo.getFunctionId() == null) ThrowBiz.throwExce("elementFunction.add.functionId-empty");
        if (elementFunctionMo.getFunctionId() < Constants.INT_1 || elementFunctionMo.getFunctionId() > Integer.MAX_VALUE) ThrowBiz.throwExce("elementFunction.add.functionId-range");

        ElementFunction elementFunction = MoToDoUtils.toAddDO(elementFunctionMo, ElementFunction.class);
        elementFunctionService.save(elementFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ElementFunctionMo elementFunctionMo){

        if (elementFunctionMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (elementFunctionMo.getElementId() == null) ThrowBiz.throwExce("elementFunction.add.elementId-empty");
        if (elementFunctionMo.getElementId() < Constants.INT_1 || elementFunctionMo.getElementId() > Integer.MAX_VALUE) ThrowBiz.throwExce("elementFunction.add.elementId-range");
        if (elementFunctionMo.getFunctionId() == null) ThrowBiz.throwExce("elementFunction.add.functionId-empty");
        if (elementFunctionMo.getFunctionId() < Constants.INT_1 || elementFunctionMo.getFunctionId() > Integer.MAX_VALUE) ThrowBiz.throwExce("elementFunction.add.functionId-range");

        ElementFunction elementFunction = MoToDoUtils.toUpdDO(elementFunctionMo, ElementFunction.class);
        elementFunctionService.updateById(elementFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(elementFunctionService.removeById(id));
    }
}