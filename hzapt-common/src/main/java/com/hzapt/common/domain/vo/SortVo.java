package com.hzapt.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("SortVo")
@Data
public class SortVo implements Serializable {

    @ApiModelProperty(value = "属性")
    private String property;

    @ApiModelProperty(value = "排序：ASC, DESC")
    private String direction;

}
