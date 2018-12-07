package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_authority_element")
@Data
public class AuthorityElement extends BaseModel {
    private Integer authorityId;

    private Integer element;

}