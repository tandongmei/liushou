package com.ls.model;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    private Integer eventId;

    private Integer userId;

    private String title;

    private String content;

    private String eventImg;

    private Integer flag;

    private Integer hostId;

    private Date createdTime;

    private String returnTime;

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
    }
}