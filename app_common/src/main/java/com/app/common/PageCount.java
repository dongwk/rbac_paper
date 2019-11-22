package com.app.common;

import lombok.Data;

import java.util.List;

@Data
public class PageCount<T> {
    private int count;
    private List<T> data;
}
