/**
 * 
 */
package com.app.web.controller;

import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.base.BaseController;
import com.app.web.common.R;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

	@RequestMapping
	public String index() {
		return "hello world";
	}

	@GetMapping(value = "/{id}")
	public R<?> get(@PathVariable long id){
        List<User> list = userService.selectList(null);
        System.out.println(list);
        userService.get(id);
    	return R.SUCCESS(new Date());
	}

}