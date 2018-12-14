package com.app.web.controller.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "forward:/500";

    private ContentCachingRequestWrapper getUnderlyingCachingRequest(ServletRequest request) {
        if (ContentCachingRequestWrapper.class.isAssignableFrom(request.getClass())) {
            return (ContentCachingRequestWrapper) request;
        }
        if (request instanceof ServletRequestWrapper) {
            return getUnderlyingCachingRequest(((ServletRequestWrapper)request).getRequest());
        }
        return null;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        System.out.println("====11222");
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        ContentCachingRequestWrapper req1 = getUnderlyingCachingRequest(req);
        log.error("GlobalExceptionHandler Url:{}, Params:{}, RequestBody:{}, Exception:{}",
                req.getRequestURL(), req.getParameterMap(), req1 != null ? new String(req1.getContentAsByteArray(), "UTF-8"):null, ExceptionUtils.getStackTrace(e));

        return mav;
    }

}