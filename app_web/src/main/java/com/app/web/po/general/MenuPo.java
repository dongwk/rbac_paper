package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 菜单表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuPo extends BaseModel {
    // 菜单名称
    private String name;

    // 菜单编码
    private String code;

    // 父菜单id
    private Integer parentId;

    // 菜单地址
    private String url;

    // 图标
    private String icon;

    // 排序
    private Integer seq;

    // 类型 1 菜单，2 按钮，3 其他
    private Short type;
}
