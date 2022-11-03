package com.study.entity.resp;


/*
 * Response to caller
 */
public class Response extends AbstractDto {

    private static final long serialVersionUID = 1L;

    protected String flag;
    protected String errorCode;
    protected String errorInfo;

    public Response() {
        System.out.println("Response");
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        System.out.println("response.toString");
        return "Response{" +
                "flag='" + flag + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorInfo='" + errorInfo + '\'' +
                '}';
    }

    public static Response buildFailure(String errCode, String errMessage) {
        Response response = new Response();
        response.setFlag("F");
        response.setErrorCode(errCode);
        response.setErrorInfo(errMessage);
        return response;
    }

    public static Response buildSuccess(){
        Response response = new Response();
        response.setFlag("T");
        return response;
    }

}
