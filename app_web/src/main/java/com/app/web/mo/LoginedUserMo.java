package com.app.web.mo;

import lombok.Data;

/**
 * @author dongwk
 * @date 2018/11/9 17:01
 **/
@Data
public class LoginedUserMo {

    private Integer id;
    private String nickname;
    private String username;
    private String token;

    public LoginedUserMo() {
    }

    public LoginedUserMo(Integer id, String nickname, String username, String token) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.token = token;
    }
}
