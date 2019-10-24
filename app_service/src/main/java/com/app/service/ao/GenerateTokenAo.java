package com.app.service.ao;

import lombok.Builder;
import lombok.Data;

/**
 * 参考文档 https://developers.arcgis.com/rest/users-groups-and-items/generate-token.htm
 */
@Data
@Builder
public class GenerateTokenAo {
    private String username;
    private String password;
}
