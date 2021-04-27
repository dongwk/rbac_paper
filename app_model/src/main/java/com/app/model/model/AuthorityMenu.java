package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 权限与菜单关联表
 */
@TableName("t_authority_menu")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityMenu extends BaseModel {
    // 权限id
    private Integer authorityId;

    // 菜单id
    private Integer menuId;
}