package com.app.common.util.encode;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

}