package com.app.core.enums;

import lombok.Getter;

/**
 * 国际化配置的 properties
 */
@Getter
public enum MessagesPropertiesEnum {

    LOGIN_USERNAME_ISEMPTY("login.username.isEmpty"),
    AUTH_ACCOUNT_ERROR("auth.account.error"),
    AUTH_PASSWORD_NOTSET("auth.password.notset"),
    AUTH_TOKEN_EXIPRED("auth.token.exipred"),
    MENU_ADD_NAMEEMPTY("menu.add.nameEmpty"),
    MENU_ADD_NAMEMAX("menu.add.nameMax"),
    MENU_UPD_PARENTIDEMPTY("menu.upd.parentidEmpty"),
    MENU_UPD_IDEMPTY("menu.upd.idEmpty"),
    ROLE_ADD_NAMEEMPTY("role.add.nameEmpty"),
    ROLE_ADD_NAMEMAX("role.add.nameMax"),
    ROLE_UPD_IDEMPTY("role.upd.idEmpty"),
    USERROLE_ADD_USERIDEMPTY("userRole.add.userIdEmpty"),
    USERROLE_ADD_USERIDRANGE("userRole.add.userIdRange"),
    USERROLE_ADD_ROLEIDEMPTY("userRole.add.roleIdEmpty"),
    USERROLE_ADD_ROLEIDRANGE("userRole.add.roleIdRange");

    private String key;

    MessagesPropertiesEnum(String key) {
        this.key = key;
    }
}
