/**
 * 
 */
package com.app.web.controller.base;

import com.app.util.JsonUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController extends BaseController{

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "ExceptionController Exception 500")
	public void handleError(Exception e) {
		log.error("Exception:");
//		log.error("url:{}", request.getRequestURL());
//		log.error("param:{}", JsonUtil.toJson(request.getParameterMap()));
		log.error("exception:{}", ExceptionUtils.getStackTrace(e));
	}
}