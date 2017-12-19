package com.ls.model;

import java.util.Date;

public class Event {
    private Integer eventId;

    private Integer userId;

    private String title;

    private String content;

    private String eventImg;

    private Integer flag;

    private Integer hostId;

    private Date createdTime;

    public Event(Integer eventId, Integer userId, String title, String content, String eventImg, Integer flag, Integer hostId, Date createdTime) {
        this.eventId = eventId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.eventImg = eventImg;
        this.flag = flag;
        this.hostId = hostId;
        this.createdTime = createdTime;
    }

    public Event() {
        super();
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg == null ? null : eventImg.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}