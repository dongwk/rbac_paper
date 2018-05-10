/**
 * 
 */
package com.app.web.controller;

import com.app.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{


	@GetMapping(value = "/")
	public String get(){
		return "login.html";
	}


}