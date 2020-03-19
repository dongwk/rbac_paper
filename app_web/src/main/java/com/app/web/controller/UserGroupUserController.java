/**
 *
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.UserGroupUser;
import com.app.service.service.UserGroupRoleService;
import com.app.service.service.UserGroupUserService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.PageMo;
import com.app.web.mo.UserGroupUserMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userGroupUser")
public class UserGroupUserController extends BaseController{

    @Autowired
    private UserGroupUserService userGroupUserService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        UserGroupUser userGroupUser = new UserGroupUser();
        PageCount<UserGroupUser> page = userGroupUserService.selectPageCount(PageMoUtils.toMPPage(pageMo), userGroupUser);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupUserService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupUserMo userGroupUserMo){

        if (userGroupUserMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (userGroupUserMo.getUserGroupId() == null) throw new BizException("userGroupUser.add.userGroupId-empty");
        if (userGroupUserMo.getUserGroupId() < Constants.INT_1 || userGroupUserMo.getUserGroupId() > Integer.MAX_VALUE) throw new BizException("userGroupUser.add.userGroupId-range");
        if (userGroupUserMo.getUserId() == null) throw new BizException("userGroupUser.add.userId-empty");
        if (userGroupUserMo.getUserId() < Constants.INT_1 || userGroupUserMo.getUserId() > Integer.MAX_VALUE) throw new BizException("userGroupUser.add.userId-range");

        UserGroupUser userGroupUser = MoToDoUtils.toAddDO(userGroupUserMo, UserGroupUser.class);
        userGroupUserService.insert(userGroupUser);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupUserMo userGroupUserMo){

        if (userGroupUserMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (userGroupUserMo.getUserGroupId() == null) throw new BizException("userGroupUser.add.userGroupId-empty");
        if (userGroupUserMo.getUserGroupId() < Constants.INT_1 || userGroupUserMo.getUserGroupId() > Integer.MAX_VALUE) throw new BizException("userGroupUser.add.userGroupId-range");
        if (userGroupUserMo.getUserId() == null) throw new BizException("userGroupUser.add.userId-empty");
        if (userGroupUserMo.getUserId() < Constants.INT_1 || userGroupUserMo.getUserId() > Integer.MAX_VALUE) throw new BizException("userGroupUser.add.userId-range");

        UserGroupUser userGroupUser = MoToDoUtils.toUpdDO(userGroupUserMo, UserGroupUser.class);
        userGroupUserService.updateById(userGroupUser);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(userGroupUserService.deleteById(id));
    }
}