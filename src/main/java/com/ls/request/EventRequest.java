package com.ls.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by tan.dongmei on 2018/1/19
 */
@Data
public class EventRequest {

    private Integer eventId;

    private Integer userId;

    private String title;

    private String content;

    private String eventImg;

    private Integer flag;

    private Integer hostId;

    private Date createdTime;
}
