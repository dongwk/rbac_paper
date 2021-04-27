package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户组与角色关联表
 */
@TableName("t_user_group_role")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupRole extends BaseModel {
    // 用户组id
    private Integer userGroupId;

    // 角色id
    private Integer roleId;
}