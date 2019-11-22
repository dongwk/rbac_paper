package com.app.web.mo;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_role_authority")
@Data
public class RoleAuthority extends BaseModel {
    private Integer roleId;

    private Integer authorityId;

}