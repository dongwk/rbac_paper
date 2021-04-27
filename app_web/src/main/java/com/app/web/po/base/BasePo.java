package com.app.web.po.base;

import lombok.Data;

@Data
public class BasePo {
    private PagePo page;

    public PagePo getPage() {
        return page;
    }
}
