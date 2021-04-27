package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 业务用户问卷表
 */
@TableName("t_business_user_questionnaire")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserQuestionnaire extends BaseModel {
    // 问卷表id
    private Integer questionnaireId;

    // 业务用户表id
    private Integer businessUserId;
}