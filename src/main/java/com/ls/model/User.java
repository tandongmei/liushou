package com.ls.model;

import lombok.Data;
import org.springframework.data.annotation.Id;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * Created by tan.dongmei on 2017/12/1
 */
// 用户实体类
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer userId;
    /**
     *  用户昵称
     * */
    private String nickName;
    /**
     *  用户密码
     * */
    private String password;
    /**
     *  用户真实姓名
     * */
    private String name;
    /**
     *  年龄
     * */
    private int age;
    /**
     *  性别
     * */
    private String sex;
    /**
     *  电话
     * */
    private String phone;
    /**
     *  邮箱
     * */
    private String email;
    /**
     *  用户头像
     * */
    private String headImg;
    /**
     *  是否是留守儿童
     * */
    private int isLeftChild;
    /**
     *  创建时间
     * */
    private Date createdTime;

}
