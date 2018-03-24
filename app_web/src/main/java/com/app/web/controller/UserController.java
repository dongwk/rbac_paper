/**
 * 
 */
package com.app.web.controller;

import com.app.web.base.BaseController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

	@RequestMapping
	public String index() {
		return "hello world";
	}

	@GetMapping(value = "/{id}")
	public String get(@PathVariable long id){
		System.out.println("============");
    	return "get "+id;
	}
}