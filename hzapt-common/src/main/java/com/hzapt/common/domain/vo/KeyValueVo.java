package com.hzapt.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@ApiModel("KeyValueVo")
@Data
@AllArgsConstructor
public class KeyValueVo implements Serializable {

    @ApiModelProperty(value = "Key")
    private String key;

    @ApiModelProperty(value = "Value")
    private Object value;

}
