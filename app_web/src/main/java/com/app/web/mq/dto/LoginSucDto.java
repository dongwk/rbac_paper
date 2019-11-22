package com.app.web.mq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class LoginSucDto {
    private int id;
    private Date loginDate;
}
