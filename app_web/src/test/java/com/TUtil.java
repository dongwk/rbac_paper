package com;

import java.util.Date;

/**
 * 新网 T 格式转换
 * @author dongwk
 * @date 2018/7/25 11:08
 **/
public class TUtil {

    public static long timeStamp(Date date){
        return date.getTime()/1000;
    }
}
