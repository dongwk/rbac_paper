package com.app.core.common;

import com.app.core.enums.MessagesPropertiesEnum;
import com.app.core.exception.BizException;
import com.app.core.exception.HttpBizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public class ThrowAssert {

    public static void isTrue(Boolean bool, String msg) {
        if (bool != null && bool) {
            throw new BizException(msg);
        }
    }

    public static void isFalse(Boolean bool, String msg) {
        if (bool == null || !bool) {
            throw new BizException(msg);
        }
    }

    public static void isNull(Object obj, String msg) {
        if (obj == null) {
            throw new BizException(msg);
        }
    }

    public static void isNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new BizException(msg);
        }
    }

    public static void isBlank(String str, String msg) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(msg);
        }
    }

    public static void isNotBlank(String str, String msg) {
        if (StringUtils.isNotBlank(str)) {
            throw new BizException(msg);
        }
    }

    public static void isEmpty(Collection col, String msg) {
        if (CollectionUtils.isEmpty(col)) {
            throw new BizException(msg);
        }
    }

    public static void isNotEmpty(Collection col, String msg) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new BizException(msg);
        }
    }

    public static void isEmpty(Map map, String msg) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(msg);
        }
    }

    public static void isNotEmpty(Map map, String msg) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new BizException(msg);
        }
    }

    public static void isTrue(Boolean bool, String msg, Object... args) {
        if (bool != null && bool) {
            throw new BizException(msg, args);
        }
    }

    public static void isFalse(Boolean bool, String msg, Object... args) {
        if (bool == null || !bool) {
            throw new BizException(msg, args);
        }
    }

    public static void isNull(Object obj, String msg, Object... args) {
        if (obj == null) {
            throw new BizException(msg, args);
        }
    }

    public static void isNotNull(Object obj, String msg, Object... args) {
        if (obj == null) {
            throw new BizException(msg, args);
        }
    }

    public static void isBlank(String str, String msg, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(msg, args);
        }
    }

    public static void isNotBlank(String str, String msg, Object... args) {
        if (StringUtils.isNotBlank(str)) {
            throw new BizException(msg, args);
        }
    }

    public static void isEmpty(Collection col, String msg, Object... args) {
        if (CollectionUtils.isEmpty(col)) {
            throw new BizException(msg, args);
        }
    }

    public static void isNotEmpty(Collection col, String msg, Object... args) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new BizException(msg, args);
        }
    }

    public static void isEmpty(Map map, String msg, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(msg, args);
        }
    }

    public static void isNotEmpty(Map map, String msg, Object... args) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new BizException(msg, args);
        }
    }

    public static void isTrue(Boolean bool, HttpStatus httpStatus, String msg) {
        if (bool != null && bool) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isFalse(Boolean bool, HttpStatus httpStatus, String msg) {
        if (bool == null || !bool) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isNull(Object obj, HttpStatus httpStatus, String msg) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isNotNull(Object obj, HttpStatus httpStatus, String msg) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isBlank(String str, HttpStatus httpStatus, String msg) {
        if (StringUtils.isBlank(str)) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isNotBlank(String str, HttpStatus httpStatus, String msg) {
        if (StringUtils.isNotBlank(str)) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isEmpty(Collection col, HttpStatus httpStatus, String msg) {
        if (CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isNotEmpty(Collection col, HttpStatus httpStatus, String msg) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isEmpty(Map map, HttpStatus httpStatus, String msg) {
        if (CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isNotEmpty(Map map, HttpStatus httpStatus, String msg) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, msg );
        }
    }

    public static void isTrue(Boolean bool, HttpStatus httpStatus, String msg, Object... args) {
        if (bool != null && bool) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isFalse(Boolean bool, HttpStatus httpStatus, String msg, Object... args) {
        if (bool == null || !bool) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isNull(Object obj, HttpStatus httpStatus, String msg, Object... args) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isNotNull(Object obj, HttpStatus httpStatus, String msg, Object... args) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isBlank(String str, HttpStatus httpStatus, String msg, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isNotBlank(String str, HttpStatus httpStatus, String msg, Object... args) {
        if (StringUtils.isNotBlank(str)) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isEmpty(Collection col, HttpStatus httpStatus, String msg, Object... args) {
        if (CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isNotEmpty(Collection col, HttpStatus httpStatus, String msg, Object... args) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isEmpty(Map map, HttpStatus httpStatus, String msg, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isNotEmpty(Map map, HttpStatus httpStatus, String msg, Object... args) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, msg , args);
        }
    }

    public static void isTrue(Boolean bool, MessagesPropertiesEnum propertiesEnum) {
        if (bool != null && bool) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isFalse(Boolean bool, MessagesPropertiesEnum propertiesEnum) {
        if (bool == null || !bool) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isNull(Object obj, MessagesPropertiesEnum propertiesEnum) {
        if (obj == null) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isNotNull(Object obj, MessagesPropertiesEnum propertiesEnum) {
        if (obj == null) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isBlank(String str, MessagesPropertiesEnum propertiesEnum) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isNotBlank(String str, MessagesPropertiesEnum propertiesEnum) {
        if (StringUtils.isNotBlank(str)) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isEmpty(Collection col, MessagesPropertiesEnum propertiesEnum) {
        if (CollectionUtils.isEmpty(col)) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isNotEmpty(Collection col, MessagesPropertiesEnum propertiesEnum) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isEmpty(Map map, MessagesPropertiesEnum propertiesEnum) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isNotEmpty(Map map, MessagesPropertiesEnum propertiesEnum) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new BizException(propertiesEnum);
        }
    }

    public static void isTrue(Boolean bool, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (bool != null && bool) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isFalse(Boolean bool, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (bool == null || !bool) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isNull(Object obj, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (obj == null) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isNotNull(Object obj, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (obj == null) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isBlank(String str, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isNotBlank(String str, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (StringUtils.isNotBlank(str)) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isEmpty(Collection col, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (CollectionUtils.isEmpty(col)) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isNotEmpty(Collection col, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isEmpty(Map map, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isNotEmpty(Map map, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new BizException(propertiesEnum, args);
        }
    }

    public static void isTrue(Boolean bool, HttpStatus httpStatus) {
        if (bool != null && bool) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isFalse(Boolean bool, HttpStatus httpStatus) {
        if (bool == null || !bool) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isNull(Object obj, HttpStatus httpStatus) {
        if (obj == null) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isNotNull(Object obj, HttpStatus httpStatus) {
        if (obj == null) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isBlank(String str, HttpStatus httpStatus) {
        if (StringUtils.isBlank(str)) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isNotBlank(String str, HttpStatus httpStatus) {
        if (StringUtils.isNotBlank(str)) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isEmpty(Collection col, HttpStatus httpStatus) {
        if (CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isNotEmpty(Collection col, HttpStatus httpStatus) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isEmpty(Map map, HttpStatus httpStatus) {
        if (CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isNotEmpty(Map map, HttpStatus httpStatus) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus);
        }
    }

    public static void isTrue(Boolean bool, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (bool != null && bool) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isFalse(Boolean bool, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (bool == null || !bool) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isNull(Object obj, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isNotNull(Object obj, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isBlank(String str, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (StringUtils.isBlank(str)) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isNotBlank(String str, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (StringUtils.isNotBlank(str)) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isEmpty(Collection col, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isNotEmpty(Collection col, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isEmpty(Map map, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isNotEmpty(Map map, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, propertiesEnum);
        }
    }

    public static void isTrue(Boolean bool, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (bool != null && bool) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isFalse(Boolean bool, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (bool == null || !bool) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isNull(Object obj, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isNotNull(Object obj, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isBlank(String str, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isNotBlank(String str, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (StringUtils.isNotBlank(str)) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isEmpty(Collection col, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isNotEmpty(Collection col, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isEmpty(Map map, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }

    public static void isNotEmpty(Map map, HttpStatus httpStatus, MessagesPropertiesEnum propertiesEnum, Object... args) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, propertiesEnum , args);
        }
    }
}
