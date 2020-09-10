/**
 * 
 */
package com.app.web.controller;

import com.app.common.web.result.R;
import com.app.core.common.ThrowBiz;
import com.app.model.model.Role;
import com.app.service.service.RoleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.PageMo;
import com.app.web.mo.RoleMo;
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
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        Role role = new Role();
        IPage<Role> page = roleService.listPageCount(PageMoUtils.toMPPage(pageMo), role);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleMo roleMo){

        if (roleMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(roleMo.getName())) ThrowBiz.throwExce("role.add.name-empty");
        if (roleMo.getName().trim().length() > 16) ThrowBiz.throwExce("role.add.name-max");
        roleMo.setName(roleMo.getName().trim());

        Role role = MoToDoUtils.toAddDO(roleMo, Role.class);
        role.setId(null);
        roleService.save(role);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping(value = "/{id}")
    public R<?> put(@RequestBody RoleMo roleMo){

        if (roleMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (roleMo.getId() == null || roleMo.getId() < 1) ThrowBiz.throwExce("role.upd.id-empty");
        if (StringUtils.isBlank(roleMo.getName())) ThrowBiz.throwExce("role.add.name-empty");
        if (roleMo.getName().trim().length() > 16) ThrowBiz.throwExce("role.add.name-max");
        roleMo.setName(roleMo.getName().trim());

        Role role = MoToDoUtils.toUpdDO(roleMo, Role.class);
        roleService.updateById(role);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(roleService.removeById(id));
    }
}