package com.ls.dto;

import com.ls.model.Comment;
import com.ls.model.User;
import lombok.Data;

import java.util.List;

/**
 * Created by tan.dongmei on 2018/2/6
 */
@Data
public class CommentDTO {

//    private Integer commentId;

    private Integer eventId;

    private Integer replayUserId;  // 当前登陆用户id

    private String replayContent;  // 评论的内容

//    private Date createdTime;

//    private Integer parentId;

    private User replayUser; // 评论者,当前登陆用户，从后台获取，保证后台session和前台session生命周期一致

    private User replayCommentUser; // 回复评论的人，回复谁

    private List<Comment> commentList; // 子评论
}
