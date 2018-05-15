/**
 * 
 */
package com.app.manage.controller.common;

import com.app.common.web.common.R;
import com.app.common.web.constant.ErrorPagePath;
import com.app.manage.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorPageController extends BaseController{


	@GetMapping(value = ErrorPagePath.E404)
    @ResponseBody
	public String E404(){
		return "404 response";
	}

	@GetMapping(value = ErrorPagePath.E500)
	@ResponseBody
	public R<?> E500(){
		return R.E500();
	}
}