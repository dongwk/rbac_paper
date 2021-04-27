package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.RoleMenu;
import com.app.service.service.RoleMenuService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.RoleMenuPo;
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
public class RoleMenuController extends BaseRestController<RoleMenuService, RoleMenu> {

    @Autowired
    private RoleMenuService roleMenuService;

    @Authorization
    @GetMapping
    public R<?> list(){
        RoleMenu roleMenu = new RoleMenu();
        IPage<RoleMenu> page = roleMenuService.listPage(getPage(), roleMenu);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleMenuService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleMenuPo roleMenuPo){
        ThrowAssert.isNull(roleMenuPo, HttpStatus.BAD_REQUEST);

        RoleMenu roleMenu = PoToDoUtils.toAddDO(roleMenuPo, RoleMenu.class);
        roleMenuService.save(roleMenu);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleMenuPo roleMenuPo){
        ThrowAssert.isNull(roleMenuPo, HttpStatus.BAD_REQUEST);

        RoleMenu roleMenu = PoToDoUtils.toUpdDO(roleMenuPo, RoleMenu.class);
        roleMenuService.updateById(roleMenu);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(roleMenuService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody RoleMenuPo roleMenuPo){
        RoleMenu roleMenu = PoToDoUtils.toAddDO(roleMenuPo, RoleMenu.class);
        IPage<RoleMenu> page = roleMenuService.listPage(getPage(), roleMenu);
        return R.SUCCESS(page);
    }
}