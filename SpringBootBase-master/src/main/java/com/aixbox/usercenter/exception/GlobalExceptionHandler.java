package com.aixbox.usercenter.exception;

import com.aixbox.usercenter.common.BaseResponse;
import com.aixbox.usercenter.common.ErrorCode;
import com.aixbox.usercenter.common.ResultUtils;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理器
 *
 * @author 魔王Aixbox
 * @version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e){
        log.error("businessException：" + e.getMessage(), e.getDescription());
        return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription());
    }

    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpStatus status) {
        BindingResult result = ex.getBindingResult();
        List<String> list = new LinkedList<>();
        result.getFieldErrors().forEach(error -> {
            String field = error.getField();
            Object value = error.getRejectedValue();
            String msg = error.getDefaultMessage();
            log.error(String.format("错误字段 -> %s 错误值 -> %s 原因 -> %s", field, value, msg));
            list.add(msg);
        });
        return ResultUtils.error(ErrorCode.VALIDATOR_ERROR.getCode(), ErrorCode.VALIDATOR_ERROR.getMessage(), StringUtil.join(list, ","));
    }


}
