package com.app.web.controller.exce;

import lombok.Getter;

public class BizException extends RuntimeException {

    @Getter
    private String properties;
    @Getter
    private Object[] args;

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
}
