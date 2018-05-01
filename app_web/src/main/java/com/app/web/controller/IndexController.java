/**
 * 
 */
package com.app.web.controller;

import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class IndexController extends BaseController{

	@GetMapping(value = "/")
	public String get(){
		System.out.println("-----111222");
		return "index";
	}
}