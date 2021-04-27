package com.app.web.config.exce;

import com.app.common.util.date.DateUtils;
import com.app.web.common.R;
import com.app.core.exception.HttpBizException;
import com.app.web.controller.common.MessageSourceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ModelAndView defaultErroRrHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) throws Exception {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", DateUtils.timestamp());
        mav.addObject("msg", String.format("该方法支持下面请求方式 %s", e.getSupportedHttpMethods().toString()));
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        ContentCachingRequestWrapper req1 = getUnderlyingCachingRequest(req);
        log.warn("url:{} params:{} requestBody:{} ", req.getRequestURL(), req.getParameterMap(), (req1 != null && req1.getContentAsByteArray() != null) ? new String(req1.getContentAsByteArray(), "UTF-8"):null);
        return mav;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErroRrHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", DateUtils.timestamp());
        mav.addObject("msg", "系统异常，请稍后重试。");
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        ContentCachingRequestWrapper req1 = getUnderlyingCachingRequest(req);
        log.error("url:{} params:{} requestBody:{} ", req.getRequestURL(), req.getParameterMap(), (req1 != null && req1.getContentAsByteArray() != null) ? new String(req1.getContentAsByteArray(), "UTF-8"):null);
        log.error("exception", e);
        return mav;
    }


    /**
     * 统一验证框架异常处理
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
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
            return R.FAIL(msg);
        }else {
            msg = "系统繁忙，请稍后重试。";
            return R.FAIL(msg);
        }
    }

    /**
     * 自定义业务异常处理
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = {HttpBizException.class})
    public ResponseEntity validateHandler(HttpServletRequest req, HttpBizException e) throws Exception {
        String msg = null;
        HttpStatus httpStatus = HttpStatus.OK;

        if (e.getMsg() != null) {
            msg = e.getMsg();
        } else if (e.getPropertiesEnum() != null) {
            msg = MessageSourceHandler.getMessage(e.getPropertiesEnum().getKey(), e.getArgs(), req);
            if (msg == null) msg = e.getPropertiesEnum().name();
        }

        if (e.getHttpStatus() != null) {
            httpStatus = e.getHttpStatus();
        }

        return new ResponseEntity(R.FAIL(msg), httpStatus);
    }


}