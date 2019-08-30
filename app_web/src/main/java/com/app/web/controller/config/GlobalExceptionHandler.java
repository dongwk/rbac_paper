package com.app.web.controller.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
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
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("exception", e.getMessage());
        ContentCachingRequestWrapper req1 = getUnderlyingCachingRequest(req);
        log.error("GlobalExceptionHandler url:{}, params:{}, requestBody:{}, exception:{}",
                req.getRequestURL(), req.getParameterMap(), req1 != null ? new String(req1.getContentAsByteArray(), "UTF-8"):null, ExceptionUtils.getStackTrace(e));

        return mav;
    }

}