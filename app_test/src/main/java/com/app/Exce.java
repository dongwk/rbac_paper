package com.app;

import lombok.Data;

@Data
public class Exce extends RuntimeException {
    private String id = "11";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
