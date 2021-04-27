package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 问卷详情投票表
 */
@TableName("t_questionnaire_question_vote")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireQuestionVote extends BaseModel {
    // 问卷表id
    private Integer questionnaireId;

    // 问卷详情表id
    private Integer questionnaireQId;

    // 问卷详情投票值
    private String questionnaireQVoteTitle;

    // 业务用户表id
    private Short seq;
}