package com.app.web.controller.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@Component
public class MessageSourceHandler {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        MessageSourceHandler.messageSource = messageSource;
    }

    public static String getMessage(String messageKey) {
        try {
            String message = messageSource.getMessage(messageKey, null, Locale.getDefault());
            return message;
        } catch (NoSuchMessageException e){
            log.error("MessageKey {}, NoSuchMessageException {}", messageKey, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static String getMessage(String messageKey, Locale locale) {
        try {
            String message = messageSource.getMessage(messageKey, null, locale);
            return message;
        } catch (NoSuchMessageException e){
            log.error("MessageKey {}, NoSuchMessageException {}", messageKey, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static String getMessage(String messageKey, Object[] args, Locale locale) {
        try {
            String message = messageSource.getMessage(messageKey, args, locale);
            return message;
        } catch (NoSuchMessageException e){
            log.warn("MessageKey {}, NoSuchMessageException {}", messageKey, e.getMessage());
            return null;
        }
    }

    public static String getMessage(String messageKey, HttpServletRequest request) {
        return getMessage(messageKey, RequestContextUtils.getLocale(request));
    }

    public static String getMessage(String messageKey, Object[] args, HttpServletRequest request) {
        return getMessage(messageKey, args, RequestContextUtils.getLocale(request));
    }
}