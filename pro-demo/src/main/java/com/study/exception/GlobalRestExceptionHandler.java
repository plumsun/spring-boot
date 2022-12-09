package com.study.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    public String easiBaseSysExceptionHandler(HttpServletResponse response, Exception e) {
        log.error("GlobalExceptionHandler...",e);
        return e.getMessage();
    }

    @ExceptionHandler(value = ResultBaseException.class)
    public Map<String, Object> sExceptionHandler(HttpServletResponse response, ResultBaseException e) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status","f");
        map.put("data",null);
        map.put("code",e.getCode());
        map.put("msg",e.getBizMessage());
        log.error("GlobalExceptionHandler...",e);
        return map;
    }
}
