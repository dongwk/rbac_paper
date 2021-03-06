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
@TableName("t_questionnaire")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Questionnaire extends BaseModel {
    // 问卷标题
    private String title;

    // 问卷开始时间
    private Date startDate;

    // 问卷结束时间
    private Date endDate;

    // 问卷描述
    private String description;
}