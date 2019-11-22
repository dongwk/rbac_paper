package com.app.web.config.interceptor;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyLocaleChangeInterceptor extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
        throws ServletException {

        String newLocale = request.getParameter(getParamName());
        if (newLocale != null) {
            return super.preHandle(request, response, handler);
        }
        // Custom logic
        else {
            newLocale = request.getHeader("Locale");
            if (newLocale != null) {          
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
                }

                try {
                    localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
                } catch (IllegalArgumentException ex) {
                    if (isIgnoreInvalidLocale()) {
                        this.logger.error("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
                    } else {
                        throw ex;
                    }
                }
            }
        }

        return true;
    }
}