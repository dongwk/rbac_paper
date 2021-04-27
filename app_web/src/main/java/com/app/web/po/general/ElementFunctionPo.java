package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 页面元素表po和功能表po关联表po，暂定为一对一关系
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementFunctionPo extends BaseModel {
    // 角色表id
    private Integer elementId;

    // 功能表id
    private Integer functionId;
}
