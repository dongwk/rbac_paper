package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 20191121 暂时废弃吧不知道怎么用
 */
@Deprecated
@TableName("app_authority_function")
@Data
public class AuthorityFunction extends BaseModel {
    private Integer authorityId;

    private Integer functionId;
}