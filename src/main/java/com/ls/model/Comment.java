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

    private User replayUser; // 评论者

    private User replayCommentUser; // 回复评论的人

    private List<Comment> commentList; // 子评论

    private Integer isShow;

}