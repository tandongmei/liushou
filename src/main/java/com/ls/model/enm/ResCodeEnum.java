package com.ls.model.enm;

/**
 * Created by tan.dongmei on 2017/12/1
 */
public enum ResCodeEnum {

    SUCCESS(0, "成功"),
    SERVER_ERROR(-99, "系统异常"),
    //user
    USER_EMPTY(1001, "用户不存在"),
    USER_EXISTS(1002, "用户名已经存在"),
    USER_NOT_LIGIN(1003,"用户未登录"),
    //event
    EVENT_EMPTY(2001,"详情查询异常"),
    //session
    SESSION_TIME_OUT(-1,"会话过期");

    private int code;
    private String msg;

    private ResCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
