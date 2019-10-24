/**
 * 
 */
package com.app.web.controller.base;

import com.app.web.controller.common.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
	
    @Autowired
	protected HttpServletRequest request;

    @Autowired
	protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    @Autowired
    private TokenHandler tokenHandler;

}