package com.ls.exception;

/**
 * Created by tan.dongmei on 2017/12/1
 */
public class ServiceException extends RuntimeException {

    private int code;

    private String message;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int code, String message) {
        super(message);
        this.setCode(code);
        this.setMessage(message);
    }

    public ServiceException(String message, Exception e)
    {
        super(message, e);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
