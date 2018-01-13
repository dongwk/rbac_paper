/**
 * 
 */
package com.app.web.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


public class BaseController {
	
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}
}