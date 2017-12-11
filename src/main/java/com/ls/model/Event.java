package com.ls.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tan.dongmei on 2017/12/11
 */
@Entity
@Data
public class Event {

    @Id
    @GeneratedValue
    private Integer eventId;
    /**
     *  用户id
     * */
    private Integer userId;
    /**
     *  板块id
     * */
    private Integer hostId;
    /**
     *  事件标题
     * */
    private String title;
    /**
     *  事件内容
     * */
    private String content;
    /**
     *  标志：1，热门 2，最新
     * */
    private Integer flag;
    /**
     *  图片地址
     * */
    private String eventImg;

    private Date createdTime;

}
