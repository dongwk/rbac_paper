/**
 * 
 */
package com.app.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.User;
import com.app.service.UserService;
import com.app.web.common.ResultWeb;

@Controller
@RequestMapping("/user")
public class UserController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());  
    
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(){
		System.out.println("0000");
    	return "success";
	}
	
    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    @ResponseBody
	public ResultWeb<User> get(@PathVariable Long uid){
    	logger.info("get");
    	User user = userService.get(uid);
    	return ResultWeb.SUCCESS(user);
	}
    
    public static void main(String[] args) {
		System.out.println(f(4));
	}
    
    static int f(int n){
		int sum = 0;
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		else if (n == 2) 
			return 1;
	    else sum = f(n - 1) + f(n - 2);
		return sum;
    }
}