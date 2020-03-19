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
import com.app.web.controller.exce.BizException;
import com.app.web.mo.LoginedUserMo;
import com.app.web.mo.PageMo;
import com.app.web.mo.RoleMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import com.app.web.utils.PasswordUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        Role role = new Role();
        PageCount<Role> page = roleService.selectPageCount(PageMoUtils.toMPPage(pageMo), role);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleMo roleMo){

        if (roleMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(roleMo.getName())) throw new BizException("role.add.name-empty");
        if (roleMo.getName().trim().length() > 16) throw new BizException("role.add.name-max");
        roleMo.setName(roleMo.getName().trim());

        Role role = MoToDoUtils.toAddDO(roleMo, Role.class);
        role.setId(null);
        roleService.insert(role);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping(value = "/{id}")
    public R<?> put(@RequestBody RoleMo roleMo){

        if (roleMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (roleMo.getId() == null || roleMo.getId() < 1) throw new BizException("role.upd.id-empty");
        if (StringUtils.isBlank(roleMo.getName())) throw new BizException("role.add.name-empty");
        if (roleMo.getName().trim().length() > 16) throw new BizException("role.add.name-max");
        roleMo.setName(roleMo.getName().trim());

        Role role = MoToDoUtils.toUpdDO(roleMo, Role.class);
        roleService.updateById(role);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(roleService.deleteById(id));
    }
}