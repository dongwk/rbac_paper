/**
 * 
 */
package com.app.web.controller.common;

import com.app.web.common.ErrorPagePath;
import com.app.web.common.R;
import com.app.web.controller.base.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorPageController extends BaseController{


	@GetMapping(value = ErrorPagePath.E404)
    @ResponseBody
	public String E404(){
		LOGGER.info("404 uri:{} ", request.getRequestURI());
		return "404 response";
	}

	@GetMapping(value = ErrorPagePath.E500)
	@ResponseBody
	public R<?> E500(){
		LOGGER.error("500 uri:{} ", request.getRequestURI());
		return R.E500();
	}
}