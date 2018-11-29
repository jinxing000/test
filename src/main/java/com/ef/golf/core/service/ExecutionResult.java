package com.ef.golf.core.service;

import com.ef.golf.common.Constant;
import com.ef.golf.common.RetHandle;
import org.apache.commons.lang.StringUtils;

public class ExecutionResult {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_ILLEGAL_ARGUMENT = "400";
    public static final String CODE_UNAUTHORIZED = "401";
    public static final String CODE_SERVICE_NOT_EXISTS = "404";
    public static final String CODE_SERVICE_EXCEPTION = "500";

    private String code;
    private String msg;
    private Object data;

    public static ExecutionResult getSuccess(Object data) {
        ExceptionData exceptionData = new ExceptionData(Constant.SUCCESS_RETCODE, RetHandle.getMessage(Constant.SUCCESS_RETCODE),data);
        return new ExecutionResult(CODE_SUCCESS, exceptionData);
    }

    public static ExecutionResult getSuccessAndroid(Object data) {

        return new ExecutionResult(CODE_SUCCESS,RetHandle.getMessage(Constant.SUCCESS_RETCODE),data);
    }


    public static ExecutionResult getServiceNotExists() {
        return new ExecutionResult(CODE_SERVICE_NOT_EXISTS, StringUtils.EMPTY);
    }

    public static ExecutionResult getException(String code,int retCode, String exceptionText) {
        ExceptionData exceptionData = new ExceptionData(retCode, exceptionText,"");
        return new ExecutionResult(code,exceptionData);
    }

    public ExecutionResult(String code, Object data) {
        this.code = code;
//        this.msg = "";
        this.data = data;
    }

    public ExecutionResult(String code, String msg,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ExecutionResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = msg;
    }


    public int getException(){
        if (data instanceof ExceptionData) {
            return ((ExceptionData) data).getCode();
        }
        return 0;
    }
    public String getExceptionText(){
        if (data instanceof ExceptionData) {
            return ((ExceptionData) data).getMessage();
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }


    @Override
    public String toString() {
        return "JsonResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class ExceptionData {
        private int code;
        private String message;
        private Object data;

        public ExceptionData(int code, String message,Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Object getData(){return data;}
    }

}
