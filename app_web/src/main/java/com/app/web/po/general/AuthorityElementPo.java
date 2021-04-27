package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 权限表po与页面元素关联表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorityElementPo extends BaseModel {
    // 权限表id
    private Integer authorityId;

    // 页面元素表id
    private Integer elementId;
}
