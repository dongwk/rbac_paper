/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.core.common.ThrowBiz;
import com.app.model.model.UserGroup;
import com.app.service.service.UserGroupService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.base.PageMo;
import com.app.web.mo.UserGroupMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/user-group")
public class UserGroupController extends BaseController{

    @Autowired
    private UserGroupService userGroupService;

    @Authorization
    @GetMapping
    public R<?> list(){
        UserGroup userGroup = new UserGroup();
        IPage<UserGroup> page = userGroupService.listPageCount(getPage(), userGroup);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupMo userGroupMo){

        ThrowAssert.isNull(userGroupMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isBlank(userGroupMo.getName(), "userGroup.add.name-empty");
        ThrowAssert.isTrue(userGroupMo.getName().trim().length() > Constants.INT_16, "userGroup.add.name-max");
        ThrowAssert.isNull(userGroupMo.getParentId(), "userGroup.upd.parentid-empty");
        ThrowAssert.isTrue(userGroupMo.getParentId() < Constants.INT_1 || userGroupMo.getParentId() > Constants.INT_1, "userGroup.add.name-max");
        userGroupMo.setName(userGroupMo.getName().trim());

        UserGroup userGroup = MoToDoUtils.toAddDO(userGroupMo, UserGroup.class);
        userGroupService.save(userGroup);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupMo userGroupMo){

        ThrowAssert.isNull(userGroupMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isBlank(userGroupMo.getName(), "userGroup.add.name-empty");
        ThrowAssert.isTrue(userGroupMo.getName().trim().length() > Constants.INT_16, "userGroup.add.name-max");
        ThrowAssert.isNull(userGroupMo.getParentId(), "userGroup.upd.parentid-empty");
        ThrowAssert.isTrue(userGroupMo.getParentId() < Constants.INT_1 || userGroupMo.getParentId() > Constants.INT_1, "userGroup.add.name-max");
        userGroupMo.setName(userGroupMo.getName().trim());

        UserGroup userGroup = MoToDoUtils.toUpdDO(userGroupMo, UserGroup.class);
        userGroupService.updateById(userGroup);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id == null, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(userGroupService.removeById(id));
    }
}