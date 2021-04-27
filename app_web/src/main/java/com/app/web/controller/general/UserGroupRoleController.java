package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.UserGroupRole;
import com.app.service.service.UserGroupRoleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.UserGroupRolePo;
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
public class UserGroupRoleController extends BaseRestController<UserGroupRoleService, UserGroupRole> {

    @Autowired
    private UserGroupRoleService userGroupRoleService;

    @Authorization
    @GetMapping
    public R<?> list(){
        UserGroupRole userGroupRole = new UserGroupRole();
        IPage<UserGroupRole> page = userGroupRoleService.listPage(getPage(), userGroupRole);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupRoleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupRolePo userGroupRolePo){
        ThrowAssert.isNull(userGroupRolePo, HttpStatus.BAD_REQUEST);

        UserGroupRole userGroupRole = PoToDoUtils.toAddDO(userGroupRolePo, UserGroupRole.class);
        userGroupRoleService.save(userGroupRole);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupRolePo userGroupRolePo){
        ThrowAssert.isNull(userGroupRolePo, HttpStatus.BAD_REQUEST);

        UserGroupRole userGroupRole = PoToDoUtils.toUpdDO(userGroupRolePo, UserGroupRole.class);
        userGroupRoleService.updateById(userGroupRole);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(userGroupRoleService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody UserGroupRolePo userGroupRolePo){
        UserGroupRole userGroupRole = PoToDoUtils.toAddDO(userGroupRolePo, UserGroupRole.class);
        IPage<UserGroupRole> page = userGroupRoleService.listPage(getPage(), userGroupRole);
        return R.SUCCESS(page);
    }
}