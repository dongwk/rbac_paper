/**
 *
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.UserRole;
import com.app.service.service.UserRoleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.UserRoleMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userRole")
public class UserRoleController extends BaseController{

    @Autowired
    private UserRoleService userRoleService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        UserRole userRole = new UserRole();
        PageCount<UserRole> page = userRoleService.selectPageCount(PageMoUtils.toMPPage(pageMo), userRole);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userRoleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserRoleMo userRoleMo){

        if (userRoleMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (userRoleMo.getUserId() == null) throw new BizException("userRole.add.userId-empty");
        if (userRoleMo.getUserId() < Constants.INT_1 || userRoleMo.getUserId() > Integer.MAX_VALUE) throw new BizException("userRole.add.userId-range");
        if (userRoleMo.getRoleId() == null) throw new BizException("userRole.add.roleId-empty");
        if (userRoleMo.getRoleId() < Constants.INT_1 || userRoleMo.getRoleId() > Integer.MAX_VALUE) throw new BizException("userRole.add.roleId-range");

        UserRole userRole = MoToDoUtils.toAddDO(userRoleMo, UserRole.class);
        userRoleService.insert(userRole);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserRoleMo userRoleMo){

        if (userRoleMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (userRoleMo.getUserId() == null) throw new BizException("userRole.add.userId-empty");
        if (userRoleMo.getUserId() < Constants.INT_1 || userRoleMo.getUserId() > Integer.MAX_VALUE) throw new BizException("userRole.add.userId-range");
        if (userRoleMo.getRoleId() == null) throw new BizException("userRole.add.roleId-empty");
        if (userRoleMo.getRoleId() < Constants.INT_1 || userRoleMo.getRoleId() > Integer.MAX_VALUE) throw new BizException("userRole.add.roleId-range");

        UserRole userRole = MoToDoUtils.toUpdDO(userRoleMo, UserRole.class);
        userRoleService.updateById(userRole);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(userRoleService.deleteById(id));
    }
}