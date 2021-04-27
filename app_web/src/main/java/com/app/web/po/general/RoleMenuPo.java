package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 角色表po和菜单表po关联表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuPo extends BaseModel {
    // 角色表id
    private Integer roleId;

    // 菜单表id
    private Integer menuId;
}
