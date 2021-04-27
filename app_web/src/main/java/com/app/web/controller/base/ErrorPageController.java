/**
 * 
 */
package com.app.web.controller.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ErrorPageController extends BaseController{


//	@RequestMapping(value = ErrorPagePath.E400)
//	public R<?> E400(){
//		log.info("400 uri:{} ", request.getRequestURI());
//		return R.MODEL(400);
//	}
//
//	@RequestMapping(value = ErrorPagePath.E404)
//	public R<?> E404(){
//		log.info("404 uri:{} ", request.getRequestURI());
//		return R.MODEL(404);
//	}
//
//	@RequestMapping(value = ErrorPagePath.E405)
//	public R<?> E405(){
//		log.info("405 uri:{} ", request.getRequestURI());
//		return R.MODEL(405);
//	}
//
//	@RequestMapping(value = ErrorPagePath.E500)
//	@ResponseBody
//	public R<?> E500(){
//		log.error("500 uri:{} ", request.getRequestURI());
//		return R.MODEL(500);
//	}

}