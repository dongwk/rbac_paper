/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowBiz;
import com.app.model.model.MenuFunction;
import com.app.service.service.MenuFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.MenuFunctionMo;
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
@RequestMapping("/menuFunction")
public class MenuFunctionController extends BaseController{

    @Autowired
    private MenuFunctionService menuFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        MenuFunction menuFunction = new MenuFunction();
        IPage<MenuFunction> page = menuFunctionService.listPageCount(PageMoUtils.toMPPage(pageMo), menuFunction);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(menuFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody MenuFunctionMo menuFunctionMo){

        if (menuFunctionMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (menuFunctionMo.getMenuId() == null) ThrowBiz.throwExce("menuFunction.add.menuId-empty");
        if (menuFunctionMo.getMenuId() < Constants.INT_1 || menuFunctionMo.getMenuId() > Integer.MAX_VALUE) ThrowBiz.throwExce("menuFunction.add.menuId-range");
        if (menuFunctionMo.getFunctionId() == null) ThrowBiz.throwExce("menuFunction.add.functionId-empty");
        if (menuFunctionMo.getFunctionId() < Constants.INT_1 || menuFunctionMo.getFunctionId() > Integer.MAX_VALUE) ThrowBiz.throwExce("menuFunction.add.functionId-range");

        MenuFunction menuFunction = MoToDoUtils.toAddDO(menuFunctionMo, MenuFunction.class);
        menuFunctionService.save(menuFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody MenuFunctionMo menuFunctionMo){

        if (menuFunctionMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (menuFunctionMo.getMenuId() == null) ThrowBiz.throwExce("menuFunction.add.menuId-empty");
        if (menuFunctionMo.getMenuId() < Constants.INT_1 || menuFunctionMo.getMenuId() > Integer.MAX_VALUE) ThrowBiz.throwExce("menuFunction.add.menuId-range");
        if (menuFunctionMo.getFunctionId() == null) ThrowBiz.throwExce("menuFunction.add.functionId-empty");
        if (menuFunctionMo.getFunctionId() < Constants.INT_1 || menuFunctionMo.getFunctionId() > Integer.MAX_VALUE) ThrowBiz.throwExce("menuFunction.add.functionId-range");

        MenuFunction menuFunction = MoToDoUtils.toUpdDO(menuFunctionMo, MenuFunction.class);
        menuFunctionService.updateById(menuFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(menuFunctionService.removeById(id));
    }
}