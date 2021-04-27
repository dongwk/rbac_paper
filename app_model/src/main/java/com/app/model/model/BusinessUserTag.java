package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 业务用户标签
 */
@TableName("t_business_user_tag")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserTag extends BaseModel {
    // 标签id（枚举）
    private Integer businessUserId;

    // 标签id（枚举）
    private Integer tagId;
}