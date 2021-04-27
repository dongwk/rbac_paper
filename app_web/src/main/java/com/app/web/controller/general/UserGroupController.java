package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.UserGroup;
import com.app.service.service.UserGroupService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.UserGroupPo;
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
public class UserGroupController extends BaseRestController<UserGroupService, UserGroup> {

    @Autowired
    private UserGroupService userGroupService;

    @Authorization
    @GetMapping
    public R<?> list(){
        UserGroup userGroup = new UserGroup();
        IPage<UserGroup> page = userGroupService.listPage(getPage(), userGroup);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupPo userGroupPo){
        ThrowAssert.isNull(userGroupPo, HttpStatus.BAD_REQUEST);

        UserGroup userGroup = PoToDoUtils.toAddDO(userGroupPo, UserGroup.class);
        userGroupService.save(userGroup);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupPo userGroupPo){
        ThrowAssert.isNull(userGroupPo, HttpStatus.BAD_REQUEST);

        UserGroup userGroup = PoToDoUtils.toUpdDO(userGroupPo, UserGroup.class);
        userGroupService.updateById(userGroup);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(userGroupService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody UserGroupPo userGroupPo){
        UserGroup userGroup = PoToDoUtils.toAddDO(userGroupPo, UserGroup.class);
        IPage<UserGroup> page = userGroupService.listPage(getPage(), userGroup);
        return R.SUCCESS(page);
    }
}