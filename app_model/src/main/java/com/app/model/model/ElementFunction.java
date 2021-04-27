package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 页面元素表和功能表关联表，暂定为一对一关系
 */
@TableName("t_element_function")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementFunction extends BaseModel {
    // 角色表id
    private Integer elementId;

    // 功能表id
    private Integer functionId;
}