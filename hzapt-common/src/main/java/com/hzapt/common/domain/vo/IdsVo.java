package com.hzapt.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ApiModel("IdsVo")
@Data
public class IdsVo implements Serializable {

    @ApiModelProperty(value = "ID", required = true)
    @Size(min = 1)
    private List<String> ids;

}
