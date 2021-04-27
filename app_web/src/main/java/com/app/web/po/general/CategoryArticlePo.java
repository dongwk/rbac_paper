package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 分类文章表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryArticlePo extends BaseModel {
    // 分类表id
    private Integer categoryId;

    // 文章表id
    private Integer articleId;

    // 是否展示 1 展示 0 不展示
    private Boolean isShow;
}
