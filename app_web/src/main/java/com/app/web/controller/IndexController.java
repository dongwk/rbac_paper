/**
 * 
 */
package com.app.web.controller;

import com.app.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController extends BaseController{


	@GetMapping(value = "/")
    @ResponseBody
	public String get(){
		return "index";
	}
}