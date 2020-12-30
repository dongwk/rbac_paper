package com.app.core.common;

import com.app.core.exception.BizException;
import com.app.core.exception.HttpBizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public class ThrowAssert {

    public static void isTrue(Boolean bool, String properties) {
        if (bool != null && bool) {
            throw new BizException(properties);
        }
    }

    public static void isFalse(Boolean bool, String properties) {
        if (bool == null || !bool) {
            throw new BizException(properties);
        }
    }

    public static void isNull(Object obj, String properties) {
        if (obj == null) {
            throw new BizException(properties);
        }
    }

    public static void isNotNull(Object obj, String properties) {
        if (obj == null) {
            throw new BizException(properties);
        }
    }

    public static void isBlank(String str, String properties) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(properties);
        }
    }

    public static void isNotBlank(String str, String properties) {
        if (StringUtils.isNotBlank(str)) {
            throw new BizException(properties);
        }
    }

    public static void isEmpty(Collection col, String properties) {
        if (CollectionUtils.isEmpty(col)) {
            throw new BizException(properties);
        }
    }

    public static void isNotEmpty(Collection col, String properties) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new BizException(properties);
        }
    }

    public static void isEmpty(Map map, String properties) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(properties);
        }
    }

    public static void isNotEmpty(Map map, String properties) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new BizException(properties);
        }
    }

    public static void isTrue(Boolean bool, String properties, Object... args) {
        if (bool != null && bool) {
            throw new BizException(properties, args);
        }
    }

    public static void isFalse(Boolean bool, String properties, Object... args) {
        if (bool == null || !bool) {
            throw new BizException(properties, args);
        }
    }

    public static void isNull(Object obj, String properties, Object... args) {
        if (obj == null) {
            throw new BizException(properties, args);
        }
    }

    public static void isNotNull(Object obj, String properties, Object... args) {
        if (obj == null) {
            throw new BizException(properties, args);
        }
    }

    public static void isBlank(String str, String properties, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(properties, args);
        }
    }

    public static void isNotBlank(String str, String properties, Object... args) {
        if (StringUtils.isNotBlank(str)) {
            throw new BizException(properties, args);
        }
    }

    public static void isEmpty(Collection col, String properties, Object... args) {
        if (CollectionUtils.isEmpty(col)) {
            throw new BizException(properties, args);
        }
    }

    public static void isNotEmpty(Collection col, String properties, Object... args) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new BizException(properties, args);
        }
    }

    public static void isEmpty(Map map, String properties, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizException(properties, args);
        }
    }

    public static void isNotEmpty(Map map, String properties, Object... args) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new BizException(properties, args);
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

    public static void isTrue(Boolean bool, HttpStatus httpStatus, String properties) {
        if (bool != null && bool) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isFalse(Boolean bool, HttpStatus httpStatus, String properties) {
        if (bool == null || !bool) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isNull(Object obj, HttpStatus httpStatus, String properties) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isNotNull(Object obj, HttpStatus httpStatus, String properties) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isBlank(String str, HttpStatus httpStatus, String properties) {
        if (StringUtils.isBlank(str)) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isNotBlank(String str, HttpStatus httpStatus, String properties) {
        if (StringUtils.isNotBlank(str)) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isEmpty(Collection col, HttpStatus httpStatus, String properties) {
        if (CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isNotEmpty(Collection col, HttpStatus httpStatus, String properties) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isEmpty(Map map, HttpStatus httpStatus, String properties) {
        if (CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isNotEmpty(Map map, HttpStatus httpStatus, String properties) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, properties );
        }
    }

    public static void isTrue(Boolean bool, HttpStatus httpStatus, String properties, Object... args) {
        if (bool != null && bool) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isFalse(Boolean bool, HttpStatus httpStatus, String properties, Object... args) {
        if (bool == null || !bool) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isNull(Object obj, HttpStatus httpStatus, String properties, Object... args) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isNotNull(Object obj, HttpStatus httpStatus, String properties, Object... args) {
        if (obj == null) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isBlank(String str, HttpStatus httpStatus, String properties, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isNotBlank(String str, HttpStatus httpStatus, String properties, Object... args) {
        if (StringUtils.isNotBlank(str)) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isEmpty(Collection col, HttpStatus httpStatus, String properties, Object... args) {
        if (CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isNotEmpty(Collection col, HttpStatus httpStatus, String properties, Object... args) {
        if (!CollectionUtils.isEmpty(col)) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isEmpty(Map map, HttpStatus httpStatus, String properties, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }

    public static void isNotEmpty(Map map, HttpStatus httpStatus, String properties, Object... args) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new HttpBizException(httpStatus, properties , args);
        }
    }
}
