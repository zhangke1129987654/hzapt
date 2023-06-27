package com.hzapt.common.validate.valid;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.hzapt.common.validate.Idcard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdcardValidate implements ConstraintValidator<Idcard, String> {

    private boolean required = true;

    @Override
    public void initialize(Idcard constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required && StrUtil.isEmpty(value)) {
            return false;
        } else if (StrUtil.isNotEmpty(value)) {
            return Validator.isCitizenId(value);
        }
        return true;
    }
}
