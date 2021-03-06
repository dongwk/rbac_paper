package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 业务用户投票详情表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUserVoteDetailPo extends BaseModel {
    // 投票表id
    private Integer voteId;

    // 投票详情表id
    private Integer voteDetailId;

    // 业务用户表id
    private Integer businessUserId;
}
