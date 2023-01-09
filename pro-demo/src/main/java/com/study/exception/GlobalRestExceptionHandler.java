package com.study.exception;

import com.study.entity.resp.RestResult;
import com.study.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局的异常处理类
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalRestExceptionHandler {

    @Autowired
    HttpServletRequest request;

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult easiBaseSysExceptionHandler(HttpServletResponse response, Exception e) {
        return ResultUtils.err(e.getMessage());
    }

    @ExceptionHandler(value = ResultBaseException.class)
    public RestResult sExceptionHandler(HttpServletResponse response, ResultBaseException e) {
        return ResultUtils.of(e.code, e.bizMessage);
    }
}
