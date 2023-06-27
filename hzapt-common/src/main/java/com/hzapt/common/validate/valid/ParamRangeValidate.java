package com.hzapt.common.validate.valid;

import cn.hutool.core.collection.CollUtil;
import com.hzapt.common.validate.ParamRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ParamRangeValidate implements ConstraintValidator<ParamRange, Object> {

    private int[] values = null;

    private boolean required = true;

    @Override
    public void initialize(ParamRange constraintAnnotation) {
        values = constraintAnnotation.values();
        required = constraintAnnotation.required();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        List<Integer> list = CollUtil.toList();
        for (int value : values) {
            list.add(value);
        }
        if (required && obj == null) {
            return false;
        } else if (obj instanceof List<?>) {
            return CollUtil.containsAll(list, (List<Integer>) obj);
        } else if (obj != null) {
            return CollUtil.contains(list, obj);
        }
        return true;
    }

}
