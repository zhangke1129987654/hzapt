package com.hzapt.common.validate;

import com.hzapt.common.validate.valid.MobileValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 中国手机号校验
 *
 * @author LJ
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = MobileValidate.class)
public @interface Mobile {

    String message() default "手机号格式错误";

    /**
     * 是否必填
     *
     * @return
     */
    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
