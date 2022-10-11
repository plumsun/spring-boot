package com.study.entity;


/*
 * Response with single record to return
 */


public class SingleResponse<T> extends Response {

    protected T data;


    public SingleResponse(){
        System.out.println("SingleResponse");
    }

    public SingleResponse(T data) {
        this.data = data;
        System.out.println("data = " + data);
    }

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setFlag("T");
        singleResponse.setData(data);
        return singleResponse;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static SingleResponse buildFailure(String errCode, String errMessage) {
        SingleResponse response = new SingleResponse();
        response.setFlag("F");
        response.setErrorCode(errCode);
        response.setErrorInfo(errMessage);
        return response;
    }

    public static SingleResponse buildSuccess(){
        SingleResponse response = new SingleResponse();
        response.setFlag("T");
        return response;
    }

}
