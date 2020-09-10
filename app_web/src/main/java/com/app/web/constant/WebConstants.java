package com.app.web.constant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 常量
 * @author ScienJus
 * @date 2015/7/31.
 */
public class WebConstants {

    // 存储当前登录用户id的字段名
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    // token有效期（小时）
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";

    // token 有效期
    public static final int TOKEN_EXPIRATION = 60;

    /**
     * auth 返回SSL TODO 暂时未用
     */
    public static final boolean SSL = false;

    // password salt
    public static final String PASSWORD_SALT = "rbac_paper";

    // 默认分页起始值
    public static final int DEFAULT_PAGE_START = 0;

    // 默认分页显示条数
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 创建默认 mybatisplus 分页对象
     */
    public static final Page defaultPage() {
        return new Page(WebConstants.DEFAULT_PAGE_START, WebConstants.DEFAULT_PAGE_SIZE);
    }
}