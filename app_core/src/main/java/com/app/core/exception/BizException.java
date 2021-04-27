package com.app.core.exception;

import com.app.core.enums.MessagesPropertiesEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 公共业务异常
 * @author dongwk
 * @date 2020-09-10 10:38
 **/
@NoArgsConstructor
@Data
public class BizException extends RuntimeException {

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 国际化信息配置
     */
    private MessagesPropertiesEnum propertiesEnum;

    /**
     * 错误信息参数，msg 和 propertiesEnum 都可用
     */
    private Object[] args;

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String msg, Object... args) {
        super(msg+" "+args);
        this.msg = msg;
        this.args = args;
    }

    public BizException(MessagesPropertiesEnum propertiesEnum) {
        super(propertiesEnum.name());
        this.propertiesEnum = propertiesEnum;
    }

    public BizException(MessagesPropertiesEnum propertiesEnum, Object... args) {
        super(propertiesEnum.name()+" "+args);
        this.propertiesEnum = propertiesEnum;
        this.args = args;
    }
}
