package com.app.web.config.exce;

import com.app.common.web.result.R;
import com.app.web.constant.BizErrorCode;
import com.app.web.controller.common.MessageSourceHandler;
import com.app.web.controller.exce.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {

    @Autowired
    private MessageSourceHandler messageSourceHandler;

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

//    @ResponseBody
//    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
//    public R validateHandler(HttpServletRequest req, Exception e) throws Exception {
//        BindingResult bindResult = null;
//        if (e instanceof BindException) {
//            bindResult = ((BindException) e).getBindingResult();
//        } else if (e instanceof MethodArgumentNotValidException) {
//            bindResult = ((MethodArgumentNotValidException) e).getBindingResult();
//        }
//        String msg;
//        if (bindResult != null && bindResult.hasErrors()) {
//            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
//        }else {
//            msg = "系统繁忙，请稍后重试...";
//        }
//        return R.ERROR(messageSourceHandler.getMessage(msg));
//    }

    @ExceptionHandler(value = {BizException.class})
    public R validateHandler(HttpServletRequest req, BizException e) throws Exception {
        String property = null;
        String msg = null;
        if (e.getProperties() != null) {
            msg = messageSourceHandler.getMessage(e.getProperties(), e.getArgs(), req);
            if (msg == null) msg = e.getProperties();
        }

        return R.ERROR(msg);
    }


}