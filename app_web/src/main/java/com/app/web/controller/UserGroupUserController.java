/**
 *
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.core.common.ThrowBiz;
import com.app.model.model.UserGroupUser;
import com.app.service.service.UserGroupUserService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.base.PageMo;
import com.app.web.mo.UserGroupUserMo;
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
@RequestMapping("/userGroupUser")
public class UserGroupUserController extends BaseController{

    @Autowired
    private UserGroupUserService userGroupUserService;

    @Authorization
    @GetMapping
    public R<?> list(){
        UserGroupUser userGroupUser = new UserGroupUser();
        IPage<UserGroupUser> page = userGroupUserService.listPageCount(getPage(), userGroupUser);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupUserService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupUserMo userGroupUserMo){

        ThrowAssert.isNull(userGroupUserMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(userGroupUserMo.getUserGroupId(), "userGroupUser.add.userGroupId-empty");
        ThrowAssert.isTrue(userGroupUserMo.getUserGroupId() < Constants.INT_1 || userGroupUserMo.getUserGroupId() > Integer.MAX_VALUE, "userGroupUser.add.userGroupId-range");
        ThrowAssert.isNull(userGroupUserMo.getUserId(), "userGroupUser.add.userId-empty");
        ThrowAssert.isTrue(userGroupUserMo.getUserId() < Constants.INT_1 || userGroupUserMo.getUserId() > Integer.MAX_VALUE, "userGroupUser.add.userId-range");

        UserGroupUser userGroupUser = MoToDoUtils.toAddDO(userGroupUserMo, UserGroupUser.class);
        userGroupUserService.save(userGroupUser);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupUserMo userGroupUserMo){

        ThrowAssert.isNull(userGroupUserMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(userGroupUserMo.getUserGroupId(), "userGroupUser.add.userGroupId-empty");
        ThrowAssert.isTrue(userGroupUserMo.getUserGroupId() < Constants.INT_1 || userGroupUserMo.getUserGroupId() > Integer.MAX_VALUE, "userGroupUser.add.userGroupId-range");
        ThrowAssert.isNull(userGroupUserMo.getUserId(), "userGroupUser.add.userId-empty");
        ThrowAssert.isTrue(userGroupUserMo.getUserId() < Constants.INT_1 || userGroupUserMo.getUserId() > Integer.MAX_VALUE, "userGroupUser.add.userId-range");

        UserGroupUser userGroupUser = MoToDoUtils.toUpdDO(userGroupUserMo, UserGroupUser.class);
        userGroupUserService.updateById(userGroupUser);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(userGroupUserService.removeById(id));
    }
}