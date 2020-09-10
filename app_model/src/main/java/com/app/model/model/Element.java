package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("app_element")
@Data
public class Element extends BaseModel {
    private String code;

    private String description;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

}