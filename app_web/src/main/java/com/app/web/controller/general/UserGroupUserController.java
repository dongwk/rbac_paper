package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.UserGroupUser;
import com.app.service.service.UserGroupUserService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.UserGroupUserPo;
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
public class UserGroupUserController extends BaseRestController<UserGroupUserService, UserGroupUser> {

    @Autowired
    private UserGroupUserService userGroupUserService;

    @Authorization
    @GetMapping
    public R<?> list(){
        UserGroupUser userGroupUser = new UserGroupUser();
        IPage<UserGroupUser> page = userGroupUserService.listPage(getPage(), userGroupUser);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupUserService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupUserPo userGroupUserPo){
        ThrowAssert.isNull(userGroupUserPo, HttpStatus.BAD_REQUEST);

        UserGroupUser userGroupUser = PoToDoUtils.toAddDO(userGroupUserPo, UserGroupUser.class);
        userGroupUserService.save(userGroupUser);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupUserPo userGroupUserPo){
        ThrowAssert.isNull(userGroupUserPo, HttpStatus.BAD_REQUEST);

        UserGroupUser userGroupUser = PoToDoUtils.toUpdDO(userGroupUserPo, UserGroupUser.class);
        userGroupUserService.updateById(userGroupUser);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(userGroupUserService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody UserGroupUserPo userGroupUserPo){
        UserGroupUser userGroupUser = PoToDoUtils.toAddDO(userGroupUserPo, UserGroupUser.class);
        IPage<UserGroupUser> page = userGroupUserService.listPage(getPage(), userGroupUser);
        return R.SUCCESS(page);
    }
}