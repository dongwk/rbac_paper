package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 问卷表
 */
@TableName("t_activity")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity extends BaseModel {
    // 问卷标题
    private String title;

    // 问卷描述
    private String description;

    // 报名最大人数
    private Short activityMaxNum;

    // 是否过期 1 过期 0 不过期
    private Boolean expiredFlag;

    // 报名开始时间
    private Date startDate;

    // 报名结束时间
    private Date endDate;
}