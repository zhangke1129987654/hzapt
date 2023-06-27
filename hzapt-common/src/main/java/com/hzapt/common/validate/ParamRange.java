package com.hzapt.common.validate;

import com.hzapt.common.validate.valid.ParamRangeValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 参数值校验
 *
 * @author lj
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = ParamRangeValidate.class)
public @interface ParamRange {

    String message() default "参数值错误";

    /**
     * 参数取值区间
     *
     * @return
     */
    int[] values();

    /**
     * 是否必填
     *
     * @return
     */
    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
