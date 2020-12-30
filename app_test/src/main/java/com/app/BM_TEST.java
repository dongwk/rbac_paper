package com.app;

/**
 * 文字查找算法
 */
public class BM_TEST {

    public static int find(String pattern, String content) {
        int start = pattern.length();
        int end = content.length();

        while (start < end) {

            int position = start;

            for (int j = pattern.length() - 1; j > -1; j--) {

                if (pattern.charAt(j) != content.charAt(position)) {
//
//                    pattern.indexOf()
//                    if (content.charAt(position))
                    break;
                }

                if (j == 0) {
                    return position;
                }

                position--;
            }

        }
        return 0;
    }
}
