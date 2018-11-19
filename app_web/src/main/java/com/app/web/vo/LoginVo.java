package com.app.web.vo;

import lombok.Data;

/**
 * @author dongwk
 * @date 2018/11/16 18:34
 **/
@Data
public class LoginVo {
    private String token;

    public LoginVo(String token) {
        this.token = token;
    }
}
