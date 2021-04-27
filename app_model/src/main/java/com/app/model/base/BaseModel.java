/**
 * @author Administrator
 */
package com.app.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel{

    @TableId(value = "id", type = IdType.INPUT)
    protected Integer id;

    /**
     * 逻辑删除字段配置
     */
    @TableLogic
    protected Boolean status;

    /**
     * 创建时间
     */
    protected Date createDate;

    /**
     * 更新时间
     */
    protected Date updateDate;

}