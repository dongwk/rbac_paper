package com.app.web.authorization.manager;

import com.app.web.authorization.model.TokenModel;

/**
 * 生成 token
 */
public interface TokenGenerate {

    /**
     * 创建 token
     * @return token
     */
    String createToken();

}