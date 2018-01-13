/**
 * 
 */
package com.app.web.controller;

import com.app.service.service.UserService;
import com.app.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/{id}")
	public String index(@PathVariable long id){
    	return id+"";
	}
}