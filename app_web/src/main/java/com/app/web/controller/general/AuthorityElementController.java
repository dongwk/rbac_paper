package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.AuthorityElement;
import com.app.service.service.AuthorityElementService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.AuthorityElementPo;
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
public class AuthorityElementController extends BaseRestController<AuthorityElementService, AuthorityElement> {

    @Autowired
    private AuthorityElementService authorityElementService;

    @Authorization
    @GetMapping
    public R<?> list(){
        AuthorityElement authorityElement = new AuthorityElement();
        IPage<AuthorityElement> page = authorityElementService.listPage(getPage(), authorityElement);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(authorityElementService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody AuthorityElementPo authorityElementPo){
        ThrowAssert.isNull(authorityElementPo, HttpStatus.BAD_REQUEST);

        AuthorityElement authorityElement = PoToDoUtils.toAddDO(authorityElementPo, AuthorityElement.class);
        authorityElementService.save(authorityElement);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody AuthorityElementPo authorityElementPo){
        ThrowAssert.isNull(authorityElementPo, HttpStatus.BAD_REQUEST);

        AuthorityElement authorityElement = PoToDoUtils.toUpdDO(authorityElementPo, AuthorityElement.class);
        authorityElementService.updateById(authorityElement);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(authorityElementService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody AuthorityElementPo authorityElementPo){
        AuthorityElement authorityElement = PoToDoUtils.toAddDO(authorityElementPo, AuthorityElement.class);
        IPage<AuthorityElement> page = authorityElementService.listPage(getPage(), authorityElement);
        return R.SUCCESS(page);
    }
}