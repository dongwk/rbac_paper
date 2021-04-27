package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.RoleFunction;
import com.app.service.service.RoleFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.RoleFunctionPo;
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
public class RoleFunctionController extends BaseRestController<RoleFunctionService, RoleFunction> {

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        RoleFunction roleFunction = new RoleFunction();
        IPage<RoleFunction> page = roleFunctionService.listPage(getPage(), roleFunction);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleFunctionPo roleFunctionPo){
        ThrowAssert.isNull(roleFunctionPo, HttpStatus.BAD_REQUEST);

        RoleFunction roleFunction = PoToDoUtils.toAddDO(roleFunctionPo, RoleFunction.class);
        roleFunctionService.save(roleFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleFunctionPo roleFunctionPo){
        ThrowAssert.isNull(roleFunctionPo, HttpStatus.BAD_REQUEST);

        RoleFunction roleFunction = PoToDoUtils.toUpdDO(roleFunctionPo, RoleFunction.class);
        roleFunctionService.updateById(roleFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(roleFunctionService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody RoleFunctionPo roleFunctionPo){
        RoleFunction roleFunction = PoToDoUtils.toAddDO(roleFunctionPo, RoleFunction.class);
        IPage<RoleFunction> page = roleFunctionService.listPage(getPage(), roleFunction);
        return R.SUCCESS(page);
    }
}