package com.hzapt.common.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Boolean枚举：0-否，1-是
 *
 * @author LIJIAN
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum {
    FALSE("否", 0), TURE("是", 1);

    /**
     * 描述
     */
    private String describe;
    /**
     * 值
     */
    private Integer value;

}