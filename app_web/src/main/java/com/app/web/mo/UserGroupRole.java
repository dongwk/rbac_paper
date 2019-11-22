package com.app.web.mo;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_user_group_role")
@Data
public class UserGroupRole extends BaseModel {
    private Integer userGroupId;

    private Integer roleId;

}