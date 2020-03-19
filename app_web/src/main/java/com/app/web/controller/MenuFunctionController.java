/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.MenuFunction;
import com.app.service.service.MenuFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.MenuFunctionMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menuFunction")
public class MenuFunctionController extends BaseController{

    @Autowired
    private MenuFunctionService menuFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        MenuFunction menuFunction = new MenuFunction();
        PageCount<MenuFunction> page = menuFunctionService.selectPageCount(PageMoUtils.toMPPage(pageMo), menuFunction);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(menuFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody MenuFunctionMo menuFunctionMo){

        if (menuFunctionMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (menuFunctionMo.getMenuId() == null) throw new BizException("menuFunction.add.menuId-empty");
        if (menuFunctionMo.getMenuId() < Constants.INT_1 || menuFunctionMo.getMenuId() > Integer.MAX_VALUE) throw new BizException("menuFunction.add.menuId-range");
        if (menuFunctionMo.getFunctionId() == null) throw new BizException("menuFunction.add.functionId-empty");
        if (menuFunctionMo.getFunctionId() < Constants.INT_1 || menuFunctionMo.getFunctionId() > Integer.MAX_VALUE) throw new BizException("menuFunction.add.functionId-range");

        MenuFunction menuFunction = MoToDoUtils.toAddDO(menuFunctionMo, MenuFunction.class);
        menuFunctionService.insert(menuFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody MenuFunctionMo menuFunctionMo){

        if (menuFunctionMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (menuFunctionMo.getMenuId() == null) throw new BizException("menuFunction.add.menuId-empty");
        if (menuFunctionMo.getMenuId() < Constants.INT_1 || menuFunctionMo.getMenuId() > Integer.MAX_VALUE) throw new BizException("menuFunction.add.menuId-range");
        if (menuFunctionMo.getFunctionId() == null) throw new BizException("menuFunction.add.functionId-empty");
        if (menuFunctionMo.getFunctionId() < Constants.INT_1 || menuFunctionMo.getFunctionId() > Integer.MAX_VALUE) throw new BizException("menuFunction.add.functionId-range");

        MenuFunction menuFunction = MoToDoUtils.toUpdDO(menuFunctionMo, MenuFunction.class);
        menuFunctionService.updateById(menuFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(menuFunctionService.deleteById(id));
    }
}