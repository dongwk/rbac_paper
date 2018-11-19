package com.app.model.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@TableName("app_user")
@Data
public class User {

    @TableId
    private Integer id;

    private String username;

    private String password;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

}