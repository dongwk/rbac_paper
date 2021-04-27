package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 菜单表和功能表关联表，暂定为一对一关系
 */
@TableName("t_menu_function")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuFunction extends BaseModel {
    // 菜单表id
    private Integer menuId;

    // 功能表id
    private Integer functionId;
}