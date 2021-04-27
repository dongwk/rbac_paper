package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.AuthorityFunction;
import com.app.service.service.AuthorityFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.AuthorityFunctionPo;
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
public class AuthorityFunctionController extends BaseRestController<AuthorityFunctionService, AuthorityFunction> {

    @Autowired
    private AuthorityFunctionService authorityFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        AuthorityFunction authorityFunction = new AuthorityFunction();
        IPage<AuthorityFunction> page = authorityFunctionService.listPage(getPage(), authorityFunction);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(authorityFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody AuthorityFunctionPo authorityFunctionPo){
        ThrowAssert.isNull(authorityFunctionPo, HttpStatus.BAD_REQUEST);

        AuthorityFunction authorityFunction = PoToDoUtils.toAddDO(authorityFunctionPo, AuthorityFunction.class);
        authorityFunctionService.save(authorityFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody AuthorityFunctionPo authorityFunctionPo){
        ThrowAssert.isNull(authorityFunctionPo, HttpStatus.BAD_REQUEST);

        AuthorityFunction authorityFunction = PoToDoUtils.toUpdDO(authorityFunctionPo, AuthorityFunction.class);
        authorityFunctionService.updateById(authorityFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(authorityFunctionService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody AuthorityFunctionPo authorityFunctionPo){
        AuthorityFunction authorityFunction = PoToDoUtils.toAddDO(authorityFunctionPo, AuthorityFunction.class);
        IPage<AuthorityFunction> page = authorityFunctionService.listPage(getPage(), authorityFunction);
        return R.SUCCESS(page);
    }
}