package com.app.web.po;

import com.app.web.po.base.BasePo;
import lombok.Data;

@Data
public class RoleQueryPo extends BasePo {

    /**
     * 搜索参数
     */
    private String t;
}
