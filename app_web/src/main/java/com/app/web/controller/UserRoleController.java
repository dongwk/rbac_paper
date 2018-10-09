/**
 * 
 */
package com.app.web.controller;

import com.app.service.service.UserRoleService;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.base.ServiceController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-role")
public class UserRoleController extends ServiceController<UserRoleService> {
	
}