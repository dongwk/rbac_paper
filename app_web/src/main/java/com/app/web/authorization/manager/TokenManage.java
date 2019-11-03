package com.app.web.authorization.manager;

import com.app.web.authorization.model.TokenModel;

/**
 * 对Token进行操作的接口
 */
public interface TokenManage {

    /**
     * 存储token
     * @param token 指定用户的id
     * @param expires token 有效期
     */
    void storeToken(String token, int expires);

    /**
     * 检查token是否有效
     * @param token
     * @return 是否有效
     */
    boolean checkToken(String token);

    /**
     * 从字符串中解析token
     * @param token 加密后的字符串
     * @return
     */
    TokenModel getToken(String token);

    /**
     * 清除token
     * @param token 登录用户的id
     */
    void deleteToken(String token);

}