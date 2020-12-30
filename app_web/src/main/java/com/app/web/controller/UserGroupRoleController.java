/**
 *
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.core.common.ThrowBiz;
import com.app.model.model.UserGroupRole;
import com.app.service.service.UserGroupRoleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.base.PageMo;
import com.app.web.mo.UserGroupRoleMo;
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
@RequestMapping("/userGroupRole")
public class UserGroupRoleController extends BaseController{

    @Autowired
    private UserGroupRoleService userGroupRoleService;

    @Authorization
    @GetMapping
    public R<?> list(){
        UserGroupRole userGroupRole = new UserGroupRole();
        IPage<UserGroupRole> page = userGroupRoleService.listPageCount(getPage(), userGroupRole);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupRoleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupRoleMo userGroupRoleMo){

        ThrowAssert.isNull(userGroupRoleMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(userGroupRoleMo.getUserGroupId(), "userGroupRole.add.userGroupId-empty");
        ThrowAssert.isTrue(userGroupRoleMo.getUserGroupId() < Constants.INT_1 || userGroupRoleMo.getUserGroupId() > Integer.MAX_VALUE, "userGroupRole.add.userGroupId-range");
        ThrowAssert.isNull(userGroupRoleMo.getRoleId(), "userGroupRole.add.roleId-empty");
        ThrowAssert.isTrue(userGroupRoleMo.getRoleId() < Constants.INT_1 || userGroupRoleMo.getRoleId() > Integer.MAX_VALUE, "userGroupRole.add.roleId-range");

        UserGroupRole userGroupRole = MoToDoUtils.toAddDO(userGroupRoleMo, UserGroupRole.class);
        userGroupRoleService.save(userGroupRole);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupRoleMo userGroupRoleMo){

        ThrowAssert.isNull(userGroupRoleMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(userGroupRoleMo.getUserGroupId(), "userGroupRole.add.userGroupId-empty");
        ThrowAssert.isTrue(userGroupRoleMo.getUserGroupId() < Constants.INT_1 || userGroupRoleMo.getUserGroupId() > Integer.MAX_VALUE, "userGroupRole.add.userGroupId-range");
        ThrowAssert.isNull(userGroupRoleMo.getRoleId(), "userGroupRole.add.roleId-empty");
        ThrowAssert.isTrue(userGroupRoleMo.getRoleId() < Constants.INT_1 || userGroupRoleMo.getRoleId() > Integer.MAX_VALUE, "userGroupRole.add.roleId-range");

        UserGroupRole userGroupRole = MoToDoUtils.toUpdDO(userGroupRoleMo, UserGroupRole.class);
        userGroupRoleService.updateById(userGroupRole);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(userGroupRoleService.removeById(id));
    }
}