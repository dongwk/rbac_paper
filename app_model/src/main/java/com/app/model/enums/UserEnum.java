package com.app.model.enums;

import com.app.core.enums.KeyValueEnum;
import lombok.Getter;

public class UserEnum {
    @Getter
    enum Status implements KeyValueEnum<Integer, String> {

        FALSE(0, "已删除"),
        TRUE(1, "正常");

        Status(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private Integer value;
        private String name;
    }
}

