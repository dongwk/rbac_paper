package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@TableName("app_user")
@Data
public class User extends BaseModel {
    private String nickname;

    private String username;

    private String password;

    private String salt;
}