package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 评论举报表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportPo extends BaseModel {
    // 被举报文章id
    private Integer articleId;

    // 被举报评论表id
    private Integer commentId;

    // 被举报业务用户表id
    private Integer reportedBusinessUserId;

    // 被举报业务用户昵称
    private String reportedBusinessUserNickname;

    // 被举报内容
    private String reportedCommentContent;

    // 举报业务用户表id
    private Integer reporterBusinessUserId;

    // 举报业务用户昵称
    private String reporterBusinessUserNickname;

    // 举报原因
    private String message;

    // 举报类型 1 不实信息 2 有害信息 3 违法信息
    private Boolean type;
}
