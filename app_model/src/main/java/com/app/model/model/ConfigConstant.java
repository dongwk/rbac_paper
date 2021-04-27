package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 配置表
 */
@TableName("t_config_constant")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigConstant extends BaseModel {
    // 类型
    private String constantType;

    // 值
    private String constantValue;

    // 描述
    private String description;
}