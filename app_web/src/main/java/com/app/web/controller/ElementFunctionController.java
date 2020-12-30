/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.ElementFunction;
import com.app.service.service.ElementFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.ElementFunctionMo;
import com.app.web.utils.MoToDoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elementFunction")
public class ElementFunctionController extends BaseController{

    @Autowired
    private ElementFunctionService elementFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        ElementFunction elementFunction = new ElementFunction();
        IPage<ElementFunction> page = elementFunctionService.listPageCount(getPage(), elementFunction);
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

        ThrowAssert.isNull(elementFunctionMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(elementFunctionMo.getElementId(),"elementFunction.add.elementId-empty");
        ThrowAssert.isTrue(elementFunctionMo.getElementId() < Constants.INT_1 || elementFunctionMo.getElementId() > Integer.MAX_VALUE,"elementFunction.add.elementId-range");
        ThrowAssert.isNull(elementFunctionMo.getFunctionId(),"elementFunction.add.functionId-empty");
        ThrowAssert.isTrue(elementFunctionMo.getFunctionId() < Constants.INT_1 || elementFunctionMo.getFunctionId() > Integer.MAX_VALUE,"elementFunction.add.functionId-range");

        ElementFunction elementFunction = MoToDoUtils.toAddDO(elementFunctionMo, ElementFunction.class);
        elementFunctionService.save(elementFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ElementFunctionMo elementFunctionMo){

        ThrowAssert.isNull(elementFunctionMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(elementFunctionMo.getElementId(),"elementFunction.add.elementId-empty");
        ThrowAssert.isTrue(elementFunctionMo.getElementId() < Constants.INT_1 || elementFunctionMo.getElementId() > Integer.MAX_VALUE, "elementFunction.add.elementId-range");
        ThrowAssert.isNull(elementFunctionMo.getFunctionId(), "elementFunction.add.functionId-empty");
        ThrowAssert.isTrue(elementFunctionMo.getFunctionId() < Constants.INT_1 || elementFunctionMo.getFunctionId() > Integer.MAX_VALUE,"elementFunction.add.functionId-range");

        ElementFunction elementFunction = MoToDoUtils.toUpdDO(elementFunctionMo, ElementFunction.class);
        elementFunctionService.updateById(elementFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(elementFunctionService.removeById(id));
    }
}