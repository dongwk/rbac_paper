/**
 * 
 */
package com.app.web.controller;

import com.app.common.util.date.DateUtil;
import com.app.common.web.result.R;
import com.app.mapper.mp.extention.CamlColumnWrapper;
import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.controller.base.BaseRestController;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseRestController<UserService, User> {

    @PostMapping
    public R<Boolean> post(@RequestBody User obj){
        baseService.insert(obj);
        return R.SUCCESS(true);
    }

    @GetMapping(value = "/{id}")
    public R<User> get(@PathVariable String id){
        return R.SUCCESS(baseService.get(id));
    }

    @PutMapping(value = "/{id}")
    public R<Boolean> put(@PathVariable String id, @RequestBody User obj){
        obj.setId(id != null ? Integer.parseInt(id):null);
        return R.SUCCESS(baseService.updateById(obj));
    }

    @PatchMapping(value = "/{id}")
    public R<Boolean> patch(@PathVariable String id, @RequestBody User obj){
        obj.setId(id != null ? Integer.parseInt(id):null);
        obj.setUpdateTime(DateUtil.date());
        return R.SUCCESS(baseService.updateById(obj));
    }

    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable String id){
        return R.SUCCESS(baseService.deleteById(id));
    }
}