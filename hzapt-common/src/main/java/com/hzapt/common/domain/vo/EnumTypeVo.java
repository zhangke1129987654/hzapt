package com.hzapt.common.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnumTypeVo {

    @ApiModelProperty(value = "枚举描述", required = true)
    private String desc;

    @ApiModelProperty(value = "枚举值", required = true)
    private Object value;

}
