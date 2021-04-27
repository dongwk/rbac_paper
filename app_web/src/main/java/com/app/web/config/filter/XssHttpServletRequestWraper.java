package com.app.web.config.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWraper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWraper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getParameter(String name) {
        return HtmlUtils.htmlUnescape(super.getParameter(name));
    }
    
    @Override
    public String getHeader(String name) {
        return HtmlUtils.htmlUnescape(super.getParameter(name));
    }
    
    @Override
    public String[] getParameterValues(String name) {
        if(StringUtils.isNotBlank(name)){
            String[] values = super.getParameterValues(name);
            if(values != null && values.length > 0){
                String[] newValues = new String[values.length];
                
                for(int i =0; i< values.length; i++){
                    newValues[i] = HtmlUtils.htmlUnescape(values[i]);
                }
                return newValues;
            }
        }
        return null;
    }
}