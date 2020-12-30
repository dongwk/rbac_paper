package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("app_menu")
@Data
public class Menu extends BaseModel {
    private String name;

    private String code;

    private Integer parentId;

    private String url;

    private String icon;

    private Integer seq;

    @TableField(exist = false)
    private List<Menu> children;
}