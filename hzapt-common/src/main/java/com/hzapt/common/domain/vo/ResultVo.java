package com.hzapt.common.domain.vo;

import cn.hutool.core.util.StrUtil;
import com.hzapt.common.domain.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@ApiModel("ResultVo")
@Data
public class ResultVo<T> implements Serializable {

    @ApiModelProperty(value = "响应码", required = true)
    private Integer code;

    @ApiModelProperty(value = "响应信息", required = true)
    private String message;

    @ApiModelProperty(value = "响应数据")
    private T data;

    private ResultVo(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVo<T> success() {
        return success(null, null);
    }

    public static <T> ResultVo<T> success(T data) {
        return success(null, data);
    }

    public static <T> ResultVo<T> success(String message) {
        return success(message, null);
    }

    public static <T> ResultVo<T> success(String message, T data) {
        return new ResultVo<>(ResultEnum.SUCCESS.getCode(),
                message != null ? message : ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ResultVo<Map<String, T>> successMap(String key, T value) {
        Map<String, T> map = new HashMap<>();
        map.put(key, value);
        return success(null, map);
    }

    public static <T> ResultVo<T> failure() {
        return failure(ResultEnum.FAILURE);
    }

    public static <T> ResultVo<T> failure(String message) {
        return failure(ResultEnum.FAILURE, message);
    }

    public static <T> ResultVo<T> failure(ResultEnum codeEnum) {
        return failure(codeEnum, codeEnum.getMessage(), null);
    }

    public static <T> ResultVo<T> failure(ResultEnum codeEnum, String message) {
        return failure(codeEnum, message, null);
    }

    public static <T> ResultVo<T> failure(ResultEnum codeEnum, T data) {
        return failure(codeEnum, codeEnum.getMessage(), data);
    }

    public static <T> ResultVo<T> failure(ResultEnum codeEnum, String message, T data) {
        return new ResultVo<>(codeEnum.getCode(), StrUtil.isNotBlank(message) ? message : codeEnum.getMessage(), data);
    }

    public static <T> ResultVo<T> failure(Integer code, String message) {
        return new ResultVo<>(code, message, null);
    }

    public static <T> ResultVo<T> isSuccess(boolean bool, String failureMsg) {
        return isSuccess(bool, null, failureMsg);
    }

    public static <T> ResultVo<T> isSuccess(boolean bool) {
        return isSuccess(bool, null, null);
    }

    public static <T> ResultVo<T> isSuccess(boolean bool, String successMsg, String failureMsg) {
        return bool ? success(successMsg) : failure(failureMsg);
    }

}
