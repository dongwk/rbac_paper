/**
 * 
 */
package com.app.web.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
	
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

	protected HttpServletRequest request;

    @Autowired
	protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

}