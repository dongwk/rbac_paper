/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowBiz;
import com.app.model.model.RoleMenu;
import com.app.service.service.RoleMenuService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.PageMo;
import com.app.web.mo.RoleMenuMo;
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
@RequestMapping("/roleMenu")
public class RoleMenuController extends BaseController{

    @Autowired
    private RoleMenuService roleMenuService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        RoleMenu roleMenu = new RoleMenu();
        IPage<RoleMenu> page = roleMenuService.listPageCount(PageMoUtils.toMPPage(pageMo), roleMenu);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleMenuService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleMenuMo roleMenuMo){

        if (roleMenuMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (roleMenuMo.getMenuId() == null) ThrowBiz.throwExce("roleMenu.add.menuId-empty");
        if (roleMenuMo.getMenuId() < Constants.INT_1 || roleMenuMo.getMenuId() > Integer.MAX_VALUE) ThrowBiz.throwExce("roleMenu.add.menuId-range");
        if (roleMenuMo.getRoleId() == null) ThrowBiz.throwExce("roleMenu.add.roleId-empty");
        if (roleMenuMo.getRoleId() < Constants.INT_1 || roleMenuMo.getRoleId() > Integer.MAX_VALUE) ThrowBiz.throwExce("roleMenu.add.roleId-range");

        RoleMenu roleMenu = MoToDoUtils.toAddDO(roleMenuMo, RoleMenu.class);
        roleMenuService.save(roleMenu);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleMenuMo roleMenuMo){

        if (roleMenuMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (roleMenuMo.getMenuId() == null) ThrowBiz.throwExce("roleMenu.add.menuId-empty");
        if (roleMenuMo.getMenuId() < Constants.INT_1 || roleMenuMo.getMenuId() > Integer.MAX_VALUE) ThrowBiz.throwExce("roleMenu.add.menuId-range");
        if (roleMenuMo.getRoleId() == null) ThrowBiz.throwExce("roleMenu.add.roleId-empty");
        if (roleMenuMo.getRoleId() < Constants.INT_1 || roleMenuMo.getRoleId() > Integer.MAX_VALUE) ThrowBiz.throwExce("roleMenu.add.roleId-range");

        RoleMenu roleMenu = MoToDoUtils.toUpdDO(roleMenuMo, RoleMenu.class);
        roleMenuService.updateById(roleMenu);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(roleMenuService.removeById(id));
    }
}