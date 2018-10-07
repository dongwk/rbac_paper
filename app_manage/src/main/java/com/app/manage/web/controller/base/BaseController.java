/**
 * 
 */
package com.app.manage.web.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseController {
	
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	protected HttpServletRequest request;

	protected HttpServletResponse response;
}