package com.ls.model;

import java.util.Date;

public class Host {
    private Integer hostId;

    private String title;

    private Date createdTime;

    public Host(Integer hostId, String title, Date createdTime) {
        this.hostId = hostId;
        this.title = title;
        this.createdTime = createdTime;
    }

    public Host() {
        super();
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}