package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色和功能关系表
 */
@TableName("app_role_element")
@Data
public class RoleElement extends BaseModel {
    private Integer roleId;

    private Integer elementId;
}