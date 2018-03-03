package com.ls.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Integer commentId;

    private Integer eventId;

    private Integer replayUserId;

    private String replayContent;

    private Date createdTime;

    private Integer parentId;

    private User replayUser; // 回复人

    private User replayCommentUser; // 被回复的那个人

    private List<Comment> commentList; //回复的后代回复

    private Integer isShow;

    private String returnTime;

    private Integer readFlag;//是否已读

}