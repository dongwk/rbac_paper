package com.app.web.controller.manager;

/**
 * 对Token进行操作的接口
 */
public interface TokenManage {

    /**
     * 添加token
     * @param token 指定用户的id
     * @param val token 值
     */
    void add(String token, String val);

    /**
     * 检查token是否有效
     * @param token
     * @return 是否有效
     */
    boolean exists(String token);

    /**
     * 从字符串中解析token
     * @param token 加密后的字符串
     * @return
     */
    String get(String token);

    /**
     * 清除token
     * @param token 登录用户的id
     */
    void del(String token);

    /**
     * 刷新token
     * @param token
     */
    void refresh(String token);

}