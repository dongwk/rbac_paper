package com.app.core.common;

import com.app.core.enums.MessagesPropertiesEnum;
import com.app.core.exception.BizException;
import com.app.core.exception.HttpBizException;
import org.springframework.http.HttpStatus;

public class ThrowBiz {

    public static void throwExce(String msg) {
        throw new BizException(msg);
    }

    public static void throwExce(String msg, Object... args) {
        throw new BizException(msg, args);
    }

    public static void throwExce(MessagesPropertiesEnum propertiesEnum) {
        throw new BizException(propertiesEnum);
    }

    public static void throwExce(MessagesPropertiesEnum propertiesEnum, Object... args) {
        throw new BizException(propertiesEnum, args);
    }

    public static void throwExce(HttpStatus httpStatus) {
        throw new HttpBizException(httpStatus);
    }

    public static void throwExce(HttpStatus httpStatus, String msg) {
        throw new HttpBizException(httpStatus, msg);
    }

    public static void throwExce(HttpStatus httpStatus, String msg, Object... args) {
        throw new HttpBizException(httpStatus, msg, args);
    }

    public static void throwExce(HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        throw new HttpBizException(httpStatus, propertiesEnum);
    }

    public static void throwExce(HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        throw new HttpBizException(httpStatus, propertiesEnum, args);
    }
}
