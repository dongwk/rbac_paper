package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 业务用户问卷详情表
 */
@TableName("t_business_user_questionnaire_q")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserQuestionnaireQ extends BaseModel {
    // 问卷表id
    private Integer questionnaireId;

    // 问卷详情表id
    private Integer questionnaireQId;

    // 问卷详情值，文本类型为内容，单选多选逗号,分隔
    private String questionnaireQValue;

    // 业务用户表id
    private Integer businessUserId;
}