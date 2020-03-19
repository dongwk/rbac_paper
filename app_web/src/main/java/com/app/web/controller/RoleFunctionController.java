/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.RoleFunction;
import com.app.service.service.RoleFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.RoleFunctionMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roleFunction")
public class RoleFunctionController extends BaseController{

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        RoleFunction roleFunction = new RoleFunction();
        PageCount<RoleFunction> page = roleFunctionService.selectPageCount(PageMoUtils.toMPPage(pageMo), roleFunction);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleFunctionMo roleFunctionMo){

        if (roleFunctionMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (roleFunctionMo.getRoleId() == null) throw new BizException("roleFunction.add.roleI-empty");
        if (roleFunctionMo.getRoleId() < Constants.INT_1 || roleFunctionMo.getRoleId() > Integer.MAX_VALUE) throw new BizException("roleFunction.add.roleI-range");
        if (roleFunctionMo.getFunctionId() == null) throw new BizException("roleFunction.add.functionId-empty");
        if (roleFunctionMo.getFunctionId() < Constants.INT_1 || roleFunctionMo.getFunctionId() > Integer.MAX_VALUE) throw new BizException("roleFunction.add.functionId-range");

        RoleFunction roleFunction = MoToDoUtils.toAddDO(roleFunctionMo, RoleFunction.class);
        roleFunctionService.insert(roleFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleFunctionMo roleFunctionMo){

        if (roleFunctionMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (roleFunctionMo.getRoleId() == null) throw new BizException("roleFunction.add.roleI-empty");
        if (roleFunctionMo.getRoleId() < Constants.INT_1 || roleFunctionMo.getRoleId() > Integer.MAX_VALUE) throw new BizException("roleFunction.add.roleI-range");
        if (roleFunctionMo.getFunctionId() == null) throw new BizException("roleFunction.add.functionId-empty");
        if (roleFunctionMo.getFunctionId() < Constants.INT_1 || roleFunctionMo.getFunctionId() > Integer.MAX_VALUE) throw new BizException("roleFunction.add.functionId-range");


        RoleFunction roleFunction = MoToDoUtils.toUpdDO(roleFunctionMo, RoleFunction.class);
        roleFunctionService.updateById(roleFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(roleFunctionService.deleteById(id));
    }
}