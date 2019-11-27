package com.example.mybatisplus.exception;

import com.example.mybatisplus.pojo.eo.ResultEO;
import lombok.extern.slf4j.Slf4j;
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
}
