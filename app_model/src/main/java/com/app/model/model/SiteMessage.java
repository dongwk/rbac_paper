package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 网站留言
 */
@TableName("t_site_message")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteMessage extends BaseModel {
    // 员工id
    private Integer businessUserId;

    // 留言信息
    private String message;
}