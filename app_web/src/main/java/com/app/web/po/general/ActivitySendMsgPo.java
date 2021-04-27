package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 报名活动发送消息记录表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySendMsgPo extends BaseModel {
    // 活动id
    private Integer activityId;

    // 接收员工id
    private Integer receiverBusinessUserId;

    // 接收员工email
    private String receiverEmail;

    // 消息类型 1 站内信 2 邮箱
    private Boolean messageType;

    // 是否发送 1 发送 0 不发送
    private Boolean isSend;

    // 是否已发送 1 已发送 0 未发送
    private Boolean seedStatus;
}
