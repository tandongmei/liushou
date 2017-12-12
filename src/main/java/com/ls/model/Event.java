package com.ls.model;

import java.util.Date;

public class Event {
    private Integer eventid;

    private Integer userid;

    private String title;

    private String content;

    private String eventimg;

    private Integer flag;

    private Integer hostid;

    private Date createdTime;

    public Event(Integer eventid, Integer userid, String title, String content, String eventimg, Integer flag, Integer hostid, Date createdTime) {
        this.eventid = eventid;
        this.userid = userid;
        this.title = title;
        this.content = content;
        this.eventimg = eventimg;
        this.flag = flag;
        this.hostid = hostid;
        this.createdTime = createdTime;
    }

    public Event() {
        super();
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getEventimg() {
        return eventimg;
    }

    public void setEventimg(String eventimg) {
        this.eventimg = eventimg == null ? null : eventimg.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getHostid() {
        return hostid;
    }

    public void setHostid(Integer hostid) {
        this.hostid = hostid;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}