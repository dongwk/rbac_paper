/**
 * @author Administrator
 */
package com.app.model.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseModel{

    @TableId
    private Integer id;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}