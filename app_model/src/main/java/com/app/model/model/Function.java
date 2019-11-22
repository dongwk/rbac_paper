package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 功能表，所有的url都在这里
 */
@TableName("app_function")
@Data
public class Function extends BaseModel {
    private String name;

    private String code;

    private String url;
}