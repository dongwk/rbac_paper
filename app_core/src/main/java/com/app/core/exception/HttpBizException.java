package com.app.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 公共 HTTP 业务异常
 * @author dongwk
 * @date 2020-09-10 10:38
 **/
@NoArgsConstructor
public class HttpBizException extends BizException {

    @Getter
    private HttpStatus httpStatus;


    public HttpBizException(String properties) {
        super(properties);
    }

    public HttpBizException(String properties, Object... args) {
        super(properties, args);
    }

    public HttpBizException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpBizException(HttpStatus httpStatus, String properties) {
        super(properties);
        this.httpStatus = httpStatus;
    }

    public HttpBizException(HttpStatus httpStatus, String properties, Object... args) {
        super(properties, args);
        this.httpStatus = httpStatus;
    }

}
