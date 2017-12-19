package com.ls.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by tan.dongmei on 2017/12/15
 */
@Data
public class UserRequest {

    private Integer userId;

    private String nickName;

    private String password;

    private String name;

    private Integer age;

    private String gender;

    private String tel;

    private String email;

    private Integer isLeftChild;

    private String headImg;

    private Date createdTime;

    private String payNo;
}
