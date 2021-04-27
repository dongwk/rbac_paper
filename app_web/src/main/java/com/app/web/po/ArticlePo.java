package com.app.web.po;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文章po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePo extends BaseModel {
    // 文章标题
    private String title;

    // 文章描述
    private String description;

    // 文章类型 1 文章 2 投票 3 报名
    private Short articleType;

    // 投票表ID
    private Integer voteId;

    // 报名活动表ID
    private Integer enrolActivityId;

    // 是否展示 1 展示 0 不展示
    private String isShow;

    // 浏览量
    private Integer pageView;

    // 评论次数
    private Integer commentTimes;

    // 文章内容
    private String content;
}
