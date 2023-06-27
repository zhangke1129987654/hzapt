package com.hzapt.common.exceptions;

import com.hzapt.common.domain.enums.ResultEnum;
import org.springframework.lang.Nullable;

/**
 * 自定义断言
 *
 * @author LJ
 */
public class MyAssert {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new MyException(message);
        }
    }

    public static void isTrue(boolean expression, ResultEnum resultEnum) {
        if (!expression) {
            throw new MyException(resultEnum);
        }
    }

    public static void isTrue(boolean expression, Integer code, String message) {
        if (!expression) {
            throw new MyException(code, message);
        }
    }

    public static void isNull(@Nullable Object object, String message) {
        if (object != null) {
            throw new MyException(message);
        }
    }

    public static void isNull(@Nullable Object object, ResultEnum resultEnum) {
        if (object != null) {
            throw new MyException(resultEnum);
        }
    }

    public static void isNull(@Nullable Object object, Integer code, String message) {
        if (object != null) {
            throw new MyException(code, message);
        }
    }

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new MyException(message);
        }
    }

    public static void notNull(@Nullable Object object, ResultEnum resultEnum) {
        if (object == null) {
            throw new MyException(resultEnum);
        }
    }

    public static void notNull(@Nullable Object object, Integer code, String message) {
        if (object == null) {
            throw new MyException(code, message);
        }
    }

}
