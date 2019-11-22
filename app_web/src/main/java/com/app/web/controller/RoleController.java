/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.web.result.R;
import com.app.model.model.Role;
import com.app.service.service.RoleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginedUser;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.base.BaseRestController;
import com.app.web.mo.LoginedUserMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.PageMoUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    @Authorization
    public R<?> list(@RequestBody PageMo pageMo){
        Role role = new Role();
        PageCount<Role> page = roleService.selectPageCount(PageMoUtils.toMPPage(pageMo), role);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @PostMapping
    @Authorization
    public R<?> add(@RequestBody PageMo pageMo){

        EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
        wrapper.setEntity(new Role());

        return R.SUCCESS();
    }
}