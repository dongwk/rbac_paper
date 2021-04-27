package com.app.core.exception;

import com.app.core.enums.MessagesPropertiesEnum;
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



    public HttpBizException(String msg) {
        super(msg);
    }

    public HttpBizException(String msg, Object... args) {
        super(msg, args);
    }

    public HttpBizException(MessagesPropertiesEnum propertiesEnum) {
        super(propertiesEnum);
    }

    public HttpBizException(MessagesPropertiesEnum propertiesEnum, Object... args) {
        super(propertiesEnum, args);
    }

    public HttpBizException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpBizException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpBizException(HttpStatus httpStatus, String msg, Object... args) {
        super(msg, args);
        this.httpStatus = httpStatus;
    }

    public HttpBizException(HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        super(propertiesEnum);
        this.httpStatus = httpStatus;
    }

    public HttpBizException(HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        super(propertiesEnum, args);
        this.httpStatus = httpStatus;
    }

}
