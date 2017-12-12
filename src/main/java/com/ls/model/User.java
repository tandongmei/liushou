package com.ls.model;

import java.util.Date;

public class User {
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

    public User(Integer userid, String nickname, String password, String name, Integer age, String gender, String tel, String email, Integer isleftchild, String headimg, Date createdTime) {
        this.userid = userid;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.isleftchild = isleftchild;
        this.headimg = headimg;
        this.createdTime = createdTime;
    }

    public User() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getIsleftchild() {
        return isleftchild;
    }

    public void setIsleftchild(Integer isleftchild) {
        this.isleftchild = isleftchild;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}