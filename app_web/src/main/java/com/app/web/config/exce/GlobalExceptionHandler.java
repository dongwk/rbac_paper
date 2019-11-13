package com.app.web.config.exce;

import com.app.common.util.date.DateUtil;
import com.app.common.web.result.R;
import com.app.web.constant.BizErrorCode;
import com.app.web.controller.common.MessageSourceHandler;
import com.app.web.controller.exce.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;
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
    public ModelAndView defaultErroRrHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", DateUtil.timestamp());
        mav.addObject("exception", e.getMessage());
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        ContentCachingRequestWrapper req1 = getUnderlyingCachingRequest(req);
        log.error("url:{}", req.getRequestURL());
        log.error("params:{}",req.getParameterMap());
        log.error("requestBody:{}", (req1 != null && req1.getContentAsByteArray() != null) ? new String(req1.getContentAsByteArray(), "UTF-8"):null);
        log.error("exception:\n{}", ExceptionUtils.getStackTrace(e));

        //TODO 后期需要改成“系统错误”，现在是错误详细信息方便查问题
        return mav;
    }

    @ResponseBody
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public R validateHandler(HttpServletRequest req, Exception e) throws Exception {
        BindingResult bindResult = null;
        if (e instanceof BindException) {
            bindResult = ((BindException) e).getBindingResult();
        } else if (e instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) e).getBindingResult();
        }
        e.printStackTrace();
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            return R.BAD_REQUEST(msg);
        }else {
            msg = "系统繁忙，请稍后重试...";
            return R.ERROR(msg);
        }
    }

    @ExceptionHandler(value = {BizException.class})
    public R validateHandler(HttpServletRequest req, BizException e) throws Exception {
        String property = null;
        String msg = null;
        HttpStatus httpStatus = e.getHttpStatus();

        if (e.getProperties() != null) {
            msg = messageSourceHandler.getMessage(e.getProperties(), e.getArgs(), req);
            if (msg == null) msg = e.getProperties();
        }

        return R.ERROR(msg);
    }


}