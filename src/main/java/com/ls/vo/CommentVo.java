package com.ls.vo;

import com.ls.model.Comment;
import com.ls.model.User;
import lombok.Data;

import java.util.List;

@Data
public class CommentVo {

    private List<Comment> commentList;

    private User user;
}
