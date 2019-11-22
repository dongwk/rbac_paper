package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 20191121 暂时废弃吧不知道怎么用
 */
@Deprecated
@TableName("app_authority_element")
@Data
public class AuthorityElement extends BaseModel {
    private Integer authorityId;

    private Integer elementId;

}