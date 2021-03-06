package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文章表po
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

    // 是否展示 1 展示 0 不展示
    private Boolean isShow;

    // 浏览量
    private Integer pageView;

    // 评论次数
    private Integer commentNum;

    // 文章头图
    private Integer headImgId;

    // 文章内容
    private String content;
}
