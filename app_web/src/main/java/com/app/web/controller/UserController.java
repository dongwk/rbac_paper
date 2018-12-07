/**
 * 
 */
package com.app.web.controller;

import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.controller.base.BaseSimpleController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseSimpleController<UserService, User> {


}