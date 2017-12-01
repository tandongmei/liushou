package com.ls.common;

import io.swagger.annotations.ApiModelProperty;
/**
 * Created by tan.dongmei on 2017/12/1
 */
public class RestfulResponse<T> {

    @ApiModelProperty(value = "错误码", required = true)
    private int code = 0;

    @ApiModelProperty(value = "错误消息", required = true)
    private String msg;

    @ApiModelProperty(value = "数据集合", required = true)
    private T data;

    @ApiModelProperty(value = "总记录数", required = true)
    private int totalRecords;

    public RestfulResponse() {

    }

    public RestfulResponse(int code) {
        this.code = code;
    }

    public RestfulResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}

