package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文章分类表
 */
@TableName("t_category")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel {
    // 分类名称
    private String name;

    // 分类描述
    private String description;

    // 分类图标
    private String icon;

    // 是否展示 1 展示 0 不展示
    private Boolean isShow;
}