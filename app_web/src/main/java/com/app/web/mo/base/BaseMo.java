package com.app.web.mo.base;

import lombok.Data;

@Data
public class BaseMo {
    private PageMo page;

    public PageMo getPage() {
        return page;
    }
}
