package com.hzapt.admin.handler;

import cn.hutool.core.util.StrUtil;
import com.hzapt.common.domain.enums.ResultEnum;
import com.hzapt.common.domain.vo.ResultVo;
import com.hzapt.common.exceptions.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author LJ
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理
 * @date 2018年3月8日 下午2:18:33
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVo<String> defaultErrorHandler(Exception e) {
        log.error(">>>>>>系统异常：", e);
        return ResultVo.failure(ResultEnum.SYSTEM_ERROR, "系统繁忙，请稍后重试！", e.getMessage());
    }

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResultVo<String> myExceptionHandler(MyException e) {
        log.error(">>>>>>自定义异常：", e);
        return ResultVo.failure(e.getCode(), e.getMessage());
    }

    /**
     * Http请求异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResultVo<String> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        return ResultVo.failure(ResultEnum.FAILURE, e.getMessage());
    }

    /**
     * 方法数据绑定验证异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVo<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        StringBuilder builder = StrUtil.builder();
        for (ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            builder.append(constraint.getMessage()).append("; ");
        }
        return ResultVo.failure(ResultEnum.PARAMETER_ERROR, builder.toString());
    }

    /**
     * 对象数据绑定验证异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo<String> methodArgumentNotValidExceptionnHandler(MethodArgumentNotValidException e) {
        StringBuilder builder = StrUtil.builder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage()).append("; ");
        }
        return ResultVo.failure(ResultEnum.PARAMETER_ERROR, builder.toString());
    }

    /**
     * 对象数据绑定验证异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResultVo<String> bindExceptionHandler(BindException e) {
        StringBuilder builder = StrUtil.builder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            builder.append(fieldError.getDefaultMessage()).append("; ");
        }
        return ResultVo.failure(ResultEnum.PARAMETER_ERROR, builder.toString());
    }

}
