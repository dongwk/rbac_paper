package com.app.web.authorization.manager;

import com.app.web.authorization.model.TokenModel;

/**
 * 对Token进行操作的接口
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    TokenModel createToken(long userId);

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