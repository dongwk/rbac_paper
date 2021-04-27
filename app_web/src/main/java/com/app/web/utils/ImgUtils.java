package com.app.web.utils;

import com.app.web.constant.ConfigConstants;
import com.app.web.constant.WebConstants;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * 图片
 *
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 15:30
 */
public class ImgUtils {

    public static String IMG_SERVER_URL = null;

    public static String fullUrl(String url){
        if (StringUtils.isBlank(url)) {
            return null;
        }
        if (IMG_SERVER_URL == null) {
            IMG_SERVER_URL = ConfigConstants.getOne(WebConstants.IMG_SERVER_KEY);
        }
        return IMG_SERVER_URL+url;
    }
}
