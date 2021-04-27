package com.app.model.model;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 投票详情表
 */
@TableName("t_vote_detail")
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDetail extends BaseModel {
    // 投票id
    private Integer voteId;

    // 选项描述
    private String detailTitle;

    // 选项排序
    private Short seq;
}