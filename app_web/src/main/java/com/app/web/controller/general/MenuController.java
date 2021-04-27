package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Menu;
import com.app.service.service.MenuService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.MenuPo;
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
@RequestMapping("/menu")
public class MenuController extends BaseRestController<MenuService, Menu> {

    @Autowired
    private MenuService menuService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Menu menu = new Menu();
        IPage<Menu> page = menuService.listPage(getPage(), menu);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(menuService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody MenuPo menuPo){
        ThrowAssert.isNull(menuPo, HttpStatus.BAD_REQUEST);

        Menu menu = PoToDoUtils.toAddDO(menuPo, Menu.class);
        menuService.save(menu);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody MenuPo menuPo){
        ThrowAssert.isNull(menuPo, HttpStatus.BAD_REQUEST);

        Menu menu = PoToDoUtils.toUpdDO(menuPo, Menu.class);
        menuService.updateById(menu);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(menuService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody MenuPo menuPo){
        Menu menu = PoToDoUtils.toAddDO(menuPo, Menu.class);
        IPage<Menu> page = menuService.listPage(getPage(), menu);
        return R.SUCCESS(page);
    }
}