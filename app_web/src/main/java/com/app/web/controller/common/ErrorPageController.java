/**
 * 
 */
package com.app.web.controller.common;

import com.app.common.web.result.R;
import com.app.web.common.ErrorPagePath;
import com.app.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class ErrorPageController extends BaseController{

	@GetMapping(value = ErrorPagePath.E404)
    @ResponseBody
	public R<?> E404(){
		log.info("404 uri:{} ", request.getRequestURI());
		return R.MODEL("404");
	}

	@GetMapping(value = ErrorPagePath.E500)
	@ResponseBody
	public R<?> E500(){
		log.error("500 uri:{} ", request.getRequestURI());
		return R.MODEL("500");
	}
}