/**
 * 
 */
package com.app.manage.web.controller.common;

import com.app.common.web.common.R;
import com.app.common.web.constant.ErrorPagePath;
import com.app.manage.web.controller.base.BaseController;
import com.app.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorPageController extends BaseController {


	@GetMapping(value = ErrorPagePath.E404)
    @ResponseBody
	public R<?> E404(){
		return R.E404();
	}

	@GetMapping(value = ErrorPagePath.E500)
	@ResponseBody
	public R<?> E500(){
		return R.E500();
	}
}