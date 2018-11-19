/**
 * 
 */
package com.app.web.controller;

import com.app.common.web.common.R;
import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

	@GetMapping
	public R<?> index(){
		return R.SUCCESS(new Date());
	}

	@GetMapping(value = "/{id}")
	public R<?> get(@PathVariable long id){
        User obj = null;
    	return R.SUCCESS(obj);
	}

}