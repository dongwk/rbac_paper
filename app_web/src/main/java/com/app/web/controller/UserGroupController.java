/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.UserGroup;
import com.app.service.service.UserGroupService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.PageMo;
import com.app.web.mo.UserGroupMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-group")
public class UserGroupController extends BaseController{

    @Autowired
    private UserGroupService userGroupService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        UserGroup userGroup = new UserGroup();
        PageCount<UserGroup> page = userGroupService.selectPageCount(PageMoUtils.toMPPage(pageMo), userGroup);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userGroupService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserGroupMo userGroupMo){

        if (userGroupMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(userGroupMo.getName())) throw new BizException("userGroup.add.name-empty");
        if (userGroupMo.getName().trim().length() > Constants.INT_16) throw new BizException("userGroup.add.name-max");
        if (userGroupMo.getParentId() == null) throw new BizException("userGroup.upd.parentid-empty");
        if (userGroupMo.getParentId() < Constants.INT_1 || userGroupMo.getParentId() > Constants.INT_1) throw new BizException("userGroup.add.name-max");
        userGroupMo.setName(userGroupMo.getName().trim());

        UserGroup userGroup = MoToDoUtils.toAddDO(userGroupMo, UserGroup.class);
        userGroupService.insert(userGroup);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserGroupMo userGroupMo){

        if (userGroupMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(userGroupMo.getName())) throw new BizException("userGroup.add.name-empty");
        if (userGroupMo.getName().trim().length() > Constants.INT_16) throw new BizException("userGroup.add.name-max");
        if (userGroupMo.getParentId() == null) throw new BizException("userGroup.upd.parentid-empty");
        if (userGroupMo.getParentId() < Constants.INT_1 || userGroupMo.getParentId() > Constants.INT_1) throw new BizException("userGroup.add.name-max");
        userGroupMo.setName(userGroupMo.getName().trim());

        UserGroup userGroup = MoToDoUtils.toUpdDO(userGroupMo, UserGroup.class);
        userGroupService.updateById(userGroup);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(userGroupService.deleteById(id));
    }
}