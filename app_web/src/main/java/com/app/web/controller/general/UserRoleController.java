package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.UserRole;
import com.app.service.service.UserRoleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.UserRolePo;
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
public class UserRoleController extends BaseRestController<UserRoleService, UserRole> {

    @Autowired
    private UserRoleService userRoleService;

    @Authorization
    @GetMapping
    public R<?> list(){
        UserRole userRole = new UserRole();
        IPage<UserRole> page = userRoleService.listPage(getPage(), userRole);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userRoleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserRolePo userRolePo){
        ThrowAssert.isNull(userRolePo, HttpStatus.BAD_REQUEST);

        UserRole userRole = PoToDoUtils.toAddDO(userRolePo, UserRole.class);
        userRoleService.save(userRole);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserRolePo userRolePo){
        ThrowAssert.isNull(userRolePo, HttpStatus.BAD_REQUEST);

        UserRole userRole = PoToDoUtils.toUpdDO(userRolePo, UserRole.class);
        userRoleService.updateById(userRole);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(userRoleService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody UserRolePo userRolePo){
        UserRole userRole = PoToDoUtils.toAddDO(userRolePo, UserRole.class);
        IPage<UserRole> page = userRoleService.listPage(getPage(), userRole);
        return R.SUCCESS(page);
    }
}