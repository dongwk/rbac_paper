/**
 * 
 */
package com.app.manage.web.controller.base;

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
		LOGGER.error("Exception:");
		LOGGER.error("url:{}", request.getRequestURL());
		LOGGER.error("param:{}", JsonUtil.toJson(request.getParameterMap()));
		LOGGER.error("exception:{}", ExceptionUtils.getStackTrace(e));
	}
}