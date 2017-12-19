package com.ls.request;

import lombok.Data;

/**
 * Created by tan.dongmei on 2017/12/4 0004.
 */
@Data
public class UserQueryRequest extends BaseQueryRequest{

    private Integer userId;

    private String nickName;

    private String password;

    private int age;

    private int isLeftChild;
}
