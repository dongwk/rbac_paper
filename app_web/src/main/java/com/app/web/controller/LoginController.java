/**
 *
 */
package com.app.web.controller;

import com.app.web.controller.base.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {


	@GetMapping
	public String get(){
		return "login";
	}


}