/**
 * 
 */
package com.app.web.base;

import com.app.util.JsonUtil;
import com.app.web.common.R;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController extends BaseController{

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception e) {
		LOGGER.error("Exception:");
		LOGGER.error("url:{}", request.getRequestURL());
		LOGGER.error("param:{}", JsonUtil.toJson(request.getParameterMap()));
		LOGGER.error("exception:{}", ExceptionUtils.getStackTrace(e));
//		return new ModelAndView("error");
		return new ModelAndView(new MappingJackson2JsonView()).addObject(R.SYS_ERROR());
	}


	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
		LOGGER.error("NoHandlerFoundException:");
		LOGGER.error("url:{}", request.getRequestURL());
		LOGGER.error("param:{}", JsonUtil.toJson(request.getParameterMap()));
		LOGGER.error("exception:{}", ExceptionUtils.getStackTrace(e));
		return new ModelAndView("404");
	}

}