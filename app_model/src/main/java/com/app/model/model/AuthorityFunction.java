package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 权限与功能操作关联表
 */
@TableName("t_authority_function")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityFunction extends BaseModel {
    // 权限表id
    private Integer authorityId;

    // 功能操作表id
    private Integer functionId;
}