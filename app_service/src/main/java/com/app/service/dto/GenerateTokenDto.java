package com.app.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class GenerateTokenDto {
    private String token;
    private long expires;
    private boolean ssl;
}
