/**
 * 
 */
package com.app.web.controller;

import com.app.web.base.BaseController;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@GetMapping("/{id}")
	public String get(@PathVariable long id){
    	return "get "+id;
	}
}