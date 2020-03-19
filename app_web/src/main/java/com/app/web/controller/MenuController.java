/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.Menu;
import com.app.service.service.MenuService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseRestController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.MenuMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseRestController<MenuService, Menu> {

    @Autowired
    private MenuService menuService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        Menu menu = new Menu();
        PageCount<Menu> page = menuService.selectPageCount(PageMoUtils.toMPPage(pageMo), menu);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(menuService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody MenuMo menuMo){

        if (menuMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(menuMo.getName())) throw new BizException("menu.add.name-empty");
        if (menuMo.getName().trim().length() > Constants.INT_16) throw new BizException("menu.add.name-max");
        if (menuMo.getParentId() == null) throw new BizException("menu.upd.parentid-empty");
        if (menuMo.getParentId() < Constants.INT_1 || menuMo.getParentId() > Constants.INT_1) throw new BizException("menu.add.name-max");
        menuMo.setName(menuMo.getName().trim());

        Menu menu = MoToDoUtils.toAddDO(menuMo, Menu.class);
        menuService.insert(menu);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody MenuMo menuMo){

        if (menuMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (menuMo.getId() == null || menuMo.getId() < Constants.INT_1) throw new BizException("menu.upd.id-empty");
        if (StringUtils.isBlank(menuMo.getName())) throw new BizException("menu.add.name-empty");
        if (menuMo.getName().trim().length() > Constants.INT_16) throw new BizException("menu.add.name-max");
        menuMo.setName(menuMo.getName().trim());

        Menu menu = MoToDoUtils.toUpdDO(menuMo, Menu.class);
        menuService.updateById(menu);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(menuService.deleteById(id));
    }
}