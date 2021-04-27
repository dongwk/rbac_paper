package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 权限表与页面元素关联表
 */
@TableName("t_authority_element")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityElement extends BaseModel {
    // 权限表id
    private Integer authorityId;

    // 页面元素表id
    private Integer elementId;
}