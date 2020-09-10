/**
 * @author Administrator
 */
package com.app.model.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseModel{

    @TableId
    private Integer id;

    /**
     * 逻辑删除字段配置
     */
    @TableLogic
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}