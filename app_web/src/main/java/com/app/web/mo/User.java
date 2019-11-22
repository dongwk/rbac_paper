package com.app.web.mo;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_user")
@Data
public class User extends BaseModel {
    private String nickname;

    private String username;

    private String password;

    private String salt;
}