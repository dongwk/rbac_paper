package com.app.web.po;

import com.app.model.base.BaseModel;
import com.app.model.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 评论表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentPo extends BaseModel {

    // 员工表ID
    private Integer businessUserId;

    // 员工昵称
    private String nickname;

    // 头像url
    private String avatarImgUrl;

    // 评论内容
    private String content;

    // 点赞数
    private Integer favorNum;

    // 评论时间
    private Date createDate;

    // 回复评论ID
    private Integer replyCommentId;

    // 第一个评论ID
    private Integer firstCommentId;

    // 当前评论的回复
    private List<CommentPo> replyFirstComments;

    public CommentPo(Comment obj) {
        if (obj != null) {
            this.id = obj.getId();
            this.businessUserId = obj.getBusinessUserId();
            this.nickname = obj.getBusinessUserNickname();
            this.content = obj.getContent();
            this.favorNum = obj.getFavorNum();
            this.createDate = obj.getCreateDate();
            this.replyCommentId = obj.getReplyCommentId();
            this.firstCommentId = obj.getFirstCommentId();
        }
    }
}
