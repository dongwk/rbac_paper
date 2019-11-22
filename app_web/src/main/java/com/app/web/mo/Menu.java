package com.app.web.mo;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_menu")
@Data
public class Menu extends BaseModel {
    private String name;

    private String url;

    private Integer parentId;

}