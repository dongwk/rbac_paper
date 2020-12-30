/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.core.common.ThrowBiz;
import com.app.model.model.RoleFunction;
import com.app.service.service.RoleFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.base.PageMo;
import com.app.web.mo.RoleFunctionMo;
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
@RequestMapping("/roleFunction")
public class RoleFunctionController extends BaseController{

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        RoleFunction roleFunction = new RoleFunction();
        IPage<RoleFunction> page = roleFunctionService.listPageCount(getPage(), roleFunction);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleFunctionMo roleFunctionMo){

        ThrowAssert.isNull(roleFunctionMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(roleFunctionMo.getRoleId(), "roleFunction.add.roleI-empty");
        ThrowAssert.isTrue(roleFunctionMo.getRoleId() < Constants.INT_1 || roleFunctionMo.getRoleId() > Integer.MAX_VALUE, "roleFunction.add.roleI-range");
        ThrowAssert.isNull(roleFunctionMo.getFunctionId(), "roleFunction.add.functionId-empty");
        ThrowAssert.isTrue(roleFunctionMo.getFunctionId() < Constants.INT_1 || roleFunctionMo.getFunctionId() > Integer.MAX_VALUE, "roleFunction.add.functionId-range");

        RoleFunction roleFunction = MoToDoUtils.toAddDO(roleFunctionMo, RoleFunction.class);
        roleFunctionService.save(roleFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleFunctionMo roleFunctionMo){

        ThrowAssert.isNull(roleFunctionMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(roleFunctionMo.getRoleId(), "roleFunction.add.roleI-empty");
        ThrowAssert.isTrue(roleFunctionMo.getRoleId() < Constants.INT_1 || roleFunctionMo.getRoleId() > Integer.MAX_VALUE, "roleFunction.add.roleI-range");
        ThrowAssert.isNull(roleFunctionMo.getFunctionId(), "roleFunction.add.functionId-empty");
        ThrowAssert.isTrue(roleFunctionMo.getFunctionId() < Constants.INT_1 || roleFunctionMo.getFunctionId() > Integer.MAX_VALUE, "roleFunction.add.functionId-range");


        RoleFunction roleFunction = MoToDoUtils.toUpdDO(roleFunctionMo, RoleFunction.class);
        roleFunctionService.updateById(roleFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(roleFunctionService.removeById(id));
    }
}