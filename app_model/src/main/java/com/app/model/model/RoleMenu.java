package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 角色和菜单关系表
 */
@TableName("app_role_menu")
@Data
public class RoleMenu extends BaseModel {
    private Integer roleId;

    private Integer menuId;
}