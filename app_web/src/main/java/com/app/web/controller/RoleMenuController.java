/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.RoleMenu;
import com.app.service.service.RoleMenuService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.RoleMenuMo;
import com.app.web.utils.MoToDoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController extends BaseController{

    @Autowired
    private RoleMenuService roleMenuService;

    @Authorization
    @GetMapping
    public R<?> list(){
        RoleMenu roleMenu = new RoleMenu();
        IPage<RoleMenu> page = roleMenuService.listPageCount(getPage(), roleMenu);
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

        ThrowAssert.isNull(roleMenuMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(roleMenuMo.getMenuId(), "roleMenu.add.menuId-empty");
        ThrowAssert.isTrue(roleMenuMo.getMenuId() < Constants.INT_1 || roleMenuMo.getMenuId() > Integer.MAX_VALUE, "roleMenu.add.menuId-range");
        ThrowAssert.isNull(roleMenuMo.getRoleId(), "roleMenu.add.roleId-empty");
        ThrowAssert.isTrue(roleMenuMo.getRoleId() < Constants.INT_1 || roleMenuMo.getRoleId() > Integer.MAX_VALUE, "roleMenu.add.roleId-range");

        RoleMenu roleMenu = MoToDoUtils.toAddDO(roleMenuMo, RoleMenu.class);
        roleMenuService.save(roleMenu);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleMenuMo roleMenuMo){

        ThrowAssert.isNull(roleMenuMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(roleMenuMo.getMenuId(), "roleMenu.add.menuId-empty");
        ThrowAssert.isTrue(roleMenuMo.getMenuId() < Constants.INT_1 || roleMenuMo.getMenuId() > Integer.MAX_VALUE, "roleMenu.add.menuId-range");
        ThrowAssert.isNull(roleMenuMo.getRoleId(), "roleMenu.add.roleId-empty");
        ThrowAssert.isTrue(roleMenuMo.getRoleId() < Constants.INT_1 || roleMenuMo.getRoleId() > Integer.MAX_VALUE, "roleMenu.add.roleId-range");

        RoleMenu roleMenu = MoToDoUtils.toUpdDO(roleMenuMo, RoleMenu.class);
        roleMenuService.updateById(roleMenu);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(roleMenuService.removeById(id));
    }
}