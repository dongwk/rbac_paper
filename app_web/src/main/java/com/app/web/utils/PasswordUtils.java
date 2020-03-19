package com.app.web.utils;

import com.app.common.util.encode.MD5Util;
import com.app.web.constant.WebConstants;
import org.apache.commons.lang3.StringUtils;

public class PasswordUtils {

    public static String encrypt(String password, String salt){
        return MD5Util.md5(WebConstants.PASSWORD_SALT+StringUtils.trim(password)+StringUtils.trim(salt));
    }
}
