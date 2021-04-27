package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色和功能关系表po
 */
@Data
public class RoleElementPo extends BaseModel {
    private Integer roleId;

    private Integer elementId;
}
