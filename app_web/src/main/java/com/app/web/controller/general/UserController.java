package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.UserPo;
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
public class UserController extends BaseRestController<UserService, User> {

    @Autowired
    private UserService userService;

    @Authorization
    @GetMapping
    public R<?> list(){
        User user = new User();
        IPage<User> page = userService.listPage(getPage(), user);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(userService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody UserPo userPo){
        ThrowAssert.isNull(userPo, HttpStatus.BAD_REQUEST);

        User user = PoToDoUtils.toAddDO(userPo, User.class);
        userService.save(user);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody UserPo userPo){
        ThrowAssert.isNull(userPo, HttpStatus.BAD_REQUEST);

        User user = PoToDoUtils.toUpdDO(userPo, User.class);
        userService.updateById(user);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(userService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody UserPo userPo){
        User user = PoToDoUtils.toAddDO(userPo, User.class);
        IPage<User> page = userService.listPage(getPage(), user);
        return R.SUCCESS(page);
    }
}