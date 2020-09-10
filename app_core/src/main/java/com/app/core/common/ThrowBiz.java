package com.app.core.common;

import com.app.core.exception.BizException;
import com.app.core.exception.HttpBizException;
import org.springframework.http.HttpStatus;

public class ThrowBiz {

    public static void throwExce(String properties) {
        throw new BizException(properties);
    }

    public static void throwExce(String properties, Object... args) {
        throw new BizException(properties, args);
    }

    public static void throwExce(HttpStatus httpStatus) {
        throw new HttpBizException(httpStatus);
    }

    public static void throwExce(HttpStatus httpStatus, String properties) {
        throw new HttpBizException(httpStatus, properties);
    }

    public static void throwExce(HttpStatus httpStatus, String properties, Object... args) {
        throw new HttpBizException(httpStatus, properties, args);
    }
}
