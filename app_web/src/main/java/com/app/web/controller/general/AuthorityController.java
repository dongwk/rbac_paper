package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Authority;
import com.app.service.service.AuthorityService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.AuthorityPo;
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
public class AuthorityController extends BaseRestController<AuthorityService, Authority> {

    @Autowired
    private AuthorityService authorityService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Authority authority = new Authority();
        IPage<Authority> page = authorityService.listPage(getPage(), authority);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(authorityService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody AuthorityPo authorityPo){
        ThrowAssert.isNull(authorityPo, HttpStatus.BAD_REQUEST);

        Authority authority = PoToDoUtils.toAddDO(authorityPo, Authority.class);
        authorityService.save(authority);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody AuthorityPo authorityPo){
        ThrowAssert.isNull(authorityPo, HttpStatus.BAD_REQUEST);

        Authority authority = PoToDoUtils.toUpdDO(authorityPo, Authority.class);
        authorityService.updateById(authority);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(authorityService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody AuthorityPo authorityPo){
        Authority authority = PoToDoUtils.toAddDO(authorityPo, Authority.class);
        IPage<Authority> page = authorityService.listPage(getPage(), authority);
        return R.SUCCESS(page);
    }
}