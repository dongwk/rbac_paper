package com.app.web.po;

import lombok.Data;

@Data
public class MenuPo {

    private Integer id;

    private String name;

    private Integer parentId;

    private String url;

    private String icon;

}
