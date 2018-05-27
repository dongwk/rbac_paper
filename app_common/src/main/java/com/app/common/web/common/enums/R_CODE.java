package com.app.common.web.common.enums;

public enum R_CODE {
    ERROR("0", "error"),
    SUCCESS("1", "success"),
    SYS_ERROR("-1", "系統异常"),
    NOT_FOUND("-2", "404");

    public String CODE;
    public String MSG;

    R_CODE(String code, String msg) {
        this.CODE = code;
        this.MSG = msg;
    }


}
