package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户组与用户关联表
 */
@TableName("t_user_group_user")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupUser extends BaseModel {
    // 用户组id
    private Integer userGroupId;

    // 用户id
    private Integer userId;
}