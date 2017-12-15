package com.ls.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by tan.dongmei on 2017/12/15
 */
@Data
public class UserRequest {

    private Integer userid;

    private String nickname;

    private String password;

    private String name;

    private Integer age;

    private String gender;

    private String tel;

    private String email;

    private Integer isleftchild;

    private String headimg;

    private Date createdTime;

    private String payno;
}
