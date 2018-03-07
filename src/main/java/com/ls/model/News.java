package com.ls.model;

import java.util.Date;

public class News {
    private Integer newsId;

    private Integer userId;

    private Integer hostId;

    private String title;

    private String content;

    private String newsImg;

    private Date createdTime;

    public News(Integer newsId, Integer userId, Integer hostId, String title, String content, String newsImg, Date createdTime) {
        this.newsId = newsId;
        this.userId = userId;
        this.hostId = hostId;
        this.title = title;
        this.content = content;
        this.newsImg = newsImg;
        this.createdTime = createdTime;
    }

    public News() {
        super();
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg == null ? null : newsImg.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}