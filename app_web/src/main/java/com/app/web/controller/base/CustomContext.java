package com.app.web.controller.base;

import com.app.web.utils.RequestUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class CustomContext {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    /**
     * 分页请求头处理
     * @return
     */
    protected Page getPage(){
        return RequestUtils.getPage(request);
    }
//
//    protected HttpServletResponse getResponse(){
//        HttpServletResponse resp = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
//        return resp;
//    }
//
//    protected HttpSession getSession(){
//        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
//        return request.getSession();
//    }
}
