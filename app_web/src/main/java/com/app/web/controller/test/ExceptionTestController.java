/**
 * 
 */
package com.app.web.controller.test;

import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.base.BaseController;
import com.app.web.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/exception/test")
public class ExceptionTestController extends BaseController{


	@GetMapping(value = "/500")
	public R<?> e500(){
		throw new RuntimeException("错误结果");
	}

	@GetMapping(value = "/404")
	public R<?> e404(){
		throw new RuntimeException("找不到页面");
	}
}