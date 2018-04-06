package com.ls.model.enm;

/**
 * Created by tan.dongmei on 2017/12/1
 */
public enum SysCodeEnum {

    USER_HEAD(0,"http://p3ga0tg9o.bkt.clouddn.com/2992fdb85109289d0641dc5a266888d4.png"),
    USER_GENDER_MAN(001,"1"),
    USER_GENDER_WOMAN(002,"0");

    private int code;
    private String msg;

    private SysCodeEnum(int code, String msg) {
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
