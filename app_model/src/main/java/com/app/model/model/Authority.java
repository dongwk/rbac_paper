package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 20191121 暂时废弃吧不知道怎么用
 */
@Deprecated
@TableName("app_authority")
@Data
public class Authority extends BaseModel {

    private String type;

}