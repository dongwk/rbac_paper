package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 元素和功能对应表
 * 一对一关系
 */
@TableName("app_element_function")
@Data
public class ElementFunction extends BaseModel {
    private Integer elementId;

    private Integer functionId;
}