package com.app.web.authorization.manager;

/**
 * password
 */
public interface PasswordEncrypt {

    /**
     * 创建 token
     * @return token
     */
    String encrypt();

}