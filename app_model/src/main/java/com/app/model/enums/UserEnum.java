package com.app.model.enums;

import com.app.core.enums.KeyValueEnum;

public class UserEnum {
    enum Status implements KeyValueEnum<Integer, String> {

        FALSE(0, "已删除"),
        TRUE(1, "正常");

        Status(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        private Integer key = null;
        private String value = null;

        @Override
        public Integer getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }
    }
}

