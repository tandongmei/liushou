package com.ls.mapper;

import com.ls.model.Comment;
import com.ls.request.CommentQueryRequest;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findParentCommentsByEventId(CommentQueryRequest commentQueryRequest);

    List<Comment> findReplayCommentsByCommentId(CommentQueryRequest cqr);
}