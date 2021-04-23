package com.example.mybatisplus.exception;

import com.example.mybatisplus.pojo.eo.ResultEO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2019-11-27 16:24  use      1.0        1.0 Version
 */
@Slf4j
@ControllerAdvice("com.example.mybatisplus.controller")
public class GloableExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public ResultEO handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultEO.fail("失败");
    }

    /**
     * @param e 参数校验异常对象
     * @Description: conoller层参数校验异常统一处理
     * @return: DataEO
     * @Author 廖齐龙
     * @date 2020-3-31 16:08
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            sb.append(fieldError.getField()).append(":").
                    append(fieldError.getDefaultMessage()).append(";");
        }
//        return DataEO.build().badRequest(sb.toString());
        return sb.toString();
    }
}
