package com.ls.model;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private Integer eventId;

    private Integer replayUserId;

    private String replayContent;

    private Date createdTime;

    private Integer parentId;

    public Comment(Integer commentId, Integer eventId, Integer replayUserId, String replayContent, Date createdTime, Integer parentId) {
        this.commentId = commentId;
        this.eventId = eventId;
        this.replayUserId = replayUserId;
        this.replayContent = replayContent;
        this.createdTime = createdTime;
        this.parentId = parentId;
    }

    public Comment() {
        super();
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getReplayUserId() {
        return replayUserId;
    }

    public void setReplayUserId(Integer replayUserId) {
        this.replayUserId = replayUserId;
    }

    public String getReplayContent() {
        return replayContent;
    }

    public void setReplayContent(String replayContent) {
        this.replayContent = replayContent == null ? null : replayContent.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}