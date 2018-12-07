package com.app.common.web.result.enums;

import lombok.Getter;

/**
 * 包含 http 标准返回状态
 * 也可以包含自定义错误信息
 */
@Getter
public enum R_CODE {
    ERROR("500", "error"),
    SUCCESS("200", "success");

    public String code;
    public String msg;

    R_CODE(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
