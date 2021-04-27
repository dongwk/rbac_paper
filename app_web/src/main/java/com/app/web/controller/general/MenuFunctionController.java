package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.MenuFunction;
import com.app.service.service.MenuFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.MenuFunctionPo;
import com.app.web.utils.PoToDoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



/**
*
* @author dongwk
* @date 2021-03-17
* @version 1.0
*/
@RestController
@RequestMapping("/menuFunction")
public class MenuFunctionController extends BaseRestController<MenuFunctionService, MenuFunction> {

    @Autowired
    private MenuFunctionService menuFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        MenuFunction menuFunction = new MenuFunction();
        IPage<MenuFunction> page = menuFunctionService.listPage(getPage(), menuFunction);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(menuFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody MenuFunctionPo menuFunctionPo){
        ThrowAssert.isNull(menuFunctionPo, HttpStatus.BAD_REQUEST);

        MenuFunction menuFunction = PoToDoUtils.toAddDO(menuFunctionPo, MenuFunction.class);
        menuFunctionService.save(menuFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody MenuFunctionPo menuFunctionPo){
        ThrowAssert.isNull(menuFunctionPo, HttpStatus.BAD_REQUEST);

        MenuFunction menuFunction = PoToDoUtils.toUpdDO(menuFunctionPo, MenuFunction.class);
        menuFunctionService.updateById(menuFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(menuFunctionService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody MenuFunctionPo menuFunctionPo){
        MenuFunction menuFunction = PoToDoUtils.toAddDO(menuFunctionPo, MenuFunction.class);
        IPage<MenuFunction> page = menuFunctionService.listPage(getPage(), menuFunction);
        return R.SUCCESS(page);
    }
}