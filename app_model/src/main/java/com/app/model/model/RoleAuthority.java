package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 20191121 暂时废弃吧不知道怎么用
 */
@Deprecated
@TableName("app_role_authority")
@Data
public class RoleAuthority extends BaseModel {
    private Integer roleId;

    private Integer authorityId;

}