package com.ef.golf.exception;

/**

 商城异常
 */
public class MallException extends Exception {

    private int retCode = 0;
    private String message;

    public MallException(int retCode) {
        this.retCode = retCode;
    }


    public int getRetCode() {
        return retCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
