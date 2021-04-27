package com.app.web.po.general;

import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 图片表po
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgPo extends BaseModel {
    // 图片名称
    private String imgName;

    // 图片url
    private String imgUrl;

    // 缩略图url
    private String thumbnailUrl;

    // 图片大小字节
    private Integer imgSize;
}
