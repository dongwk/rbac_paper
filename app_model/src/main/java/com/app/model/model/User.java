package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户表
 */
@TableName("t_user")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {
    // 昵称
    private String nickname;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 加密盐
    private String salt;
}