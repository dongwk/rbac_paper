/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.ElementFunction;
import com.app.service.service.ElementFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.ElementFunctionMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
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
    public R<?> list(@RequestBody PageMo pageMo){
        ElementFunction elementFunction = new ElementFunction();
        PageCount<ElementFunction> page = elementFunctionService.selectPageCount(PageMoUtils.toMPPage(pageMo), elementFunction);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(elementFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ElementFunctionMo elementFunctionMo){

        if (elementFunctionMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (elementFunctionMo.getElementId() == null) throw new BizException("elementFunction.add.elementId-empty");
        if (elementFunctionMo.getElementId() < Constants.INT_1 || elementFunctionMo.getElementId() > Integer.MAX_VALUE) throw new BizException("elementFunction.add.elementId-range");
        if (elementFunctionMo.getFunctionId() == null) throw new BizException("elementFunction.add.functionId-empty");
        if (elementFunctionMo.getFunctionId() < Constants.INT_1 || elementFunctionMo.getFunctionId() > Integer.MAX_VALUE) throw new BizException("elementFunction.add.functionId-range");

        ElementFunction elementFunction = MoToDoUtils.toAddDO(elementFunctionMo, ElementFunction.class);
        elementFunctionService.insert(elementFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ElementFunctionMo elementFunctionMo){

        if (elementFunctionMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (elementFunctionMo.getElementId() == null) throw new BizException("elementFunction.add.elementId-empty");
        if (elementFunctionMo.getElementId() < Constants.INT_1 || elementFunctionMo.getElementId() > Integer.MAX_VALUE) throw new BizException("elementFunction.add.elementId-range");
        if (elementFunctionMo.getFunctionId() == null) throw new BizException("elementFunction.add.functionId-empty");
        if (elementFunctionMo.getFunctionId() < Constants.INT_1 || elementFunctionMo.getFunctionId() > Integer.MAX_VALUE) throw new BizException("elementFunction.add.functionId-range");

        ElementFunction elementFunction = MoToDoUtils.toUpdDO(elementFunctionMo, ElementFunction.class);
        elementFunctionService.updateById(elementFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(elementFunctionService.deleteById(id));
    }
}