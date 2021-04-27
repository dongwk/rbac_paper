package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 角色表和菜单表关联表
 */
@TableName("t_role_menu")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu extends BaseModel {
    // 角色表id
    private Integer roleId;

    // 菜单表id
    private Integer menuId;
}