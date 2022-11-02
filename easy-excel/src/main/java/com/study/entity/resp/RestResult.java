package com.study.entity.resp;


/**
 * The type Rest result.
 *
 * @param <T> the type parameter
 */
public class RestResult<T> extends SingleResponse<T> {

    private static final long serialVersionUID = 3161850176095433538L;

    /**
     * The Code.
     */
    protected int code;

    /**
     * The Message.
     */
    protected String message;


    /**
     * Instantiates a new Rest result.
     */
    public RestResult() {
    }

    /**
     * Instantiates a new Rest result.
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     */
    public RestResult(int code, String message, T data) {
        this.code = code;
        this.setMessage(message);
        super.setData(data);
    }

    /**
     * Instantiates a new Rest result.
     *
     * @param code the code
     * @param data the data
     */
    public RestResult(int code, T data) {
        this.code = code;
        super.setData(data);
    }

    /**
     * Instantiates a new Rest result.
     *
     * @param code    the code
     * @param message the message
     */
    public RestResult(int code, String message) {
        this.code = code;
        this.setMessage(message);
    }




    /**
     * T rest result.
     *
     * @param data the data
     * @return the rest result
     */
    public static RestResult T(Object data){
        RestResult restResult = new RestResult();
        restResult.setCode(200);
        restResult.setMessage("success");
        restResult.setData(data);
        System.out.println("T");
        return restResult;
    }

    /**
     * F rest result.
     *
     * @return the rest result
     */
    public static RestResult F(){
        return new RestResult(500,"error");
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * Ok boolean.
     *
     * @return the boolean
     */
    public boolean ok() {
        return this.code == 0 || this.code == 200;
    }

    @Override
    public String toString() {
        System.out.println("restResult.toString");
        return "RestResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
