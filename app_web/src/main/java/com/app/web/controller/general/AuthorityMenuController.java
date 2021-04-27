package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.AuthorityMenu;
import com.app.service.service.AuthorityMenuService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.AuthorityMenuPo;
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
public class AuthorityMenuController extends BaseRestController<AuthorityMenuService, AuthorityMenu> {

    @Autowired
    private AuthorityMenuService authorityMenuService;

    @Authorization
    @GetMapping
    public R<?> list(){
        AuthorityMenu authorityMenu = new AuthorityMenu();
        IPage<AuthorityMenu> page = authorityMenuService.listPage(getPage(), authorityMenu);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(authorityMenuService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody AuthorityMenuPo authorityMenuPo){
        ThrowAssert.isNull(authorityMenuPo, HttpStatus.BAD_REQUEST);

        AuthorityMenu authorityMenu = PoToDoUtils.toAddDO(authorityMenuPo, AuthorityMenu.class);
        authorityMenuService.save(authorityMenu);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody AuthorityMenuPo authorityMenuPo){
        ThrowAssert.isNull(authorityMenuPo, HttpStatus.BAD_REQUEST);

        AuthorityMenu authorityMenu = PoToDoUtils.toUpdDO(authorityMenuPo, AuthorityMenu.class);
        authorityMenuService.updateById(authorityMenu);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(authorityMenuService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody AuthorityMenuPo authorityMenuPo){
        AuthorityMenu authorityMenu = PoToDoUtils.toAddDO(authorityMenuPo, AuthorityMenu.class);
        IPage<AuthorityMenu> page = authorityMenuService.listPage(getPage(), authorityMenu);
        return R.SUCCESS(page);
    }
}