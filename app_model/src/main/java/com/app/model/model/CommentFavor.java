package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 评论点赞表
 */
@TableName("t_comment_favor")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentFavor extends BaseModel {
    // 评论id
    private Integer commentId;

    // 业务用户表id
    private Integer businessUserId;
}