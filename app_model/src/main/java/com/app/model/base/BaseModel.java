/**
 * @author Administrator
 */
package com.app.model.base;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseModel{

    @TableId
    private Integer id;

    @TableLogic
    private Boolean status;

    private Date createTime;

    private Date updateTime;

}