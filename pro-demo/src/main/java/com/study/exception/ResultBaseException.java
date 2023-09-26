package com.study.exception;


import com.study.entity.resp.CodeI;

/**
 * 自定义异常
 */
public class ResultBaseException extends RuntimeException {

    public ResultBaseException() {
    }

    public ResultBaseException(String message) {
        super(message);
    }

    public ResultBaseException(CodeI codeI) {
        super(codeI.getMessage());
    }
}
