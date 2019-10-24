package com.app.web.vo;

import lombok.Data;

/**
 * @author dongwk
 * @date 2018/11/9 17:01
 **/
@Data
public class UserVo {
    public UserVo() {
    }

    public UserVo(String name) {
        this.name = name;
    }

    private String name;
}
