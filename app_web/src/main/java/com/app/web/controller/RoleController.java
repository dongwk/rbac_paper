/**
 * 
 */
package com.app.web.controller;

import com.app.model.model.Role;
import com.app.service.service.RoleService;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.base.BaseRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseRestController<RoleService, Role> {
	
}