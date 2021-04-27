package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Role;
import com.app.service.service.RoleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.RolePo;
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
@RestController
@RequestMapping("/role")
public class RoleController extends BaseRestController<RoleService, Role> {

    @Autowired
    private RoleService roleService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Role role = new Role();
        IPage<Role> page = roleService.listPage(getPage(), role);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RolePo rolePo){
        ThrowAssert.isNull(rolePo, HttpStatus.BAD_REQUEST);

        Role role = PoToDoUtils.toAddDO(rolePo, Role.class);
        roleService.save(role);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RolePo rolePo){
        ThrowAssert.isNull(rolePo, HttpStatus.BAD_REQUEST);

        Role role = PoToDoUtils.toUpdDO(rolePo, Role.class);
        roleService.updateById(role);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(roleService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody RolePo rolePo){
        Role role = PoToDoUtils.toAddDO(rolePo, Role.class);
        IPage<Role> page = roleService.listPage(getPage(), role);
        return R.SUCCESS(page);
    }
}