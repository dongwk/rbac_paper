package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 投票表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotePo extends BaseModel {
    // 投票标题
    private String title;

    // 投票描述
    private String description;

    // 投票类型 1 单选 2 多选
    private Boolean type;

    // 是否过期 1 过期 0 不过期
    private Boolean expiredFlag;

    // 投票开始时间
    private Date startDate;

    // 投票结束书剑
    private Date endDate;
}
