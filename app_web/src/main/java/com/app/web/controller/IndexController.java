/**
 * 
 */
package com.app.web.controller;

import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController extends BaseController{


	@GetMapping(value = "/")
    @ResponseBody
	public String get(){
		return "index11.html";
	}


	@GetMapping(value = "/404")
	@ResponseBody
	public String notFound(){
		return "404";
	}
}