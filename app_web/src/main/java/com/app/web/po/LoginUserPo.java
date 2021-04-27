package com.app.web.po;

import lombok.Data;

import java.util.Set;

/**
 * @author dongwk
 * @date 2018/11/9 17:01
 **/
@Data
public class LoginUserPo {

    private Integer id;
    private String nickname;
    private String username;
    private String token;
    private String avatarImgUrl;
    private Set<String> funs; // 权限

    public LoginUserPo() {
    }

    public LoginUserPo(Integer id, String nickname, String username, String token, Set<String> funs) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.token = token;
        this.funs = funs;
    }
}
