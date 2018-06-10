/**
 * 
 */
package com.app.manage.web.controller;

import com.app.manage.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = "/")
	public String index(){
        System.out.println("index1");
        return "index";
	}

	@RequestMapping(value = "/index")
	public String index1(){
		System.out.println("index2");
		return "index1";
	}

}