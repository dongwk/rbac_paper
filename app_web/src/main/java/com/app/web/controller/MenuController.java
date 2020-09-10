/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowBiz;
import com.app.model.model.Menu;
import com.app.service.service.MenuService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.mo.LoginUserMo;
import com.app.web.mo.MenuMo;
import com.app.web.mo.PageMo;
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
@RequestMapping("/menu")
public class MenuController extends BaseRestController<MenuService, Menu> {

    @Autowired
    private MenuService menuService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody(required = false) PageMo pageMo){
        Menu menu = new Menu();
        IPage<Menu> page = menuService.listPage(PageMoUtils.toMPPage(pageMo), menu, Boolean.TRUE, "seq");
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(menuService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody MenuMo menuMo){

        if (menuMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(menuMo.getName())) ThrowBiz.throwExce("menu.add.name-empty");
        if (menuMo.getName().trim().length() > Constants.INT_16) ThrowBiz.throwExce("menu.add.name-max");
        if (menuMo.getParentId() == null) ThrowBiz.throwExce("menu.upd.parentid-empty");
        if (menuMo.getParentId() < Constants.INT_1 || menuMo.getParentId() > Constants.INT_1) ThrowBiz.throwExce("menu.add.name-max");
        menuMo.setName(menuMo.getName().trim());

        Menu menu = MoToDoUtils.toAddDO(menuMo, Menu.class);
        menuService.save(menu);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody MenuMo menuMo){

        if (menuMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (menuMo.getId() == null || menuMo.getId() < Constants.INT_1) ThrowBiz.throwExce("menu.upd.id-empty");
        if (StringUtils.isBlank(menuMo.getName())) ThrowBiz.throwExce("menu.add.name-empty");
        if (menuMo.getName().trim().length() > Constants.INT_16) ThrowBiz.throwExce("menu.add.name-max");
        menuMo.setName(menuMo.getName().trim());

        Menu menu = MoToDoUtils.toUpdDO(menuMo, Menu.class);
        menuService.updateById(menu);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(menuService.removeById(id));
    }

    @Authorization
    @GetMapping("/list")
    public R<?> list(@LoginUser LoginUserMo loginUserMo){
        return R.SUCCESS(menuService.listByUserId(loginUserMo.getId()));
    }
}