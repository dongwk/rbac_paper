/**
 * 
 */
package com.app.web.controller;

import com.app.common.util.date.DateUtil;
import com.app.common.web.result.R;
import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.controller.base.BaseRestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseRestController<UserService, User> {

    @PostMapping
    public R<Boolean> post(@RequestBody User obj){
        baseService.save(obj);
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
        obj.setUpdateDate(DateUtil.date());
        return R.SUCCESS(baseService.updateById(obj));
    }

    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable String id){
        return R.SUCCESS(baseService.removeById(id));
    }
}