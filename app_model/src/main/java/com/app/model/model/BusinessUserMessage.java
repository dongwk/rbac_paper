package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 站内信
 */
@TableName("t_business_user_message")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserMessage extends BaseModel {
    // 发送方 business_user_id
    private Integer senderBusinessUserId;

    // 接收方 business_user_id
    private Integer receiverBusinessUserId;

    // 消息内容
    private String message;

    // 是否已读 0 未读 1 已读
    private String isRead;
}