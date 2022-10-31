package com.study.entity;


/**
 * 自定义异常
 */
public class ResultBaseException extends RuntimeException{

    protected int code;
    protected String bizCode;
    protected String bizMessage;

    public ResultBaseException() {}

    public ResultBaseException(CodeI codeI) {
        super(codeI.getMessage());
        this.code = codeI.getCode();
    }

    public ResultBaseException(CodeI codeI , String errorCode,String errorInfo) {
        super(codeI.getMessage());
        this.code = codeI.getCode();
        this.bizCode = errorCode;
        this.bizMessage = errorInfo;
    }

    public ResultBaseException(String bizCode , String bizMessage) {
        super("500");
        this.code = 500;
        this.bizCode = bizCode;
        this.bizMessage = bizMessage;
    }

    public ResultBaseException(int code , String message , String bizCode , String bizMessage) {
        super(message);
        this.code = code;
        this.bizCode = bizCode;
        this.bizMessage = bizMessage;
    }

    public ResultBaseException(CodeI codeI , Throwable cause) {
        super(codeI.getMessage() , cause);
        this.code = codeI.getCode();
    }

    public ResultBaseException(CodeI codeI , String errorCode,String errorInfo,Throwable cause) {
        super(codeI.getMessage() , cause);
        this.code = codeI.getCode();
        this.bizCode = errorCode;
        this.bizMessage = errorInfo;
    }

    public ResultBaseException(String bizCode , String bizMessage , Throwable cause) {
        super("500" , cause);
        this.code = 500;
        this.bizCode = bizCode;
        this.bizMessage = bizMessage;
    }

    public ResultBaseException(int code , String message , String bizCode , String bizMessage , Throwable cause) {
        super(message, cause);
        this.code = code;
        this.bizCode = bizCode;
        this.bizMessage = bizMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizMessage() {
        return bizMessage;
    }

    public void setBizMessage(String bizMessage) {
        this.bizMessage = bizMessage;
    }

    @Override
    public String toString() {
        return "ResultBaseException{" +
                "code=" + code +
                ", message='" + super.getMessage() + '\'' +
                ", bizCode='" + bizCode + '\'' +
                ", bizMessage='" + bizMessage + '\'' +
                '}';
    }
}
