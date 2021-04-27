package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 业务用户表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserPo extends BaseModel {
    // 名字
    private String name;

    // 昵称
    private String nickname;

    // 头像img_id
    private Integer avatarImgId;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 加密盐
    private String salt;

    // 联系方式
    private String mobilePhone;

    // 邮箱
    private String email;

    // 业务用户类型 1 普通用户 2 管理员
    private Integer businessUserType;
}
