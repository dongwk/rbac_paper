package com.app.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 公共业务异常
 * @author dongwk
 * @date 2020-09-10 10:38
 **/
@NoArgsConstructor
public class BizException extends RuntimeException {

    @Getter
    private String properties;
    @Getter
    private Object[] args;

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
