package com.app.web.po;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginPo {
    private String username;
    private String password;
    private String client;
    private String referer;
    private long expiration;
    private String token;
    private String serverURL;
}
