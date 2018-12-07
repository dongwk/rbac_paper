package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_user_grop")
@Data
public class UserGroup extends BaseModel {
    private String name;

    private Integer parentId;

}