package com.ls.model;

import java.util.Date;

public class User {
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

    private String payUrl;

    private Integer noReadCommentCount;

    private Integer noReadReplyCount;

    public User(Integer userId, String nickName, String password, String name, Integer age, String gender, String tel, String email, Integer isLeftChild, String headImg, Date createdTime, String payNo, String payUrl) {
        this.userId = userId;
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.isLeftChild = isLeftChild;
        this.headImg = headImg;
        this.createdTime = createdTime;
        this.payNo = payNo;
        this.payUrl = payUrl;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
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

    public Integer getIsLeftChild() {
        return isLeftChild;
    }

    public void setIsLeftChild(Integer isLeftChild) {
        this.isLeftChild = isLeftChild;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl == null ? null : payUrl.trim();
    }

    public Integer getNoReadCommentCount() {
        return noReadCommentCount;
    }

    public void setNoReadCommentCount(Integer noReadCommentCount) {
        this.noReadCommentCount = noReadCommentCount;
    }

    public Integer getNoReadReplyCount() {
        return noReadReplyCount;
    }

    public void setNoReadReplyCount(Integer noReadReplyCount) {
        this.noReadReplyCount = noReadReplyCount;
    }
}