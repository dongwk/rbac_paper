package com.app.web.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author dongwk
 * @date 2018/11/16 18:34
 **/
@Data
@Builder
@Accessors(chain = true)
public class LoginVo {
    private String token;
    private long expires;
    private boolean ssl;
}
