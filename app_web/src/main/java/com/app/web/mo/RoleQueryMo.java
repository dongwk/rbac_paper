package com.app.web.mo;

import com.app.web.mo.base.BaseMo;
import lombok.Data;

@Data
public class RoleQueryMo extends BaseMo {

    /**
     * 搜索参数
     */
    private String t;
}
