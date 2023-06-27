package com.hzapt.common.validate;

import com.hzapt.common.validate.valid.IdcardValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 18位中国身份证号校验
 *
 * @author LJ
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = IdcardValidate.class)
public @interface Idcard {

    String message() default "身份证号格式错误";

    /**
     * 是否必填
     *
     * @return
     */
    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
