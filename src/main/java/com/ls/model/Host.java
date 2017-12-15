package com.ls.model;

import java.util.Date;

public class Host {
    private Integer hostid;

    private String title;

    private Date createdTime;

    public Host(Integer hostid, String title, Date createdTime) {
        this.hostid = hostid;
        this.title = title;
        this.createdTime = createdTime;
    }

    public Host() {
        super();
    }

    public Integer getHostid() {
        return hostid;
    }

    public void setHostid(Integer hostid) {
        this.hostid = hostid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}