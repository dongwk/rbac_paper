package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 菜单和功能对应表
 * 一对一关系
 */
@TableName("app_menu_function")
@Data
public class MenuFunction extends BaseModel {
    private Integer menuId;

    private Integer functionId;
}