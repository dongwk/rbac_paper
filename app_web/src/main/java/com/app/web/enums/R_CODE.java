package com.app.web.enums;

public enum R_CODE {
    ERROR("0", "error"),
    SUCCESS("1", "success"),
    SYS_ERROR("-1", "系統异常"),
    SYS_NOTFOUND("-2", "错误路径");

    public String CODE;
    public String MSG;

    R_CODE(String code, String msg) {
        this.CODE = code;
        this.MSG = msg;
    }


}
