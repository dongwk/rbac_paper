package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("app_function")
@Data
public class Function extends BaseModel {
    private String name;

    private String code;

    private String url;
}