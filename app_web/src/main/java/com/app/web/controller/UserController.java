/**
 * 
 */
package com.app.web.controller;

import com.app.web.base.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

	public UserController() {
		System.out.println("--------");
	}

	@RequestMapping("/{id}")
	public String get(@PathVariable long id){
		System.out.println("============");
    	return "get "+id;
	}
}