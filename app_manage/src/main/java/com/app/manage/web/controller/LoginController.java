/**
 *
 */
package com.app.manage.web.controller;

import com.app.manage.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@GetMapping
	public String get(){
		LOGGER.debug("login");
		return "login";
	}
}