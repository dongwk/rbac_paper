package com.app.web.controller.exce;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BizException extends RuntimeException {

    @Getter
    private String properties;
    @Getter
    private Object[] args;
    @Getter
    private HttpStatus httpStatus;

    public BizException() {

    }

    public BizException(String properties) {
        super(properties);
        this.properties = properties;
    }

    public BizException(String properties, Object... args) {
        super(properties);
        this.properties = properties;
        this.args = args;
    }

    public BizException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BizException(HttpStatus httpStatus, String properties) {
        super(properties);
        this.httpStatus = httpStatus;
        this.properties = properties;
    }

}
