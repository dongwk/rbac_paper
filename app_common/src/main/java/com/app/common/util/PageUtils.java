package com.app.common.util;

public class PageUtils {

    /**
     * 计算页数
     * @param size
     * @param count
     * @return
     */
    public static int totalPage(int size, int count) {
        if (size == 0) return 0;
        return count%size == 0 ? count/size : count/size + 1;
    }
}
