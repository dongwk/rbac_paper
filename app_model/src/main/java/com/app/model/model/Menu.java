package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_menu")
@Data
public class Menu extends BaseModel {
    private String name;

    private Integer parentId;

}