package com.ls.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

public class Comment {

    public Comment(){

    }

    public Comment(Integer commentId, Integer eventId, Integer replayUserId, String replayContent, Date createdTime, Integer parentId, Integer isShow, Integer readFlag, Integer beiReplyId) {
        this.commentId = commentId;
        this.eventId = eventId;
        this.replayUserId = replayUserId;
        this.replayContent = replayContent;
        this.createdTime = createdTime;
        this.parentId = parentId;
        this.isShow = isShow;
        this.readFlag = readFlag;
        this.beiReplyId = beiReplyId;
    }

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

    private Integer beiReplyId; //被回复人

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
        this.replayContent = replayContent;
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

    public User getReplayUser() {
        return replayUser;
    }

    public void setReplayUser(User replayUser) {
        this.replayUser = replayUser;
    }

    public User getReplayCommentUser() {
        return replayCommentUser;
    }

    public void setReplayCommentUser(User replayCommentUser) {
        this.replayCommentUser = replayCommentUser;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Integer readFlag) {
        this.readFlag = readFlag;
    }

    public Integer getBeiReplyId() {
        return beiReplyId;
    }

    public void setBeiReplyId(Integer beiReplyId) {
        this.beiReplyId = beiReplyId;
    }
}
