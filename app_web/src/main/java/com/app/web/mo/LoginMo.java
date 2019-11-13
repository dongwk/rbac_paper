package com.app.web.mo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class LoginMo {
    private String username;
    private String password;
    private String client;
    private String referer;
    private long expiration;
    private String token;
    private String serverURL;
}
