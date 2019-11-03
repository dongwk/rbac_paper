package com.app;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class LoginMo {
    @NotNull(message = "{login.username.isempty}")
    private String username;
    private String password;
    private String client;
    private String referer;
    private long expiration;
    private String token;
    private String serverURL;
}
