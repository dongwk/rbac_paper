/**
 *
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.UserGroupRole;
import com.app.service.service.UserGroupRoleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.UserGroupRoleMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userGroupRole")
public class UserGroupRoleController extends BaseController{

    @Autowired
    private UserGroupRoleService userGroupRoleService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        UserGroupRole userGroupRole = new UserGroupRole();
        PageCount<UserGroupRole> page = userGroupRoleService.selectPageCount(PageMoUtils.toMPPage(pageMo), userGroupRole);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupRoleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupRoleMo userGroupRoleMo){

        if (userGroupRoleMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (userGroupRoleMo.getUserGroupId() == null) throw new BizException("userGroupRole.add.userGroupId-empty");
        if (userGroupRoleMo.getUserGroupId() < Constants.INT_1 || userGroupRoleMo.getUserGroupId() > Integer.MAX_VALUE) throw new BizException("userGroupRole.add.userGroupId-range");
        if (userGroupRoleMo.getRoleId() == null) throw new BizException("userGroupRole.add.roleId-empty");
        if (userGroupRoleMo.getRoleId() < Constants.INT_1 || userGroupRoleMo.getRoleId() > Integer.MAX_VALUE) throw new BizException("userGroupRole.add.roleId-range");

        UserGroupRole userGroupRole = MoToDoUtils.toAddDO(userGroupRoleMo, UserGroupRole.class);
        userGroupRoleService.insert(userGroupRole);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupRoleMo userGroupRoleMo){

        if (userGroupRoleMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (userGroupRoleMo.getUserGroupId() == null) throw new BizException("userGroupRole.add.userGroupId-empty");
        if (userGroupRoleMo.getUserGroupId() < Constants.INT_1 || userGroupRoleMo.getUserGroupId() > Integer.MAX_VALUE) throw new BizException("userGroupRole.add.userGroupId-range");
        if (userGroupRoleMo.getRoleId() == null) throw new BizException("userGroupRole.add.roleId-empty");
        if (userGroupRoleMo.getRoleId() < Constants.INT_1 || userGroupRoleMo.getRoleId() > Integer.MAX_VALUE) throw new BizException("userGroupRole.add.roleId-range");

        UserGroupRole userGroupRole = MoToDoUtils.toUpdDO(userGroupRoleMo, UserGroupRole.class);
        userGroupRoleService.updateById(userGroupRole);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(userGroupRoleService.deleteById(id));
    }
}