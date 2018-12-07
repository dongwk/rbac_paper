package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_user_role")
@Data
public class UserRole extends BaseModel {
    private Integer userId;

    private Integer roleId;

}