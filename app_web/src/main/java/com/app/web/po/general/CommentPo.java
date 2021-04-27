package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 评论表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentPo extends BaseModel {
    // 评论内容
    private String content;

    // 是否展示 1 展示 0 不展示
    private Boolean isShow;

    // 回复第一层评论id
    private Integer firstCommentId;

    // 回复评论id
    private Integer replyCommentId;

    // 是否管理员回复 1 是 0 否
    private Boolean isManager;

    // 文章表id
    private Integer articleId;

    // 业务用户表id
    private Integer businessUserId;

    // 业务用户昵称
    private String businessUserNickname;

    // 点赞数
    private Integer favorNum;
}
