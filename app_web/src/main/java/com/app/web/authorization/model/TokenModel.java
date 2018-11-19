package com.app.web.authorization.model;

import lombok.Data;

/**
 * @author dongwk
 * @date 2018/11/9 16:09
 **/
@Data
public class TokenModel {
    //用户id
    private long userId;

    //随机生成的uuid
    private String token;

    public TokenModel(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
