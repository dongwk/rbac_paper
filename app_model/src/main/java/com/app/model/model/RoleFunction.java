package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 角色和功能关系表
 */
@TableName("app_role_function")
@Data
public class RoleFunction extends BaseModel {
    private Integer roleId;

    private Integer functionId;
}