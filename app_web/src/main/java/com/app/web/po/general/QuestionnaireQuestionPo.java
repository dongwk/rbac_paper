package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 问卷详情表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireQuestionPo extends BaseModel {
    // 问卷表id
    private Integer questionnaireId;

    // 标题
    private String title;

    // 类型 1 文本 2 单选 3 多选
    private Boolean detailType;

    // 排序
    private Short seq;
}
