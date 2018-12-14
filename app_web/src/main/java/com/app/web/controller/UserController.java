/**
 * 
 */
package com.app.web.controller;

import com.app.common.util.date.DateUtil;
import com.app.common.web.result.R;
import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.controller.base.BaseSimpleController;
import com.app.web.vo.PageVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseSimpleController<UserService, User> {

    @GetMapping
    public R<?> get(){
        System.out.println("=======11");
        Wrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.setSqlSelect("username");
        Page<User> page = baseSimpleService.selectPage(new Page(getDefPage(), getDefPerPage()), wrapper);
        return R.SUCCESS(page.getRecords());
    }

    @PostMapping
    public R<Boolean> post(@RequestBody User obj){
        if ("1".equals(obj.getUsername())) throw new RuntimeException("id not 1");
        System.out.println("=====");
        baseSimpleService.insert(obj);
        return R.SUCCESS(true);
    }

    @GetMapping(value = "/{id}")
    public R<User> get(@PathVariable String id){
        if ("1".equals(id)) throw new RuntimeException("id not 1");
        System.out.println("=====1");
        return R.SUCCESS(baseSimpleService.get(id));
    }

    @PutMapping(value = "/{id}")
    public R<Boolean> put(@PathVariable String id, @RequestBody User obj){
        System.out.println("=====2");
        obj.setId(id != null ? Integer.parseInt(id):null);
        obj.setUpdateTime(DateUtil.date());
        return R.SUCCESS(baseSimpleService.updateById(obj));
    }

    @PatchMapping(value = "/{id}")
    public R<Boolean> patch(@PathVariable String id, @RequestBody User obj){
        obj.setId(id != null ? Integer.parseInt(id):null);
        obj.setUpdateTime(DateUtil.date());
        return R.SUCCESS(baseSimpleService.updateById(obj));
    }

    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable String id){
        return R.SUCCESS(baseSimpleService.deleteById(id));
    }
}