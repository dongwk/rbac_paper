package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("app_user_group_user")
@Data
public class UserGroupUser extends BaseModel {
    private Integer userGroupId;

    private Integer userId;

}