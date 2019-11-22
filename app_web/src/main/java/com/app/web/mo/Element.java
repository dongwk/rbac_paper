package com.app.web.mo;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;
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